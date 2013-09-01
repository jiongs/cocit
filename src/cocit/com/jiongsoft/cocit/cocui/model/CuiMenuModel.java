package com.jiongsoft.cocit.cocui.model;

import com.jiongsoft.cocit.utils.TreeNode;

/**
 * 菜单界面模型：包括菜单属性和菜单所需要的数据。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiMenuModel extends BaseCuiModel {

	private TreeNode data;

	public TreeNode getData() {
		return data;
	}

	public void setData(TreeNode menu) {
		this.data = menu;
	}

}
