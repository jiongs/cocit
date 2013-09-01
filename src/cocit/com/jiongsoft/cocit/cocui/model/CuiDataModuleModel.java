package com.jiongsoft.cocit.cocui.model;

import java.util.List;

/**
 * “数据模块”界面模型：由一个主表界面和多个从表界面组成。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiDataModuleModel extends BaseCuiModel {
	private Long moduleID;

	// 一个主表界面
	private CuiDataTableModel mainDataTableModel;

	// 多个从表界面模型
	private List<CuiDataTableModel> childrenDataTableModels;

	public CuiDataModuleModel(CuiDataTableModel mainDataTableModel) {
		this.mainDataTableModel = mainDataTableModel;
	}

	public Long getModuleID() {
		return moduleID;
	}

	public void setModuleID(Long moduleID) {
		this.moduleID = moduleID;
	}

	public CuiDataTableModel getMainDataTableModel() {
		return mainDataTableModel;
	}

	public void setMainDataTableModel(CuiDataTableModel dataTableModel) {
		this.mainDataTableModel = dataTableModel;
	}

	public List<CuiDataTableModel> getChildrenDataTableModels() {
		return childrenDataTableModels;
	}

	public void setChildrenDataTableModels(List<CuiDataTableModel> childrenModels) {
		this.childrenDataTableModels = childrenModels;
	}
}
