package com.jiongsoft.cocit.cocobj.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocobj.CobDataField;
import com.jiongsoft.cocit.cocobj.CobDataGroup;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.impl.sft.system.SystemDataGroup;
import com.kmetop.demsy.engine.BizEngine;

class DemsyCobDataGroup implements CobDataGroup {
	private SystemDataGroup entity;
	private List<AbstractSystemData> dataFields;

	DemsyCobDataGroup(SystemDataGroup e) {
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
	public String getEditMode(String opCode) {

		return ((BizEngine) Demsy.bizEngine).parseMode(opCode, entity.getMode());

	}

	// @Override
	// public CobDataTable getDataTable() {
	// IBizSystem g = entity.getSystem();
	// if (g == null)
	// return null;
	//
	// return fc.makeDataTable(g.getId());
	// }

	@Override
	public List<CobDataField> getDataFields() {

		if (dataFields == null)
			return null;

		List<CobDataField> ret = new ArrayList();

		for (AbstractSystemData f : dataFields) {
			CobDataField cdf = new DemsyCobDataField((AbstractSystemData) f);
			ret.add(cdf);
		}

		return ret;
	}

	public void setDataFields(List<AbstractSystemData> fields) {
		this.dataFields = fields;
	}

}
