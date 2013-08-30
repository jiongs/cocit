package com.jiongsoft.cocit.cui.impl.demsy;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.coft.CoftModule;
import com.jiongsoft.cocit.cui.CuiModelFactory;
import com.jiongsoft.cocit.cui.model.CuiGridModel;
import com.jiongsoft.cocit.cui.model.CuiMenuModel;
import com.jiongsoft.cocit.cui.model.CuiModuleMainDetailModel;
import com.jiongsoft.cocit.cui.model.CuiModuleModel;
import com.jiongsoft.cocit.cui.model.CuiModulesTabsModel;
import com.jiongsoft.cocit.cui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.cui.model.CuiTreeModel;
import com.jiongsoft.cocit.utils.TreeNode;

public class DemsyCuiModelFactory implements CuiModelFactory {

	@Override
	public CuiModuleModel getModuleModel(CoftModule module) {
		CuiModuleModel model = new CuiModuleModel();
		model.setId(module.getID());
		model.setName(module.getName());
		model.setTree(this.getTreeModel(module));
		model.setMenu(this.getMenuModel(module));
		model.setGrid(this.getGridModel(module));
		model.setSearchBox(this.getSearchBoxModel(module));

		return model;
	}

	@Override
	public CuiSearchBoxModel getSearchBoxModel(CoftModule module) {
		return new CuiSearchBoxModel();
	}

	@Override
	public CuiGridModel getGridModel(CoftModule module) {
		CuiGridModel grid = new CuiGridModel();

		// TODO 创建Grid字段列

		return grid;
	}

	@Override
	public CuiMenuModel getMenuModel(CoftModule module) {
		CuiMenuModel menu = new CuiMenuModel();

		TreeNode treeData = new TreeNode();

		// TODO 创建模块操作菜单树型数据

		menu.setData(treeData);

		return menu;
	}

	@Override
	public CuiTreeModel getTreeModel(CoftModule module) {
		CuiTreeModel tree = new CuiTreeModel();

		TreeNode treeData = new TreeNode();

		// TODO 创建模块导航树型数据

		tree.setData(treeData);

		return tree;
	}

	@Override
	public CuiModuleMainDetailModel getModuleMainDetailsModel(CoftModule module) {

		// 创建主模块模型
		List<CuiModuleModel> mainList = new ArrayList();
		mainList.add(this.getModuleModel(module));
		CuiModulesTabsModel main = null;
		if (mainList.size() > 0)
			main = new CuiModulesTabsModel(mainList);

		// 创建从模块模型
		List<CuiModuleModel> detailList = new ArrayList();
		List<CoftModule> children = module.getChildren();
		for (CoftModule child : children) {
			detailList.add(this.getModuleModel(child));
		}
		CuiModulesTabsModel detail = null;
		if (detailList.size() > 0)
			detail = new CuiModulesTabsModel(detailList);

		// 创建主从结构模块模型
		return new CuiModuleMainDetailModel(main, detail);
	}
}
