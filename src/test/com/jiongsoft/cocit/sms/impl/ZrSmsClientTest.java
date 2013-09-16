package com.jiongsoft.cocit.sms.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.List;

import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.ActionContext;
import com.jiongsoft.cocit.service.ConfigManager;
import com.jiongsoft.cocit.service.SoftService;
import com.jiongsoft.cocit.sms.impl.ZrSmsClient;
import com.jiongsoft.cocit.util.CocCalendar;

public class ZrSmsClientTest {

	@Before
	public void setUp() {
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
				soft.getConfig(ConfigManager.SMS_URL, "http://oa.zrsms.com");
				result = "http://oa.zrsms.com";
				soft.getConfig(ConfigManager.SMS_UID, "");
				result = "zlsandi";
				soft.getConfig(ConfigManager.SMS_PWD, "");
				result = "zlsandi";
			}
		};
	}

	private void mockHttpURLConnection(final ZrSmsClient smsClient, final String returnResult) {

		new NonStrictExpectations(smsClient) {
			@Mocked
			HttpURLConnection mockConn;
			{
				invoke(smsClient, "createConnection", anyString);
				result = mockConn;
				try {
					mockConn.setRequestMethod(anyString);
				} catch (ProtocolException e) {
				}
				mockConn.setRequestProperty(anyString, anyString);
				invoke(smsClient, "getResult", mockConn);
				result = returnResult;
				mockConn.disconnect();
			}
		};
	}

	private void mockNullConnection(final ZrSmsClient smsClient) {

		new Expectations(smsClient) {
			{
				invoke(smsClient, "createConnection", anyString);
				result = null;
			}
		};
	}

	@Test
	public void queryBalance() throws UnsupportedEncodingException {
		ZrSmsClient smsClient = new ZrSmsClient();

		mockHttpURLConnection(smsClient, "您的余额为100！");
		String ret = smsClient.queryBalance();
		assertEquals("您的余额为100！", ret);

		this.mockNullConnection(smsClient);
		ret = smsClient.queryBalance();
		assertNull(ret);
	}

	@Test
	public void queryBalance_integration() throws UnsupportedEncodingException {
		ZrSmsClient smsClient = new ZrSmsClient();

		String ret = smsClient.queryBalance();
		assertNotNull(ret);
	}

	@Test
	public void send() {
		ZrSmsClient smsClient = new ZrSmsClient();

		mockHttpURLConnection(smsClient, "0发送成功!");
		String ret = smsClient.send("15911731833", "短信测试", "", "", "");
		assertEquals("0发送成功!", ret);

		this.mockNullConnection(smsClient);
		ret = smsClient.send("15911731833", "短信测试", "", "", "");
		assertNull(ret);

	}

	@Ignore
	// @Test
	public void send_integration() {
		ZrSmsClient smsClient = new ZrSmsClient();

		String ret = smsClient.send("15911731833", "展仁短信测试" + CocCalendar.getNowDateTime(), "", "", "");
		assertNotNull(ret);
	}

	@Test
	public void receive() {
		ZrSmsClient smsClient = new ZrSmsClient();

		mockHttpURLConnection(smsClient, "接收短信成功!");
		List ret = smsClient.receive();
		assertNotNull(ret);

		this.mockNullConnection(smsClient);
		ret = smsClient.receive();
		assertNull(ret);

	}

	@Test
	public void receive_integration() {
		ZrSmsClient smsClient = new ZrSmsClient();

		List ret = smsClient.receive();
		assertNotNull(ret);
	}
}