package com.kmetop.demsy.plugins.security;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.LibConst;
import com.kmetop.demsy.comlib.entity.base.BaseUser;
import com.kmetop.demsy.lang.DemsyException;
import com.kmetop.demsy.orm.IOrm;

public class SaveUser extends BaseEntityPlugin {

	@Override
	public void before(CocEntityEvent event) {
		BaseUser user = (BaseUser) event.getEntity();
		String code = user.getCode();
		IOrm orm = (IOrm) event.getOrm();
		int count = orm.count(user.getClass(), Expr.eq(LibConst.F_SOFT_ID, Demsy.me().getSoft()).and(Expr.eq(LibConst.F_CODE, code)));
		if (count > 0) {
			throw new DemsyException("登录帐号已被使用，请重新填写登录帐号!");
		}
	}

	@Override
	public void after(CocEntityEvent event) {
	}

	@Override
	public void loaded(CocEntityEvent event) {
		// TODO Auto-generated method stub

	}

}
