package com.jiongsoft.cocit.ui.model;

import java.util.List;


/**
 * “数据模块”界面模型：由一个主表界面和多个从表界面组成。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiEntityModuleModel extends BaseCuiModel {
	private Long moduleID;
	private String name;

	// 一个主表界面
	private CuiEntityTableModel entityTableModel;

	// 多个从表界面模型
	private List<CuiEntityTableModel> childrenEntityTableModels;

	public CuiEntityModuleModel(CuiEntityTableModel mainDataTableModel) {
		this.entityTableModel = mainDataTableModel;
	}

	public Long getModuleID() {
		return moduleID;
	}

	public void setModuleID(Long moduleID) {
		this.moduleID = moduleID;
	}

	public CuiEntityTableModel getEntityTableModel() {
		return entityTableModel;
	}

	public void setEntityTableModel(CuiEntityTableModel dataTableModel) {
		this.entityTableModel = dataTableModel;
	}

	public List<CuiEntityTableModel> getChildrenEntityTableModels() {
		return childrenEntityTableModels;
	}

	public void setChildrenEntityTableModels(List<CuiEntityTableModel> childrenModels) {
		this.childrenEntityTableModels = childrenModels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
