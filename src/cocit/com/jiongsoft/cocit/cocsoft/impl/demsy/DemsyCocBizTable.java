package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizGroup;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.kmetop.demsy.comlib.impl.base.biz.BizAction;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.impl.sft.system.SFTSystem;
import com.kmetop.demsy.comlib.impl.sft.system.SystemDataGroup;

class DemsyCocBizTable implements CocBizTable {
	private SFTSystem entity;
	private List<SystemDataGroup> dataGroups;
	private List<AbstractSystemData> dataFields;
	private List<AbstractSystemData> dataFieldsForNaviTree;
	private List<AbstractSystemData> dataFieldsForGrid;
	private List<BizAction> dataOperations;

	DemsyCocBizTable(SFTSystem e) {
		this.entity = e;
	}

	@Override
	public Long getID() {
		return entity.getId();
	}

	@Override
	public String getName() {
		return entity.getName();
	}

	@Override
	public boolean isDisabled() {
		return entity.isDisabled();
	}

	@Override
	public String getInfo() {
		return entity.getDesc();
	}

	@Override
	public Date getCreatedDate() {
		return entity.getCreated();
	}

	@Override
	public String getCreatedUser() {
		return entity.getCreatedBy();
	}

	@Override
	public Date getLatestModifiedDate() {
		return entity.getUpdated();
	}

	@Override
	public String getLatestModifiedUser() {
		return entity.getUpdatedBy();
	}

	@Override
	public <T> T getExtProp(String propName) {
		return (T) entity.get(propName);
	}

	@Override
	public List<CocBizGroup> getBizGroups() {
		if (this.dataGroups == null)
			return null;

		List<CocBizGroup> ret = new ArrayList();
		for (SystemDataGroup s : this.dataGroups) {
			ret.add(new DemsyCocBizGroup(s));
		}

		return ret;
	}

	@Override
	public List<CocBizField> getBizFields() {
		if (this.dataFields == null)
			return null;

		List<CocBizField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFields) {
			ret.add(new DemsyCocBizField(s));
		}

		return ret;
	}

	@Override
	public List<CocBizOperation> getBizOperations() {
		if (this.dataOperations == null)
			return null;

		List<CocBizOperation> ret = new ArrayList();
		for (BizAction s : this.dataOperations) {
			ret.add(new DemsyCocBizOperation(s));
		}

		return ret;
	}

	@Override
	public List<CocBizField> getBizFieldsForNaviTree() {
		if (this.dataFieldsForNaviTree == null)
			return null;

		List<CocBizField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFieldsForNaviTree) {
			ret.add(new DemsyCocBizField(s));
		}

		return ret;
	}

	@Override
	public List<CocBizField> getBizFieldsForGrid() {
		if (this.dataFieldsForGrid == null)
			return null;

		List<CocBizField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFieldsForGrid) {
			ret.add(new DemsyCocBizField(s));
		}

		return ret;
	}

	public void setDataGroups(List<SystemDataGroup> dataGroups) {
		this.dataGroups = dataGroups;
	}

	public void setDataFields(List<AbstractSystemData> dataFields) {
		this.dataFields = dataFields;
	}

	public void setDataFieldsForNaviTree(List<AbstractSystemData> dataFieldsForNaviTree) {
		this.dataFieldsForNaviTree = dataFieldsForNaviTree;
	}

	public void setDataOperations(List<BizAction> dataOperations) {
		this.dataOperations = dataOperations;
	}

	public void setDataFieldsForGrid(List<AbstractSystemData> dataFieldsForGrid) {
		this.dataFieldsForGrid = dataFieldsForGrid;
	}

}
