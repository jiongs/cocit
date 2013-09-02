package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.Date;

import com.jiongsoft.cocit.cocsoft.CocSoftConfig;
import com.jiongsoft.cocit.cocsoft.impl.BaseCocSoft;
import com.kmetop.demsy.comlib.entity.IDemsySoft;
import com.kmetop.demsy.config.SoftConfigManager;

class DemsyCocSoft extends BaseCocSoft {
	private IDemsySoft entity;
	private DemsyCocSoftConfig config;

	DemsyCocSoft(IDemsySoft demsySoft) {
		this.entity = demsySoft;

		config = new DemsyCocSoftConfig(SoftConfigManager.me());
	}

	@Override
	protected CocSoftConfig getSoftConfig() {
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
