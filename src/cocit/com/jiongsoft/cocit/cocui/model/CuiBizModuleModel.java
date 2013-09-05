package com.jiongsoft.cocit.cocui.model;

import java.util.List;


/**
 * “数据模块”界面模型：由一个主表界面和多个从表界面组成。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiBizModuleModel extends BaseCuiModel {
	private Long moduleID;
	private String name;

	// 一个主表界面
	private CuiBizTableModel mainBizTableModel;

	// 多个从表界面模型
	private List<CuiBizTableModel> childrenBizTableModels;

	public CuiBizModuleModel(CuiBizTableModel mainDataTableModel) {
		this.mainBizTableModel = mainDataTableModel;
	}

	public Long getModuleID() {
		return moduleID;
	}

	public void setModuleID(Long moduleID) {
		this.moduleID = moduleID;
	}

	public CuiBizTableModel getMainBizTableModel() {
		return mainBizTableModel;
	}

	public void setMainBizTableModel(CuiBizTableModel dataTableModel) {
		this.mainBizTableModel = dataTableModel;
	}

	public List<CuiBizTableModel> getChildrenBizTableModels() {
		return childrenBizTableModels;
	}

	public void setChildrenBizTableModels(List<CuiBizTableModel> childrenModels) {
		this.childrenBizTableModels = childrenModels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
