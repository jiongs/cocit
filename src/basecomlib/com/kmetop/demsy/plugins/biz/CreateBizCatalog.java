package com.kmetop.demsy.plugins.biz;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.IBizCatalog;
import com.kmetop.demsy.orm.IOrm;

public class CreateBizCatalog extends BaseEntityPlugin {

	@Override
	public void before(CocEntityEvent event) {
	}

	@Override
	public void after(CocEntityEvent event) {
		IOrm orm = (IOrm) event.getOrm();
		IBizCatalog catalog = (IBizCatalog) event.getEntityData();

		// 将【业务分类】转换成【文件夹模块】
		Demsy.moduleEngine.makeModule(orm, Demsy.me().getSoft(), catalog);

	}

	@Override
	public void loaded(CocEntityEvent event) {
		// TODO Auto-generated method stub

	}

}
