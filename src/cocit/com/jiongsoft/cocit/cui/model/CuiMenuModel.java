package com.jiongsoft.cocit.cui.model;

import com.jiongsoft.cocit.utils.TreeNode;

/**
 * CoC工具栏UI模型
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
