package com.kmetop.demsy.plugins.lib;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.impl.BaseActionPlugin;
import com.kmetop.demsy.config.SoftConfigManager;

public class ClearSoftConfigCache extends BaseActionPlugin {

	@Override
	public void after(ActionEvent event) {
		SoftConfigManager.clearCache();
	}

}
