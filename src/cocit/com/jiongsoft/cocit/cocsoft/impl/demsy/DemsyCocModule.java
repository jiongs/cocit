package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.Date;

import com.jiongsoft.cocit.cocsoft.CocModule;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.security.IModule;

class DemsyCocModule implements CocModule {

	private IModule entity;

	DemsyCocModule(IModule e) {
		this.entity = e;
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

	// @Override
	// public CocFolderModule getParent() {
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
