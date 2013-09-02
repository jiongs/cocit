package com.jiongsoft.cocit.sms.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.cocsoft.CocSoft;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.sms.impl.EmaySDKSmsClient;
import com.jiongsoft.cocit.utils.DateUtil;

public class EmaySDKSmsClientTest {
	@Test
	public void testEmaySmsClient_1() throws Exception {

		EmaySDKSmsClient result = new EmaySDKSmsClient();

		assertNotNull(result);
	}

	@Test
	public void testQueryBalance_1() throws Exception {
		EmaySDKSmsClient fixture = new EmaySDKSmsClient();

		String result = fixture.queryBalance();

		assertNotNull(result);
	}

	@Test
	public void testReceive_1() throws Exception {
		EmaySDKSmsClient fixture = new EmaySDKSmsClient();

		List<String[]> result = fixture.receive();

		assertNotNull(result);
	}

	@Ignore
	public void testSend_1() throws Exception {
		EmaySDKSmsClient fixture = new EmaySDKSmsClient();
		String mobiles = "15911731833";
		String content = "亿美短信测试" + DateUtil.getCurrentDateTime();
		String extCode = "";
		String time = "";
		String rrid = "";

		String result = fixture.send(mobiles, content, extCode, time, rrid);

		assertNotNull(result);
	}

	@Before
	public void setUp() throws Exception {
		new Expectations(Cocit.class) {
			@Mocked
			CocitHttpContext softContext;
			@Mocked
			CocSoft soft;
			{
				Cocit.getHttpContext();
				result = softContext;
				softContext.getSoft();
				result = soft;

				soft.getConfig(SmsClient.CFG_PROXY_HOST, "");
				result = "192.168.128.3";
				soft.getConfig(SmsClient.CFG_PROXY_PORT, 80);
				result = 80;

				/*
				 * 员工网帐号
				 */
				// soft.getConfig(SmsClient.CFG_UID, "");
				// result = "3SDK-KYJ-0130-KJXQT";
				// soft.getConfig(SmsClient.CFG_PWD, "");
				// result = "257330";
				// soft.getConfig("sms.key", "");
				// result = "147088";

				/*
				 * 茶缘帐号
				 */
				soft.getConfig(SmsClient.CFG_UID, "");
				result = "3SDK-KYJ-0130-KJXQL";
				soft.getConfig(SmsClient.CFG_PWD, "");
				result = "356860";
				soft.getConfig("sms.key", "");
				result = "147080";
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}
}