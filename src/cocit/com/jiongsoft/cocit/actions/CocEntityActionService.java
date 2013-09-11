package com.jiongsoft.cocit.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.lang.Mirror;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.mvc.adaptor.EntityParamNode;
import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityOperationService;
import com.jiongsoft.cocit.service.CocEntityTableService;
import com.jiongsoft.cocit.service.CocServiceFactory;
import com.jiongsoft.cocit.service.CocEntityManager;
import com.jiongsoft.cocit.service.CocEntityManagerFactory;
import com.jiongsoft.cocit.ui.model.CuiModelFactory;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.Json;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

/**
 * 实体数据管理逻辑Action服务类：服务于{@link CocEntityAction}
 * 
 * @author yongshan.ji
 * 
 */
class CocEntityActionService {

	String operationArgs;

	Long entityModuleID;

	Long entityTableID;

	Long entityOperationID;

	String entityOperationMode;

	CocEntityModuleService entityModule;

	CocEntityTableService entityTable;

	CocEntityOperationService entityOperation;

	CocEntityManager entityManager;

	CuiModelFactory modelFactory;

	Object entityData;

	CocException exception;

	/**
	 * 执行批量数据操作时的数据列表
	 */
	List entityDataList;

	/**
	 * 构造一个Action Service对象
	 * 
	 * @param operationArgs
	 *            参数组成“entityModuleID:entityTableID:entityOperationID”
	 * @return
	 */
	static CocEntityActionService make(String args) {
		return new CocEntityActionService(args, null, null);
	}

	/**
	 * 
	 * @param operationArgs
	 *            参数组成“entityModuleID:entityTableID:entityOperationID”
	 * @return
	 */
	static CocEntityActionService make(String args, String dataID, EntityParamNode dataNode) {
		return new CocEntityActionService(args, dataID, dataNode);
	}

	private CocEntityActionService(String args, String dataID, EntityParamNode dataNode) {
		Log.debug("CocEntityActionService... {operationArgs:%s, operationArgs:%s, dataNode:%s}", args, dataID, dataNode);

		this.operationArgs = args;

		initEntityOperation(args);
		this.initEntityData(dataID);

		// 注入HTTP参数到实体对象中
		if (dataNode != null) {
			Class type = entityManager.getType();
			entityData = dataNode.inject(Mirror.me(type), entityData, null);
		}

		modelFactory = Cocit.getUIModelFactory();
	}

	private void initEntityOperation(String args) {
		/*
		 * 转换参数
		 */
		String[] argArr = ActionUtil.decodeArgs(args);

		String argModuleID = argArr.length > 0 ? argArr[0] : null;
		String argTableID = argArr.length > 1 ? argArr[1] : null;
		String argOperationID = argArr.length > 2 ? argArr[2] : null;

		Log.debug("CocEntityActionService.initEntityOperation... {operationArgs:%s, entityModuleID:%s, entityTableID:%s, entityOperationID:%s}", args, argModuleID, argTableID, argOperationID);

		/*
		 * 获取数据模块和数据表对象
		 */
		entityModuleID = StringUtil.castTo(argModuleID, 0L);
		entityTableID = StringUtil.castTo(argTableID, 0L);
		entityOperationID = StringUtil.castTo(argOperationID, 0L);

		CocServiceFactory softFactory = Cocit.getServiceFactory();

		entityModule = softFactory.getEntityModuleService(entityModuleID);
		entityTable = softFactory.getEntityTableService(entityModuleID, entityTableID);
		entityOperation = softFactory.getEntityOperationService(entityModuleID, entityTableID, entityOperationID);
		if (entityOperation != null)
			entityOperationMode = entityOperation.getOperationMode();

		Log.debug("CocEntityActionService.initEntityOperation: entityModule = %s, entityTable = %s", entityModule, entityTable);

		/*
		 * 初始化实体管理器
		 */
		CocEntityManagerFactory entityManagerFactory = Cocit.getEntityManagerFactory();
		entityManager = entityManagerFactory.getEntityManager(entityModule, entityTable);

	}

	private void initEntityData(String argEntityID) {
		if (argEntityID == null) {
			return;
		}

		Log.debug("CocEntityActionService.initData... arrDataID = %s", argEntityID);

		// 加载单条数据
		String[] arrDataID = StringUtil.toArray(argEntityID);
		if (arrDataID != null && arrDataID.length == 1) {
			Long dataID = StringUtil.castTo(arrDataID[0], 0L);
			try {
				entityData = entityManager.load(dataID, entityOperationMode);
			} catch (CocException e) {
				Log.warn("", e);
				exception = e;
			}
		}
	}

	private CndExpr makeRuleExprFromJson(String jsonExpr, StringBuffer logExpr) {

		if (StringUtil.isNil(jsonExpr) || jsonExpr.charAt(0) != '{') {
			return null;
		}

		CndExpr retExpr = null;

		Map map = Json.fromJson(Map.class, jsonExpr);
		Iterator<String> exprs = map.keySet().iterator();
		while (exprs.hasNext()) {
			String prop = exprs.next();

			if (!StringUtil.isNil(prop)) {
				String value = map.get(prop).toString();

				if (!StringUtil.isNil(value)) {
					if (retExpr == null) {
						retExpr = Expr.contains(prop, value);

						logExpr.append("(" + prop + " contains " + value + ")");
					} else {
						retExpr = retExpr.and(Expr.contains(prop, value));

						logExpr.append(" and (" + prop + " contains " + value + ")");
					}
				}
			}
		}

		return retExpr;
	}

	/**
	 * 将JSON表达式转换成 in 表达式，即JSON对象中的字段值为数组。
	 * <p>
	 * JSON格式：
	 * <p>
	 * <code>
	 * {field-1:["value-1","value-2",...,"value-n"], field-2:[...], ... , field-n: [...]}
	 * </code>
	 */
	private CndExpr makeInExprFromJson(String jsonExpr, StringBuffer logExpr) {

		if (StringUtil.isNil(jsonExpr) || jsonExpr.charAt(0) != '{') {
			return null;
		}

		CndExpr retExpr = null;

		Map map = Json.fromJson(Map.class, jsonExpr);
		Iterator<String> exprs = map.keySet().iterator();
		while (exprs.hasNext()) {
			String prop = exprs.next();
			Object value = map.get(prop);
			if (value instanceof List) {
				List valueList = (List) value;

				if (retExpr == null) {
					retExpr = Expr.in(prop, valueList);

					logExpr.append("(" + prop + " in " + Json.toJson(valueList) + ")");
				} else {
					retExpr = retExpr.and(Expr.in(prop, valueList));

					logExpr.append(" and (" + prop + " in " + Json.toJson(valueList) + ")");
				}
			} else {
				String str = value.toString();

				if (retExpr == null) {
					retExpr = Expr.eq(prop, str);

					logExpr.append("(" + prop + " eq " + str + ")");
				} else {
					retExpr = retExpr.and(Expr.eq(prop, str));

					logExpr.append("and (" + prop + " in " + str + ")");
				}
			}
		}

		return retExpr;
	}

	@SuppressWarnings("unused")
	CndExpr makeExpr() {
		CocitHttpContext httpContext = Cocit.getHttpContext();

		StringBuffer logExpr = new StringBuffer();
		CndExpr retExpr = null;

		/*
		 * 解析查询条件
		 */
		String queryNaviTreeJsonExpr = httpContext.getParameterValue("queryNaviTreeJsonExpr", "");
		String querySearchBoxJsonExpr = httpContext.getParameterValue("querySearchBoxJsonExpr", "");
		String queryParentGridJsonExpr = httpContext.getParameterValue("queryParentGridJsonExpr", "");

		CndExpr naviTreeExpr = this.makeInExprFromJson(queryNaviTreeJsonExpr, logExpr);
		CndExpr parentGridExpr = this.makeInExprFromJson(queryParentGridJsonExpr, logExpr);
		CndExpr searchBoxExpr = this.makeRuleExprFromJson(querySearchBoxJsonExpr, logExpr);
		if (naviTreeExpr != null) {
			if (retExpr == null)
				retExpr = naviTreeExpr;
			else
				retExpr = retExpr.and(naviTreeExpr);
		}
		if (parentGridExpr != null) {
			if (retExpr == null)
				retExpr = parentGridExpr;
			else
				retExpr = retExpr.and(parentGridExpr);
		}
		if (searchBoxExpr != null) {
			if (retExpr == null)
				retExpr = searchBoxExpr;
			else
				retExpr = retExpr.and(searchBoxExpr);
		}

		// 解析JSON表达式
		if (retExpr == null) {
			retExpr = Expr.notNull("id");
			logExpr.append("(id not null)");
		}

		/*
		 * 解析分页和排序
		 */
		int pageIndex = httpContext.getParameterValue("pageIndex", 1);
		int pageSize = httpContext.getParameterValue("pageSize", ActionUtil.DEFAULT_PAGE_SIZE);
		String sortField = httpContext.getParameterValue("sortField", "id");
		String sortOrder = httpContext.getParameterValue("sortOrder", "desc");

		if (sortOrder.toLowerCase().equals("asc")) {
			retExpr = retExpr.addAsc(sortField);
		} else {
			retExpr = retExpr.addDesc(sortField);
		}
		logExpr.append("( order by " + sortField + " " + sortOrder + ")");

		retExpr = retExpr.setPager(pageIndex, pageSize);
		logExpr.append("(pageIndex=" + pageIndex + " pageSize=" + pageSize + ")");

		/*
		 * 返回解析结果
		 */
		Log.info("查询条件：opArgs = %s, queryExpr = %s", operationArgs, logExpr.toString());
		return retExpr;
	}
}
