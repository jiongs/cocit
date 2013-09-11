package com.jiongsoft.cocit.ui.widget;


/**
 * “业务表”窗体界面模型：由“左边导航树、右边顶部操作菜单、右边顶部查询栏、右边下部Grid”组成。
 * 
 * @author yongshan.ji
 * 
 */
public class EntityTableWidgetModel extends WidgetModel {

	private String name;

	private String loadUrl;

	// 导航树
	private TreeWidgetModel naviTreeModel;

	// 操作菜单
	private MenuWidgetModel operationMenuModel;

	// 检索框
	private SearchBoxWidgetModel searchBoxModel;

	// Grid
	private GridWidgetModel gridModel;

	public void setGrid(GridWidgetModel grid) {
		this.gridModel = grid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeWidgetModel getNaviTreeModel() {
		return naviTreeModel;
	}

	public void setNaviTreeModel(TreeWidgetModel naviTreeModel) {
		this.naviTreeModel = naviTreeModel;
	}

	public MenuWidgetModel getOperationMenuModel() {
		return operationMenuModel;
	}

	public void setOperationMenuModel(MenuWidgetModel operationMenuModel) {
		this.operationMenuModel = operationMenuModel;
	}

	public SearchBoxWidgetModel getSearchBoxModel() {
		return searchBoxModel;
	}

	public void setSearchBoxModel(SearchBoxWidgetModel searchBoxModel) {
		this.searchBoxModel = searchBoxModel;
	}

	public GridWidgetModel getGridModel() {
		return gridModel;
	}

	public void setGridModel(GridWidgetModel gridModel) {
		this.gridModel = gridModel;
	}

	public String getLoadUrl() {
		return loadUrl;
	}

	public void setLoadUrl(String dataLoadUrl) {
		this.loadUrl = dataLoadUrl;
	}

}
