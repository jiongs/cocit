package com.jiongsoft.cocit.cui;

import com.jiongsoft.cocit.coft.CoftModule;
import com.jiongsoft.cocit.cui.model.CuiGridModel;
import com.jiongsoft.cocit.cui.model.CuiMenuModel;
import com.jiongsoft.cocit.cui.model.CuiModuleMainDetailModel;
import com.jiongsoft.cocit.cui.model.CuiModuleModel;
import com.jiongsoft.cocit.cui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.cui.model.CuiTreeModel;

/**
 * UI模型工厂：负责创建或管理{@link CuiModel}
 * 
 * @author jiongs753
 * 
 */
public interface CuiModelFactory {

	/**
	 * 获取模块主界面窗体模型
	 * 
	 * @param module
	 * @return
	 */
	CuiModuleModel getModuleModel(CoftModule module);

	CuiGridModel getGridModel(CoftModule module);

	CuiSearchBoxModel getSearchBoxModel(CoftModule module);

	CuiMenuModel getMenuModel(CoftModule module);

	CuiTreeModel getTreeModel(CoftModule module);

	CuiModuleMainDetailModel getModuleMainDetailsModel(CoftModule module);

}
