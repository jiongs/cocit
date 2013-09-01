package com.jiongsoft.cocit.cocobj.impl.demsy;

import java.util.Date;

import com.jiongsoft.cocit.cocobj.CobSoftConfig;
import com.jiongsoft.cocit.cocobj.impl.BaseCobSoft;
import com.kmetop.demsy.comlib.entity.IDemsySoft;
import com.kmetop.demsy.config.SoftConfigManager;

class DemsyCobSoft extends BaseCobSoft {
	private IDemsySoft entity;
	private DemsyCobSoftConfig config;

	DemsyCobSoft(IDemsySoft demsySoft) {
		this.entity = demsySoft;

		config = new DemsyCobSoftConfig(SoftConfigManager.me());
	}

	@Override
	protected CobSoftConfig getSoftConfig() {
		return config;
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

}
