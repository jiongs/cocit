package com.jiongsoft.cocit.cocui.model;

/**
 * “数据表”界面模型：由“左边导航树、右边顶部操作菜单、右边顶部查询栏、右边下部Grid”组成。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiDataTableModel extends BaseCuiModel {

	private Long id;
	private String name;

	// 导航树
	private CuiTreeModel treeModel;

	// 操作菜单
	private CuiMenuModel menuModel;

	// 检索框
	private CuiSearchBoxModel searchBoxModel;

	// Grid
	private CuiGridModel gridModel;

	public void setGrid(CuiGridModel grid) {
		this.gridModel = grid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CuiTreeModel getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(CuiTreeModel naviTreeModel) {
		this.treeModel = naviTreeModel;
	}

	public CuiMenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(CuiMenuModel operationMenuModel) {
		this.menuModel = operationMenuModel;
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

}
