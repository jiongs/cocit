package com.jiongsoft.cocit.cocsoft.impl;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.cocsoft.CocSoft;
import com.jiongsoft.cocit.cocsoft.CocSoftConfig;
import com.jiongsoft.cocit.sms.SmsClient;

public abstract class BaseCocSoft implements CocSoft {

	/*
	 * lazy load the following properties
	 */
	protected CocSoftConfig config;
	protected SmsClient smsClient;

	@Override
	public SmsClient getSmsClient() {
		if (smsClient == null) {
			String type = getConfig(CocSoftConfig.CFG_TYPE, "");
			smsClient = Cocit.makeSmsClient(type);
		}

		return smsClient;
	}

	@Override
	public <T> T getConfig(String configKey, T defaultReturn) {
		// initialize config
		if (config == null)
			config = getSoftConfig();

		return config.get(configKey, defaultReturn);
	}

	protected abstract CocSoftConfig getSoftConfig();
}
