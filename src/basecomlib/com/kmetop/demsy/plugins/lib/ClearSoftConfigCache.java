package com.kmetop.demsy.plugins.lib;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.kmetop.demsy.config.SoftConfigManager;

public class ClearSoftConfigCache extends BaseEntityPlugin {

	@Override
	public void after(CocEntityEvent event) {
		SoftConfigManager.clearCache();
	}

}
