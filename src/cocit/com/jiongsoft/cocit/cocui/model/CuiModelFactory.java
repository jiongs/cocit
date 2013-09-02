package com.jiongsoft.cocit.cocui.model;

import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocui.CuiModel;

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
	CuiBizModuleModel getBizModuleModel(CocBizModule module);

	/**
	 * 获取数据表主界面窗体模型
	 * 
	 * @param module
	 * @return
	 */
	CuiBizTableModel getBizTableModel(CocBizModule module, CocBizTable dataTable);

	/**
	 * 获取数据表Grid模型
	 * 
	 * @param module
	 * @return
	 */
	CuiGridModel getGridModel(CocBizModule module, CocBizTable dataTable);

	/**
	 * 获取数据表搜索框模型
	 * 
	 * @param module
	 * @return
	 */
	CuiSearchBoxModel getSearchBoxModel(CocBizModule module, CocBizTable dataTable);

	/**
	 * 获取数据表操作菜单模型
	 * 
	 * @param module
	 * @return
	 */
	CuiMenuModel getOperationMenuModel(CocBizModule module, CocBizTable dataTable);

	/**
	 * 获取数据表导航树模型
	 * 
	 * @param module
	 * @return
	 */
	CuiTreeModel getNaviTreeModel(CocBizModule module, CocBizTable dataTable);

}
