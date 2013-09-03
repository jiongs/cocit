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
	 * @param bizModule
	 * @return
	 */
	CuiBizModuleModel getBizModuleModel(CocBizModule bizModule);

	/**
	 * 获取数据表主界面窗体模型
	 * 
	 * @param bizModule
	 * @return
	 */
	CuiBizTableModel getBizTableModel(CocBizModule bizModule, CocBizTable bizTable);

	/**
	 * 获取数据表Grid模型
	 * 
	 * @param bizModule
	 * @return
	 */
	CuiGridModel getGridModel(CocBizModule bizModule, CocBizTable bizTable);

	/**
	 * 获取数据表搜索框模型
	 * 
	 * @param bizModule
	 * @return
	 */
	CuiSearchBoxModel getSearchBoxModel(CocBizModule bizModule, CocBizTable bizTable);

	/**
	 * 获取数据表操作菜单模型
	 * 
	 * @param bizModule
	 * @return
	 */
	CuiMenuModel getOperationMenuModel(CocBizModule bizModule, CocBizTable bizTable);

	/**
	 * 获取数据表导航树模型
	 * 
	 * @param bizModule
	 * @return
	 */
	CuiTreeModel getNaviTreeModel(CocBizModule bizModule, CocBizTable bizTable);

	CuiTreeModelData getNaviTreeModelData(CocBizModule bizModule, CocBizTable bizTable);

}
