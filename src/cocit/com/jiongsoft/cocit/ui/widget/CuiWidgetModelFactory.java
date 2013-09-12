package com.jiongsoft.cocit.ui.widget;

import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityOperationService;
import com.jiongsoft.cocit.service.CocEntityTableService;

/**
 * UI窗体模型工厂：负责创建或管理用户窗体界面
 * 
 * @author jiongs753
 * 
 */
public interface CuiWidgetModelFactory {

	/**
	 * 获取“数据模块”界面模型
	 * 
	 * @param moduleService
	 * @return
	 */
	EntityModuleWidgetModel getEntytyModuleUI(CocEntityModuleService entityModule);

	/**
	 * 获取数据表主界面窗体模型
	 * 
	 * @param moduleService
	 * @return
	 */
	EntityTableWidgetModel getEntityTableUI(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表Grid模型
	 * 
	 * @param moduleService
	 * @return
	 */
	GridWidgetModel getGridUI(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表搜索框模型
	 * 
	 * @param moduleService
	 * @return
	 */
	SearchBoxWidgetModel getSearchBoxUI(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表操作菜单模型
	 * 
	 * @param moduleService
	 * @return
	 */
	MenuWidgetModel getOperationMenuUI(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	/**
	 * 获取数据表导航树模型
	 * 
	 * @param moduleService
	 * @return
	 */
	TreeWidgetModel getEntityNaviUI(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	TreeWidgetData getEntityNaviData(CocEntityModuleService entityModule, CocEntityTableService entityTable);

	EntityFormWidgetModel getEntityFormUI(CocEntityModuleService entityModule, CocEntityTableService entityTable, CocEntityOperationService entityOperation, Object entityData);

}
