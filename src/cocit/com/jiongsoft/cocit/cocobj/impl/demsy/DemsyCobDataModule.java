package com.jiongsoft.cocit.cocobj.impl.demsy;

import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocobj.CobDataModule;
import com.jiongsoft.cocit.cocobj.CobDataTable;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.impl.base.security.Module;

class DemsyCobDataModule implements CobDataModule {
	private Module entity;

	private CobDataTable mainDataTable;
	private List<CobDataTable> childrenDataTables;

	DemsyCobDataModule(Module e, CobDataTable refrencedDataTable) {
		this.entity = e;
		this.mainDataTable = refrencedDataTable;
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
	public <T> T getExtProp(String propName) {
		return (T) entity.get(propName);
	}

	@Override
	public CobDataTable getMainDataTable() {
		return mainDataTable;
	}

	public List<CobDataTable> getChildrenDataTables() {
		return childrenDataTables;
	}

	public void setChildrenDataTables(List<CobDataTable> childrenDataTables) {
		this.childrenDataTables = childrenDataTables;
	}

}