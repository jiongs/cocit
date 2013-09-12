package com.kmetop.demsy.plugins.security;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.impl.BaseActionPlugin;
import com.kmetop.demsy.Demsy;

public class SavePermission extends BaseActionPlugin {

	@Override
	public void before(ActionEvent event) {
	}

	@Override
	public void after(ActionEvent event) {
		Demsy.security.clearPermissions();
	}

	@Override
	public void loaded(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
