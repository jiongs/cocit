package com.jiongsoft.cocit.cocobj.impl;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.cocobj.CobSoft;
import com.jiongsoft.cocit.cocobj.CobSoftConfig;
import com.jiongsoft.cocit.sms.SmsClient;

public abstract class BaseCobSoft implements CobSoft {

	/*
	 * lazy load the following properties
	 */
	protected CobSoftConfig config;
	protected SmsClient smsClient;

	@Override
	public SmsClient getSmsClient() {
		if (smsClient == null) {
			String type = getConfig(SmsClient.CFG_TYPE, "");
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

	protected abstract CobSoftConfig getSoftConfig();
}
