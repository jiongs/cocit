package com.jiongsoft.cocit.cocsoft.impl.demsy;

import com.jiongsoft.cocit.cocsoft.impl.BaseCocSoftConfig;
import com.kmetop.demsy.config.SoftConfigManager;

class DemsyCocSoftConfig extends BaseCocSoftConfig {
	private SoftConfigManager demsyConfigManager;

	DemsyCocSoftConfig(SoftConfigManager manager) {
		demsyConfigManager = manager;
	}

	protected String getStr(String key) {
		return demsyConfigManager.get(key, "");
	}

}
