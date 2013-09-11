package com.jiongsoft.cocit.ui.model;

import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityOperationService;
import com.jiongsoft.cocit.service.CocEntityTableService;
import com.jiongsoft.cocit.ui.CuiModel;

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
	 * @param entityModule
	 * @return
	 */
	CuiEntityModuleModel getEntytyModuleUI(CocEntityModuleService entityModule);

	/**
	 * 获取数据表主界面窗体模型
	 * 
	 * @param entityModule
	 * @return
	 */
	CuiEntityTableModel getEntityTableUI(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表Grid模型
	 * 
	 * @param entityModule
	 * @return
	 */
	CuiGridModel getGridModel(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表搜索框模型
	 * 
	 * @param entityModule
	 * @return
	 */
	CuiSearchBoxModel getSearchBoxModel(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表操作菜单模型
	 * 
	 * @param entityModule
	 * @return
	 */
	CuiMenuModel getOperationMenuModel(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表导航树模型
	 * 
	 * @param entityModule
	 * @return
	 */
	CuiTreeModel getEntityNaviUI(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	CuiTreeModelData getEntityNaviData(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	CuiFormModel getEntityFormUI(CocEntityModuleService entityModule, CocEntityTableService entityTable, CocEntityOperationService entityOperation, Object entityData);

}
