package com.jiongsoft.cocit.cui.model;

/**
 * CoC模块模型：CoC模块模型由“左边导航树、右边顶部操作菜单、右边顶部查询栏、右边下部Grid数据”等多部分组成。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiModuleModel extends BaseCuiModel {

	private Long id;
	private String name;

	// 导航树
	private CuiTreeModel tree;

	// 操作菜单
	private CuiMenuModel menu;

	// 检索框
	private CuiSearchBoxModel searchBox;

	// Grid
	private CuiGridModel grid;

	public CuiTreeModel getTree() {
		return tree;
	}

	public void setTree(CuiTreeModel tree) {
		this.tree = tree;
	}

	public CuiMenuModel getMenu() {
		return menu;
	}

	public void setMenu(CuiMenuModel menu) {
		this.menu = menu;
	}

	public CuiSearchBoxModel getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(CuiSearchBoxModel searchBox) {
		this.searchBox = searchBox;
	}

	public CuiGridModel getGrid() {
		return grid;
	}

	public void setGrid(CuiGridModel grid) {
		this.grid = grid;
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
}
