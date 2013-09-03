package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.impl.base.security.Module;

class DemsyCocBizModule implements CocBizModule {
	private Module entity;

	private CocBizTable mainDataTable;
	private List<CocBizTable> childrenDataTables;

	DemsyCocBizModule(Module e, CocBizTable refrencedDataTable) {
		this.entity = e;
		this.mainDataTable = refrencedDataTable;
	}

	@Override
	public boolean is(String propName) {
		Object obj = entity.get(propName);
		if (obj == null)
			return false;

		try {
			return Boolean.valueOf(obj.toString());
		} catch (Throwable e) {
			return false;
		}
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
	public int getSequence() {
		return entity.getOrderby();
	}

	@Override
	public String getLogo() {
		Upload u = entity.getLogo();
		return u == null ? null : u.toString();
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
	public <T> T get(String propName) {
		return (T) entity.get(propName);
	}

	@Override
	public CocBizTable getMainBizTable() {
		return mainDataTable;
	}

	public List<CocBizTable> getChildrenBizTables() {
		return childrenDataTables;
	}

	public void setChildrenDataTables(List<CocBizTable> childrenDataTables) {
		this.childrenDataTables = childrenDataTables;
	}

	public Module getEntity() {
		return entity;
	}

}