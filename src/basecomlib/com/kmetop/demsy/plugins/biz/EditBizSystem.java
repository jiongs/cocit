package com.kmetop.demsy.plugins.biz;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.IBizSystem;
import com.kmetop.demsy.lang.Cls;

@Deprecated
public class EditBizSystem extends BasePlugin {

	@Override
	public void before(ActionEvent event) {
	}

	@Override
	public void after(ActionEvent event) {
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
	public void loaded(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
