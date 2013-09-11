package com.kmetop.demsy.plugins.security;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.kmetop.demsy.comlib.entity.base.BaseUser;

public class SaveStaffUser extends SaveUser {

	@Override
	public void before(CocEntityEvent event) {
		super.before(event);

		BaseUser user = (BaseUser) event.getEntity();
		user.setDisabled(true);
	}
}
