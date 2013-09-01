package com.jiongsoft.cocit.cocobj.impl.demsy;

import com.jiongsoft.cocit.cocobj.impl.BaseCobSoftConfig;
import com.kmetop.demsy.config.SoftConfigManager;

class DemsyCobSoftConfig extends BaseCobSoftConfig {
	private SoftConfigManager demsyConfigManager;

	DemsyCobSoftConfig(SoftConfigManager manager) {
		demsyConfigManager = manager;
	}

	protected String getStr(String key) {
		return demsyConfigManager.get(key, "");
	}

}
