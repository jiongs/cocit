package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizGroup;
import com.jiongsoft.cocit.utils.StringUtil;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.impl.sft.system.SystemDataGroup;
import com.kmetop.demsy.engine.BizEngine;

class DemsyCocBizGroup implements CocBizGroup {
	private SystemDataGroup entity;
	private List<AbstractSystemData> dataFields;

	DemsyCocBizGroup(SystemDataGroup e) {
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
	public <T> T get(String propName, T defaultReturn) {
		String value = entity.get(propName);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) value;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.cast(value, valueType);
		} catch (Throwable e) {
		}

		return defaultReturn;
	}

	@Override
	public String getEditMode(String opCode) {

		return ((BizEngine) Demsy.bizEngine).parseMode(opCode, entity.getMode());

	}

	// @Override
	// public CocBizTable getDataTable() {
	// IBizSystem g = entity.getSystem();
	// if (g == null)
	// return null;
	//
	// return fc.makeDataTable(g.getId());
	// }

	@Override
	public List<CocBizField> getBizFields() {

		if (dataFields == null)
			return null;

		List<CocBizField> ret = new ArrayList();

		for (AbstractSystemData f : dataFields) {
			CocBizField cdf = new DemsyCocBizField((AbstractSystemData) f);
			ret.add(cdf);
		}

		return ret;
	}

	public void setDataFields(List<AbstractSystemData> fields) {
		this.dataFields = fields;
	}

	@Override
	public int getSequence() {
		return entity.getOrderby();
	}
}
