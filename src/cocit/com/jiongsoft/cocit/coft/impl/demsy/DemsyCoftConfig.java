package com.jiongsoft.cocit.coft.impl.demsy;

import com.jiongsoft.cocit.coft.impl.BaseCoftConfig;
import com.kmetop.demsy.config.SoftConfigManager;

public class DemsyCoftConfig extends BaseCoftConfig {
	private SoftConfigManager demsyConfigManager;

	DemsyCoftConfig() {
		demsyConfigManager = SoftConfigManager.me();
	}

	protected String getStr(String key) {
		return demsyConfigManager.get(key, "");
	}

}
