package com.jiongsoft.cocit.coction;

import java.util.List;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.cocobj.CobDataModule;
import com.jiongsoft.cocit.cocobj.CobDataTable;
import com.jiongsoft.cocit.cocobj.CocSoftFactory;
import com.jiongsoft.cocit.cocui.CuiModelFactory;
import com.jiongsoft.cocit.cocui.CuiModelView;
import com.jiongsoft.cocit.cocui.CuiPaths;
import com.jiongsoft.cocit.cocui.model.CuiDataModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiDataTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModelData;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;
import com.jiongsoft.cocit.utils.TreeNode;

/**
 * 数据模块Action：负责处理业务数据的“增加、删除、查询、修改、导入、导出”等业务操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
public class CoctionData {

	/**
	 * 获取“数据模块”界面模型，用于输出数据模块的界面。
	 * 
	 * @param args
	 *            Hex加密后的调用参数，参数组成“dataModuleID”
	 * @return
	 */
	@At(CuiPaths.DATA_MODULE)
	public CuiDataModuleModel module(String args) {
		// 转换模块参数
		String moduleID = CuiPaths.decodeArgs(args);

		Log.debug("CoctionData.module... {args:%s, moduleID:%s}", args, moduleID);

		// 获取数据模块对象
		CocSoftFactory softFactory = Cocit.getCocSoftFactory();
		CobDataModule module = softFactory.getDataModule(Long.parseLong(moduleID));

		Log.debug("CoctionData.module: module = %s", module);

		// 获取模块界面模型
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiDataModuleModel moduleModel = modelFactory.getDataModuleModel(module);

		Log.debug("CoctionData.module: moduleModel = %s", moduleModel);

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
	@At(CuiPaths.DATA_TABLE)
	public CuiDataTableModel table(String args) {
		// 获取数据表参数
		String argSrc = CuiPaths.decodeArgs(args);
		String[] argArr = StringUtil.toArray(argSrc, ":");

		String moduleID = argArr.length > 0 ? argArr[0] : null;
		String tableID = argArr.length > 1 ? argArr[1] : null;

		Log.debug("CoctionData.table... {args:%s, moduleID:%s, tableID}", args, moduleID, tableID);

		// 获取数据模块和数据表对象
		CocSoftFactory softFactory = Cocit.getCocSoftFactory();
		Long mID = Long.parseLong(moduleID);
		CobDataModule module = softFactory.getDataModule(mID);
		CobDataTable table = softFactory.getDataTable(mID, Long.parseLong(tableID));

		Log.debug("CoctionData.table: module=%s, table = %s", module, table);

		// 获取数据表界面模型
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiDataTableModel tableModel = modelFactory.getDataTableModel(module, table);

		Log.debug("CoctionData.module: tableModel = %s", tableModel);

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
	@At(CuiPaths.DATA_GRIDDATA)
	public CuiGridModelData gridData(String args) {
		// 转换参数
		String argSrc = CuiPaths.decodeArgs(args);
		String[] argArr = StringUtil.toArray(argSrc, ":");

		String moduleID = argArr.length > 0 ? argArr[0] : null;
		String tableID = argArr.length > 1 ? argArr[1] : null;

		Log.debug("CoctionData.gridData... {args:%s, moduleID:%s, tableID}", args, moduleID, tableID);

		// 获取数据模块和数据表对象
		CocSoftFactory softFactory = Cocit.getCocSoftFactory();
		Long mID = Long.parseLong(moduleID);
		CobDataModule module = softFactory.getDataModule(mID);
		CobDataTable table = softFactory.getDataTable(mID, Long.parseLong(tableID));

		Log.debug("CoctionData.gridData: module=%s, table = %s", module, table);

		// 获取数据Grid界面模型
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiGridModel tableModel = modelFactory.getGridModel(module, table);

		Log.debug("CoctionData.gridData: tableModel = %s", tableModel);

		// TOTO: 查询GRID数据
		List data = null;

		Log.debug("CoctionData.gridData: tableModel = %s, data.size = %s", tableModel, data == null ? 0 : data.size());

		// 构造Grid数据模型
		CuiGridModelData dataModel = new CuiGridModelData();
		dataModel.setModel(tableModel);
		dataModel.setData(data);

		// 返回
		return dataModel;
	}

	/**
	 * 获取“获取数据表导航树”数据模型，用于输出树所需要的JSON数据。
	 * 
	 * @param args
	 *            加密后的调用参数，参数组成“dataModuleID:dataTableID”
	 * @return
	 */
	@At(CuiPaths.DATA_NAVITREEDATA)
	public CuiTreeModelData naviTreeData(String args) {
		// 转换参数
		String argSrc = CuiPaths.decodeArgs(args);
		String[] argArr = StringUtil.toArray(argSrc, ":");

		String moduleID = argArr.length > 0 ? argArr[0] : null;
		String tableID = argArr.length > 1 ? argArr[1] : null;

		Log.debug("CoctionData.naviTreeData... {args:%s, moduleID:%s, tableID}", args, moduleID, tableID);

		// 获取数据模块和数据表对象
		CocSoftFactory softFactory = Cocit.getCocSoftFactory();
		Long mID = Long.parseLong(moduleID);
		CobDataModule module = softFactory.getDataModule(mID);
		CobDataTable table = softFactory.getDataTable(mID, Long.parseLong(tableID));

		Log.debug("CoctionData.naviTreeData: module=%s, table = %s", module, table);

		// 获取Tree界面模型
		CuiModelFactory modelFactory = Cocit.getCuiModelFactory();
		CuiTreeModel treeModel = modelFactory.getNaviTreeModel(module, table);

		Log.debug("CoctionData.naviTreeData: treeModel = %s", treeModel);

		// TOTO: 查询NaviTree数据
		TreeNode data = new TreeNode();

		Log.debug("CoctionData.naviTreeData: treeModel = %s, data = %s", treeModel, data);

		// 构造Tree数据模型
		CuiTreeModelData dataModel = new CuiTreeModelData();
		dataModel.setModel(treeModel);
		dataModel.setData(data);

		// 返回
		return dataModel;
	}
}
