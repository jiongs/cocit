package com.kmetop.demsy.plugins.lib;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.kmetop.demsy.config.SoftConfigManager;

public class ClearSoftConfigCache extends BasePlugin {

	@Override
	public void after(ActionEvent event) {
		SoftConfigManager.clearCache();
	}

}
