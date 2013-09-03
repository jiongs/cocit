package com.jiongsoft.cocit.actions;

import java.util.List;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocsoft.ComFactory;
import com.jiongsoft.cocit.cocsoft.EntityManager;
import com.jiongsoft.cocit.cocsoft.EntityManagerFactory;
import com.jiongsoft.cocit.cocui.CuiModelView;
import com.jiongsoft.cocit.cocui.model.CuiBizModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;
import com.jiongsoft.cocit.cocui.model.CuiModelFactory;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModelData;
import com.jiongsoft.cocit.corm.expr.CndExpr;
import com.jiongsoft.cocit.corm.expr.Expr;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.Tree;

/**
 * 数据模块Action：负责处理业务数据的“增加、删除、查询、修改、导入、导出”等业务操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
public class CocitBizAction {

	/**
	 * 获取“数据模块”界面模型，用于输出数据模块的界面。
	 * 
	 * @param args
	 *            Hex加密后的调用参数，参数组成“dataModuleID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_MODULE_MODEL)
	public CuiBizModuleModel getBizModuleModel(String args) {
		// 转换模块参数
		String[] argArr = ActionUtil.decodeArgs(args);

		String moduleID = argArr.length > 0 ? argArr[0] : null;

		Log.debug("CocitBizAction.getBizModuleModel... {args:%s, moduleID:%s}", args, moduleID);

		// 获取数据模块对象
		ComFactory softFactory = Cocit.getComFactory();
		CocBizModule module = softFactory.getBizModule(Long.parseLong(moduleID));

		Log.debug("CocitBizAction.getBizModuleModel: module = %s", module);

		// 获取模块界面模型
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiBizModuleModel moduleModel = modelFactory.getBizModuleModel(module);

		Log.debug("CocitBizAction.getBizModuleModel: moduleModel = %s", moduleModel);

		// 返回
		return moduleModel;
	}

	/**
	 * 获取“数据表”界面模型，用于输出数据表界面。
	 * 
	 * @param args
	 *            Hex加密后的调用参数，参数组成“dataModuleID:dataTableID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_TABLE_MODEL)
	public CuiBizTableModel getBizTableModel(String args) {
		// 获取数据表参数
		String[] argArr = ActionUtil.decodeArgs(args);

		String moduleID = argArr.length > 0 ? argArr[0] : null;
		String tableID = argArr.length > 1 ? argArr[1] : null;

		Log.debug("CocitBizAction.getBizTableModel... {args:%s, moduleID:%s, tableID}", args, moduleID, tableID);

		// 获取数据模块和数据表对象
		ComFactory softFactory = Cocit.getComFactory();
		Long mID = Long.parseLong(moduleID);
		CocBizModule module = softFactory.getBizModule(mID);
		CocBizTable table = softFactory.getBizTable(mID, Long.parseLong(tableID));

		Log.debug("CocitBizAction.getBizTableModel: module=%s, table = %s", module, table);

		// 获取数据表界面模型
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiBizTableModel tableModel = modelFactory.getBizTableModel(module, table);

		Log.debug("CocitBizAction.getBizTableModel: tableModel = %s", tableModel);

		// 返回
		return tableModel;
	}

	/**
	 * 获取“数据表GRID”数据模型，用于输出数据表GRID所需要的JSON数据。
	 * 
	 * @param args
	 *            加密后的调用参数，参数组成“dataModuleID:dataTableID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_TABLE_GRID_DATA)
	public CuiGridModelData getBizTableGridData(String args) {
		/*
		 * 转换参数
		 */
		String[] argArr = ActionUtil.decodeArgs(args);

		String moduleID = argArr.length > 0 ? argArr[0] : null;
		String tableID = argArr.length > 1 ? argArr[1] : null;

		Log.debug("CocitBizAction.getBizTableGridData... {args:%s, moduleID:%s, tableID}", args, moduleID, tableID);

		/*
		 * 获取数据模块和数据表对象
		 */
		ComFactory softFactory = Cocit.getComFactory();
		Long mID = Long.parseLong(moduleID);
		CocBizModule module = softFactory.getBizModule(mID);
		CocBizTable table = softFactory.getBizTable(mID, Long.parseLong(tableID));

		Log.debug("CocitBizAction.getBizTableGridData: module=%s, table = %s", module, table);

		/*
		 * 获取数据Grid界面模型
		 */
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiGridModel tableModel = modelFactory.getGridModel(module, table);

		Log.debug("CocitBizAction.getBizTableGridData: tableModel = %s", tableModel);

		/*
		 * 查询GRID数据
		 */
		EntityManagerFactory entityManagerFactory = Cocit.getEntityManagerFactory();
		EntityManager entityManger = entityManagerFactory.getEntityManager(module);

		// 构造查询条件
		CndExpr expr = this.makeExpr();

		List data = entityManger.query(expr, null);
		int total = entityManger.count(expr, null);

		Log.debug("CocitBizAction.getBizTableGridData: tableModel = %s, data.size = %s", tableModel, data == null ? 0 : data.size());

		/*
		 * 构造Grid数据模型
		 */
		CuiGridModelData dataModel = new CuiGridModelData();
		dataModel.setModel(tableModel);
		dataModel.setData(data);
		dataModel.setTotal(total);

		/*
		 * 返回
		 */
		return dataModel;
	}

	private CndExpr makeExpr() {
		CocitHttpContext ctx = Cocit.getHttpContext();
		int pageIndex = ctx.getParameterValue("pageIndex", 1);
		int pageSize = ctx.getParameterValue("pageSize", ActionUtil.PAGE_SIZE);
		CndExpr expr = Expr.page(pageIndex, pageSize);

		// TODO:设置查询条件

		return expr;
	}

	/**
	 * 获取“获取数据表导航树”数据模型，用于输出树所需要的JSON数据。
	 * 
	 * @param args
	 *            加密后的调用参数，参数组成“dataModuleID:dataTableID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_TABLE_NAVI_TREE_DATA)
	public CuiTreeModelData getBizTableNaviTreeData(String args) {
		/*
		 * 转换参数
		 */
		String[] argArr = ActionUtil.decodeArgs(args);

		String moduleID = argArr.length > 0 ? argArr[0] : null;
		String tableID = argArr.length > 1 ? argArr[1] : null;

		Log.debug("CocitBizAction.getBizTableNaviTreeData... {args:%s, moduleID:%s, tableID}", args, moduleID, tableID);

		/*
		 * 获取数据模块和数据表对象
		 */
		ComFactory softFactory = Cocit.getComFactory();
		Long mID = Long.parseLong(moduleID);
		CocBizModule module = softFactory.getBizModule(mID);
		CocBizTable table = softFactory.getBizTable(mID, Long.parseLong(tableID));

		Log.debug("CocitBizAction.getBizTableNaviTreeData: module=%s, table = %s", module, table);

		/*
		 * 获取Tree界面模型
		 */
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiTreeModelData treeModel = modelFactory.getNaviTreeModelData(module, table);

		Log.debug("CocitBizAction.getBizTableNaviTreeData: treeModel = %s", treeModel);

		/*
		 * 返回
		 */
		return treeModel;
	}
}
