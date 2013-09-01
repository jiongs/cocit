package com.jiongsoft.cocit.cocui;

import com.jiongsoft.cocit.cocobj.CobDataModule;
import com.jiongsoft.cocit.cocobj.CobDataTable;
import com.jiongsoft.cocit.cocui.model.CuiDataModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiDataTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiMenuModel;
import com.jiongsoft.cocit.cocui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;

/**
 * UI模型工厂：负责创建或管理{@link CuiModel}
 * 
 * @author jiongs753
 * 
 */
public interface CuiModelFactory {

	/**
	 * 获取“数据模块”界面模型
	 * 
	 * @param module
	 * @return
	 */
	CuiDataModuleModel getDataModuleModel(CobDataModule module);

	/**
	 * 获取数据表主界面窗体模型
	 * 
	 * @param module
	 * @return
	 */
	CuiDataTableModel getDataTableModel(CobDataModule module, CobDataTable dataTable);

	/**
	 * 获取数据表Grid模型
	 * 
	 * @param module
	 * @return
	 */
	CuiGridModel getGridModel(CobDataModule module, CobDataTable dataTable);

	/**
	 * 获取数据表搜索框模型
	 * 
	 * @param module
	 * @return
	 */
	CuiSearchBoxModel getSearchBoxModel(CobDataModule module, CobDataTable dataTable);

	/**
	 * 获取数据表操作菜单模型
	 * 
	 * @param module
	 * @return
	 */
	CuiMenuModel getOperationMenuModel(CobDataModule module, CobDataTable dataTable);

	/**
	 * 获取数据表导航树模型
	 * 
	 * @param module
	 * @return
	 */
	CuiTreeModel getNaviTreeModel(CobDataModule module, CobDataTable dataTable);

}
