package com.jiongsoft.cocit.service.impl;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.service.ConfigService;
import com.jiongsoft.cocit.service.SoftService;
import com.jiongsoft.cocit.sms.SmsClient;

public abstract class BaseSoftService implements SoftService {

	/*
	 * lazy load the following properties
	 */
	protected ConfigService config;
	protected SmsClient smsClient;

	@Override
	public SmsClient getSmsClient() {
		if (smsClient == null) {
			String type = getConfig(ConfigService.CFG_TYPE, "");
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

	protected abstract ConfigService getSoftConfig();
}
