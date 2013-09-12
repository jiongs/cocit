package com.jiongsoft.cocit.ui.model.widget;

import java.util.List;

import com.jiongsoft.cocit.ui.model.WidgetModel;



/**
 * “数据模块”窗体界面模型：由一个主表界面和多个从表界面组成。
 * 
 * @author yongshan.ji
 * 
 */
public class EntityModuleWidgetModel extends WidgetModel {
	private Long moduleID;
	private String name;

	// 一个主表界面
	private EntityTableWidgetModel entityTableModel;

	// 多个从表界面模型
	private List<EntityTableWidgetModel> childrenEntityTableModels;

	public EntityModuleWidgetModel(EntityTableWidgetModel mainDataTableModel) {
		this.entityTableModel = mainDataTableModel;
	}

	public Long getModuleID() {
		return moduleID;
	}

	public void setModuleID(Long moduleID) {
		this.moduleID = moduleID;
	}

	public EntityTableWidgetModel getEntityTableModel() {
		return entityTableModel;
	}

	public void setEntityTableModel(EntityTableWidgetModel dataTableModel) {
		this.entityTableModel = dataTableModel;
	}

	public List<EntityTableWidgetModel> getChildrenEntityTableModels() {
		return childrenEntityTableModels;
	}

	public void setChildrenEntityTableModels(List<EntityTableWidgetModel> childrenModels) {
		this.childrenEntityTableModels = childrenModels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
