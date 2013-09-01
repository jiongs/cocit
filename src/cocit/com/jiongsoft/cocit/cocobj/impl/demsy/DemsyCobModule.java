package com.jiongsoft.cocit.cocobj.impl.demsy;

import java.util.Date;

import com.jiongsoft.cocit.cocobj.CobModule;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.security.IModule;

class DemsyCobModule implements CobModule {

	private IModule entity;

	DemsyCobModule(IModule e) {
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

	// @Override
	// public CobFolderModule getParent() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public String getLogo() {
		Upload u = entity.getLogo();
		if (u == null)
			return null;

		return u.toString();
	}

}
