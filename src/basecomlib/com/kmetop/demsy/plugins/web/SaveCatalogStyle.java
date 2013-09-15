package com.kmetop.demsy.plugins.web;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.LibConst;
import com.kmetop.demsy.comlib.impl.base.web.UiCatalogStyle;
import com.kmetop.demsy.lang.DemsyException;
import com.kmetop.demsy.orm.IOrm;

public class SaveCatalogStyle extends BasePlugin {

	@Override
	public void before(ActionEvent event) {
		UiCatalogStyle style = (UiCatalogStyle) event.getEntity();
		String name = style.getName();
		IOrm orm = (IOrm) event.getOrm();
		int count = orm.count(style.getClass(), Expr.eq(LibConst.F_SOFT_ID, Demsy.me().getSoft()).and(Expr.eq(LibConst.F_NAME, name)));
		if (count > 1) {
			throw new DemsyException("样式名称已被使用，请重新填写样式名称!");
		} else if (count == 1) {
			if (style.getId() == null || style.getId() <= 0) {
				throw new DemsyException("样式名称已被使用，请重新填写样式名称!");
			}
		}
	}

	@Override
	public void after(ActionEvent event) {

	}

	@Override
	public void loaded(ActionEvent event) {

	}

}
