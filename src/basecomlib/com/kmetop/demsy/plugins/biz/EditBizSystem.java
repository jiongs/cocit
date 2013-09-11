package com.kmetop.demsy.plugins.biz;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.IBizSystem;
import com.kmetop.demsy.lang.Cls;

public class EditBizSystem extends BaseEntityPlugin {

	@Override
	public void before(CocEntityEvent event) {
	}

	@Override
	public void after(CocEntityEvent event) {
		IBizSystem sys = (IBizSystem) event.getEntity();
		if (sys == null) {
			return;
		}

		try {
			String extendClass = Demsy.bizEngine.getExtendClassName(sys);
			Demsy.bizEngine.parseSystemByAnnotation(Cls.forName(extendClass), sys);
		} catch (ClassNotFoundException e) {
			// throw new DemsyException(e);
		}
	}

	@Override
	public void loaded(CocEntityEvent event) {
		// TODO Auto-generated method stub

	}

}
