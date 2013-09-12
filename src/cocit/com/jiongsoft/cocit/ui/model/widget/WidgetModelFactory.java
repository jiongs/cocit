package com.jiongsoft.cocit.ui.model.widget;

import com.jiongsoft.cocit.service.ModuleService;
import com.jiongsoft.cocit.service.EntityOperationService;
import com.jiongsoft.cocit.service.EntityTableService;

/**
 * UI窗体模型工厂：负责创建或管理用户窗体界面
 * 
 * @author jiongs753
 * 
 */
public interface WidgetModelFactory {

	/**
	 * 获取“数据模块”界面模型
	 * 
	 * @param module
	 * @return
	 */
	EntityModuleWidgetModel getEntytyModuleUI(ModuleService module);

	/**
	 * 获取数据表主界面窗体模型
	 * 
	 * @param module
	 * @return
	 */
	EntityTableWidgetModel getEntityTableUI(ModuleService module, EntityTableService table);

	/**
	 * 获取数据表Grid模型
	 * 
	 * @param module
	 * @return
	 */
	GridWidgetModel getGridUI(ModuleService module, EntityTableService table);

	/**
	 * 获取数据表搜索框模型
	 * 
	 * @param module
	 * @return
	 */
	SearchBoxWidgetModel getSearchBoxUI(ModuleService module, EntityTableService table);

	/**
	 * 获取数据表操作菜单模型
	 * 
	 * @param module
	 * @return
	 */
	MenuWidgetModel getOperationMenuUI(ModuleService module, EntityTableService table);

	/**
	 * 获取数据表导航树模型
	 * 
	 * @param module
	 * @return
	 */
	TreeWidgetModel getEntityNaviUI(ModuleService module, EntityTableService table);

	TreeWidgetData getEntityNaviData(ModuleService module, EntityTableService table);

	EntityFormWidgetModel getEntityFormUI(ModuleService module, EntityTableService table, EntityOperationService operation, Object entity);

}
