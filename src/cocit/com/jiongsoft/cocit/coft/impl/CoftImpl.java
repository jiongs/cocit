package com.jiongsoft.cocit.coft.impl;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.coft.CoftConfig;
import com.jiongsoft.cocit.coft.Coft;
import com.jiongsoft.cocit.sms.SmsClient;

public class CoftImpl implements Coft {

	protected Long softID;

	/*
	 * lazy load the following properties
	 */
	protected CoftConfig config;
	protected SmsClient smsClient;

	public CoftImpl(Long softID) {
		this.softID = softID;
	}

	@Override
	public Long getCoftID() {
		return softID;
	}

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
			config = Cocit.makeCoftConfig();

		return config.get(configKey, defaultReturn);
	}
}
