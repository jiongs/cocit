package com.jiongsoft.cocit.cocobj.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocobj.CobDataField;
import com.jiongsoft.cocit.cocobj.CobDataGroup;
import com.jiongsoft.cocit.cocobj.CobDataOperation;
import com.jiongsoft.cocit.cocobj.CobDataTable;
import com.kmetop.demsy.comlib.impl.base.biz.BizAction;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.impl.sft.system.SFTSystem;
import com.kmetop.demsy.comlib.impl.sft.system.SystemDataGroup;

class DemsyCobDataTable implements CobDataTable {
	private SFTSystem entity;
	private List<SystemDataGroup> dataGroups;
	private List<AbstractSystemData> dataFields;
	private List<AbstractSystemData> dataFieldsForNaviTree;
	private List<AbstractSystemData> dataFieldsForGrid;
	private List<BizAction> dataOperations;

	DemsyCobDataTable(SFTSystem e) {
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
	public List<CobDataGroup> getDataGroups() {
		if (this.dataGroups == null)
			return null;

		List<CobDataGroup> ret = new ArrayList();
		for (SystemDataGroup s : this.dataGroups) {
			ret.add(new DemsyCobDataGroup(s));
		}

		return ret;
	}

	@Override
	public List<CobDataField> getDataFields() {
		if (this.dataFields == null)
			return null;

		List<CobDataField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFields) {
			ret.add(new DemsyCobDataField(s));
		}

		return ret;
	}

	@Override
	public List<CobDataOperation> getDataOperations() {
		if (this.dataOperations == null)
			return null;

		List<CobDataOperation> ret = new ArrayList();
		for (BizAction s : this.dataOperations) {
			ret.add(new DemsyCobDataOperation(s));
		}

		return ret;
	}

	@Override
	public List<CobDataField> getDataFieldsForNaviTree() {
		if (this.dataFieldsForNaviTree == null)
			return null;

		List<CobDataField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFieldsForNaviTree) {
			ret.add(new DemsyCobDataField(s));
		}

		return ret;
	}

	@Override
	public List<CobDataField> getDataFieldsForGrid() {
		if (this.dataFieldsForGrid == null)
			return null;

		List<CobDataField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFieldsForGrid) {
			ret.add(new DemsyCobDataField(s));
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
