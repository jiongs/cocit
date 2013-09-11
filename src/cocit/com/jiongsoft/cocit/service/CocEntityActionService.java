package com.jiongsoft.cocit.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.lang.Mirror;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.actions.CocEntityAction;
import com.jiongsoft.cocit.mvc.adaptor.EntityParamNode;
import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.jiongsoft.cocit.ui.widget.WidgetModelFactory;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.Json;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

/**
 * 实体数据管理逻辑Action服务类：服务于{@link CocEntityAction}
 * 
 * @author yongshan.ji
 * 
 */
public class CocEntityActionService {

	public String pathArgs;

	public Long entityModuleID;

	public Long entityTableID;

	public Long entityOperationID;

	public String entityOperationMode;

	public CocEntityModuleService entityModule;

	public CocEntityTableService entityTable;

	public CocEntityOperationService entityOperation;

	public CocEntityManager entityManager;

	public WidgetModelFactory modelFactory;

	public Object entity;

	public Throwable exception;

	public String[] entityID;

	/**
	 * 构造一个Action Service对象
	 * 
	 * @param pathArgs
	 *            参数组成“entityModuleID:entityTableID:entityOperationID”
	 * @return
	 */
	public static CocEntityActionService make(String args) {
		return new CocEntityActionService(args, null, null);
	}

	/**
	 * 
	 * @param pathArgs
	 *            参数组成“entityModuleID:entityTableID:entityOperationID”
	 * @return
	 */
	public static CocEntityActionService make(String args, String entityID, EntityParamNode dataNode) {
		return new CocEntityActionService(args, entityID, dataNode);
	}

	private CocEntityActionService(String args, String entityID, EntityParamNode dataNode) {
		Log.debug("CocEntityActionService... {pathArgs:%s, pathArgs:%s, dataNode:%s}", args, entityID, dataNode);

		this.pathArgs = args;

		initEntityOperation(args);
		this.initEntity(entityID);

		// 注入HTTP参数到实体对象中
		if (dataNode != null) {
			Class type = entityManager.getType();
			entity = dataNode.inject(Mirror.me(type), entity, null);
		}

		modelFactory = Cocit.getWidgetModelFactory();
	}

	private void initEntityOperation(String args) {
		/*
		 * 转换参数
		 */
		String[] argArr = ActionUtil.decodeArgs(args);

		String argModuleID = argArr.length > 0 ? argArr[0] : null;
		String argTableID = argArr.length > 1 ? argArr[1] : null;
		String argOperationID = argArr.length > 2 ? argArr[2] : null;

		Log.debug("CocEntityActionService.initEntityOperation... {pathArgs:%s, entityModuleID:%s, entityTableID:%s, entityOperationID:%s}", args, argModuleID, argTableID, argOperationID);

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

	private void initEntity(String argEntityID) {
		if (argEntityID == null) {
			return;
		}

		Log.debug("CocEntityActionService.initEntity... argEntityID = %s", argEntityID);

		// 加载单条数据
		entityID = StringUtil.toArray(argEntityID);
		if (entityID != null && entityID.length == 1) {
			Long id = StringUtil.castTo(entityID[0], 0L);
			try {
				entity = entityManager.load(id, entityOperationMode);
			} catch (Throwable e) {
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
	public CndExpr makeExpr() {
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
		Log.info("查询条件：opArgs = %s, queryExpr = %s", pathArgs, logExpr.toString());
		return retExpr;
	}
}
