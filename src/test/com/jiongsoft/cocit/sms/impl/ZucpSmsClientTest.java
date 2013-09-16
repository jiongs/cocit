package com.jiongsoft.cocit.sms.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.ActionContext;
import com.jiongsoft.cocit.service.ConfigManager;
import com.jiongsoft.cocit.service.SoftService;
import com.jiongsoft.cocit.util.CocCalendar;

public class ZucpSmsClientTest {

	@Before
	public void setUp() {
		//
		new Expectations(Cocit.class) {

			@Mocked
			ActionContext softContext;
			@Mocked
			SoftService soft;
			{
				Cocit.getActionContext();
				result = softContext;
				softContext.getSoftService();
				result = soft;

				soft.getConfig(ConfigManager.SMS_PROXY_HOST, "");
				result = "192.168.128.3";
				soft.getConfig(ConfigManager.SMS_PROXY_PORT, 80);
				result = 80;
				soft.getConfig(ConfigManager.SMS_URL, "http://sdk2.zucp.net:8060/webservice.asmx");
				result = "http://sdk2.zucp.net:8060/webservice.asmx";
				soft.getConfig(ConfigManager.SMS_UID, "");
				result = "SDK-BBX-010-18027";
				soft.getConfig(ConfigManager.SMS_PWD, "");
				result = "0b2D5-7D";
			}
		};
	}

	//
	// @Test
	// public void regester() throws UnsupportedEncodingException {
	//
	// SmsClientZucpImpl smsClient = new SmsClientZucpImpl();
	// String ret = smsClient.register("云南", "昆明", "医药", "云南白药", "", "", "", "admin@yunnanbaiyao.com.cn", "", "", "");
	// System.out.println("regester: ret = " + ret);
	//
	// want.string(ret).notNull();
	// want.number(ret.length()).greaterThan(0);
	// want.bool(ret.charAt(0) != '-').is(true);
	// }

	@Test
	public void queryBalance() throws UnsupportedEncodingException {

		ZucpSmsClient smsClient = new ZucpSmsClient();
		String ret = smsClient.queryBalance();
		System.out.println("queryBalance: ret = " + ret);

		assertNotNull(ret);
		assertTrue(ret.length() > 0);
		assertTrue(ret.charAt(0) != '-');
	}

	// @Test
	@Ignore
	public void send() throws UnsupportedEncodingException {
		ZucpSmsClient smsClient = new ZucpSmsClient();
		String rrid = "" + System.currentTimeMillis();
		String ret = smsClient.send("15911731833", "漫道短信测试" + CocCalendar.getNowDateTime(), "", "", rrid);
		System.out.println("send: ret = " + ret);

		assertNotNull(ret);
		assertEquals(rrid, ret);
	}
}