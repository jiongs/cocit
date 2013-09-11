package com.jiongsoft.cocit.ui.widget;

import com.jiongsoft.cocit.utils.Tree;

/**
 * 菜单窗体界面模型：包括菜单属性和菜单所需要的数据。
 * 
 * @author yongshan.ji
 * 
 */
public class MenuWidgetModel extends WidgetModel {

	private SearchBoxWidgetModel searchBoxModel;
	
	private Tree data;

	public Tree getData() {
		return data;
	}

	public void setData(Tree menu) {
		this.data = menu;
	}

	public SearchBoxWidgetModel getSearchBoxModel() {
		return searchBoxModel;
	}

	public void setSearchBoxModel(SearchBoxWidgetModel searchBoxModel) {
		this.searchBoxModel = searchBoxModel;
	}

}
