package com.jiongsoft.cocit.cocui.model;

import com.jiongsoft.cocit.utils.TreeNode;

/**
 * 树界面模型：可以包含树所需要的数据，如果数据不存在则表示将异步获取JSON格式的树型数据。
 * 
 * @author jiongsoft
 * 
 */
public class CuiTreeModel extends BaseCuiModel {

	// Tree数据，如果该值为Null，则将通过AJAX方式加载树数据。
	private TreeNode data;

	// Grid数据“增、删、查、改”操作的URL地址
	private String dataLoadUrl;
	private String dataDeleteUrl;
	private String dataEditUrl;
	private String dataAddUrl;

	public String getDataLoadUrl() {
		return dataLoadUrl;
	}

	public void setDataLoadUrl(String dataLoadUrl) {
		this.dataLoadUrl = dataLoadUrl;
	}

	public String getDataDeleteUrl() {
		return dataDeleteUrl;
	}

	public void setDataDeleteUrl(String dataDeleteUrl) {
		this.dataDeleteUrl = dataDeleteUrl;
	}

	public String getDataEditUrl() {
		return dataEditUrl;
	}

	public void setDataEditUrl(String dataEditUrl) {
		this.dataEditUrl = dataEditUrl;
	}

	public String getDataAddUrl() {
		return dataAddUrl;
	}

	public void setDataAddUrl(String dataUpdateUrl) {
		this.dataAddUrl = dataUpdateUrl;
	}

	public TreeNode getData() {
		return data;
	}

	public void setData(TreeNode data) {
		this.data = data;
	}

}
