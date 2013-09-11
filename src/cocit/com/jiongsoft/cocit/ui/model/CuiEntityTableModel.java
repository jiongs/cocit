package com.jiongsoft.cocit.ui.model;

/**
 * “业务表”界面模型：由“左边导航树、右边顶部操作菜单、右边顶部查询栏、右边下部Grid”组成。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiEntityTableModel extends BaseCuiModel {

	private String name;

	private String loadUrl;

	// 导航树
	private CuiTreeModel naviTreeModel;

	// 操作菜单
	private CuiMenuModel operationMenuModel;

	// 检索框
	private CuiSearchBoxModel searchBoxModel;

	// Grid
	private CuiGridModel gridModel;

	public void setGrid(CuiGridModel grid) {
		this.gridModel = grid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CuiTreeModel getNaviTreeModel() {
		return naviTreeModel;
	}

	public void setNaviTreeModel(CuiTreeModel naviTreeModel) {
		this.naviTreeModel = naviTreeModel;
	}

	public CuiMenuModel getOperationMenuModel() {
		return operationMenuModel;
	}

	public void setOperationMenuModel(CuiMenuModel operationMenuModel) {
		this.operationMenuModel = operationMenuModel;
	}

	public CuiSearchBoxModel getSearchBoxModel() {
		return searchBoxModel;
	}

	public void setSearchBoxModel(CuiSearchBoxModel searchBoxModel) {
		this.searchBoxModel = searchBoxModel;
	}

	public CuiGridModel getGridModel() {
		return gridModel;
	}

	public void setGridModel(CuiGridModel gridModel) {
		this.gridModel = gridModel;
	}

	public String getLoadUrl() {
		return loadUrl;
	}

	public void setLoadUrl(String dataLoadUrl) {
		this.loadUrl = dataLoadUrl;
	}

}
