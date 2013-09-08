package com.jiongsoft.cocit.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.lang.Mirror;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocsoft.ComFactory;
import com.jiongsoft.cocit.cocsoft.EntityManager;
import com.jiongsoft.cocit.cocsoft.EntityManagerFactory;
import com.jiongsoft.cocit.cocui.model.CuiModelFactory;
import com.jiongsoft.cocit.corm.expr.CndExpr;
import com.jiongsoft.cocit.corm.expr.Expr;
import com.jiongsoft.cocit.mvc.CocitBizDataNode;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.Json;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

class CocitBizActionHelper {

	String operationArgs;
	Long moduleID;
	Long tableID;
	Long operationID;
	String operationMode;
	CocBizModule module;
	CocBizTable table;
	CocBizOperation operation;
	EntityManager entityManager;
	CuiModelFactory modelFactory;
	Object data;
	CocException exception;
	/**
	 * 执行批量数据操作时的数据列表
	 */
	List dataList;

	/**
	 * 
	 * @param operationArgs
	 *            参数组成“bizModuleID:bizTableID:bizOperationID”
	 * @return
	 */
	static CocitBizActionHelper make(String args) {
		return new CocitBizActionHelper(args, null, null);
	}

	/**
	 * 
	 * @param operationArgs
	 *            参数组成“bizModuleID:bizTableID:bizOperationID”
	 * @return
	 */
	static CocitBizActionHelper make(String args, String dataID, CocitBizDataNode dataNode) {
		return new CocitBizActionHelper(args, dataID, dataNode);
	}

	private CocitBizActionHelper(String args, String dataID, CocitBizDataNode dataNode) {
		Log.debug("CocitBizActionHelper... {operationArgs:%s, operationArgs:%s, dataNode:%s}", args, dataID, dataNode);

		this.operationArgs = args;

		initBizOperation(args);
		this.initData(dataID);

		// 注入HTTP参数到实体对象中
		if (dataNode != null) {
			Class type = entityManager.getType();
			data = dataNode.inject(Mirror.me(type), data, null);
		}

		modelFactory = Cocit.getCuiModelFactory();
	}

	private void initBizOperation(String args) {
		/*
		 * 转换参数
		 */
		String[] argArr = ActionUtil.decodeArgs(args);

		String argModuleID = argArr.length > 0 ? argArr[0] : null;
		String argTableID = argArr.length > 1 ? argArr[1] : null;
		String argOperationID = argArr.length > 2 ? argArr[2] : null;

		Log.debug("CocitBizActionHelper.initBizOperation... {operationArgs:%s, moduleID:%s, tableID:%s, operationID:%s}", args, argModuleID, argTableID, argOperationID);

		/*
		 * 获取数据模块和数据表对象
		 */
		moduleID = StringUtil.castTo(argModuleID, 0l);
		tableID = StringUtil.castTo(argTableID, 0l);
		operationID = StringUtil.castTo(argOperationID, 0l);

		ComFactory softFactory = Cocit.getComFactory();

		module = softFactory.getBizModule(moduleID);
		table = softFactory.getBizTable(moduleID, tableID);
		operation = softFactory.getBizOperation(moduleID, tableID, operationID);
		if (operation != null)
			operationMode = operation.getOperationMode();

		Log.debug("CocitBizActionHelper.initBizOperation: module = %s, table = %s", module, table);

		/*
		 * 初始化实体管理器
		 */
		EntityManagerFactory entityManagerFactory = Cocit.getEntityManagerFactory();
		entityManager = entityManagerFactory.getEntityManager(module, table);

	}

	private void initData(String argDataID) {
		if (argDataID == null) {
			return;
		}

		Log.debug("CocitBizActionHelper.initData... arrDataID = %s", argDataID);

		// 加载单条数据
		String[] arrDataID = StringUtil.toArray(argDataID);
		if (arrDataID != null && arrDataID.length == 1) {
			Long dataID = StringUtil.castTo(arrDataID[0], 0l);
			try {
				data = entityManager.load(dataID, operationMode);
			} catch (CocException e) {
				exception = e;
			}
		}
	}

	private CndExpr makeExprFromJsonByContains(String jsonExpr, StringBuffer logExpr) {

		if (StringUtil.isNil(jsonExpr) || (!jsonExpr.startsWith("{") && !jsonExpr.endsWith("}"))) {
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
	private CndExpr makeExprFromJsonByIn(String jsonExpr, StringBuffer logExpr) {

		if (StringUtil.isNil(jsonExpr) || (!jsonExpr.startsWith("{") && !jsonExpr.endsWith("}"))) {
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

		CndExpr naviTreeExpr = this.makeExprFromJsonByIn(queryNaviTreeJsonExpr, logExpr);
		CndExpr parentGridExpr = this.makeExprFromJsonByIn(queryParentGridJsonExpr, logExpr);
		CndExpr searchBoxExpr = this.makeExprFromJsonByContains(querySearchBoxJsonExpr, logExpr);
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
