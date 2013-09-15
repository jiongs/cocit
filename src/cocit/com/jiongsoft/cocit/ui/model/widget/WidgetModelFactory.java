package com.jiongsoft.cocit.ui.model.widget;

import com.jiongsoft.cocit.service.ModuleService;
import com.jiongsoft.cocit.service.TableService;

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
	 * @param moduleID
	 * @return
	 */
	EntityModuleWidgetModel getEntytyModuleUI(ModuleService module);

	/**
	 * 获取数据表主界面窗体模型
	 * 
	 * @param moduleID
	 * @return
	 */
	EntityTableWidgetModel getEntityTableUI(ModuleService module, TableService table);

	/**
	 * 获取数据表Grid模型
	 * 
	 * @param moduleID
	 * @return
	 */
	GridWidgetModel getGridUI(ModuleService module, TableService table);

	/**
	 * 获取数据表搜索框模型
	 * 
	 * @param moduleID
	 * @return
	 */
	SearchBoxWidgetModel getSearchBoxUI(ModuleService module, TableService table);

	/**
	 * 获取数据表操作菜单模型
	 * 
	 * @param moduleID
	 * @return
	 */
	MenuWidgetModel getOperationMenuUI(ModuleService module, TableService table);

	/**
	 * 获取数据表导航树模型
	 * 
	 * @param moduleID
	 * @return
	 */
	TreeWidgetModel getEntityNaviUI(ModuleService module, TableService table);

	TreeWidgetData getEntityNaviData(ModuleService module, TableService table);

	EntityFormWidgetModel getEntityFormUI(ModuleService module, TableService table, String opMode, Object entity);

}
