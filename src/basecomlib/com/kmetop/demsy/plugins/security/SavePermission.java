package com.kmetop.demsy.plugins.security;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.kmetop.demsy.Demsy;

public class SavePermission extends BaseEntityPlugin {

	@Override
	public void before(CocEntityEvent event) {
	}

	@Override
	public void after(CocEntityEvent event) {
		Demsy.security.clearPermissions();
	}

	@Override
	public void loaded(CocEntityEvent event) {
		// TODO Auto-generated method stub

	}

}
