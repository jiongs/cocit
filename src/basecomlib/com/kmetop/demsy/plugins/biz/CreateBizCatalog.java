package com.kmetop.demsy.plugins.biz;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.IBizCatalog;
import com.kmetop.demsy.orm.IOrm;

public class CreateBizCatalog extends BasePlugin {

	@Override
	public void before(ActionEvent event) {
	}

	@Override
	public void after(ActionEvent event) {
		IOrm orm = (IOrm) event.getOrm();
		IBizCatalog catalog = (IBizCatalog) event.getEntity();

		// 将【业务分类】转换成【文件夹模块】
		Demsy.moduleEngine.makeModule(orm, Demsy.me().getSoft(), catalog);

	}

	@Override
	public void loaded(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
