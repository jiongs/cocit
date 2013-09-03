package com.jiongsoft.cocit.cocui.model;

import com.jiongsoft.cocit.utils.Tree;

/**
 * 菜单界面模型：包括菜单属性和菜单所需要的数据。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiMenuModel extends BaseCuiModel {

	private Tree data;

	public Tree getData() {
		return data;
	}

	public void setData(Tree menu) {
		this.data = menu;
	}

}
