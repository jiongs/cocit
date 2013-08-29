package com.jiongsoft.cocit.coft.impl.demsy;

import com.jiongsoft.cocit.coft.impl.BaseCoftConfig;
import com.kmetop.demsy.config.SoftConfigManager;

public class DemsyCoftConfig extends BaseCoftConfig {
	private SoftConfigManager manager;

	DemsyCoftConfig() {
		manager = SoftConfigManager.me();
	}

	protected String getStr(String key) {
		return manager.get(key, "");
	}

}
