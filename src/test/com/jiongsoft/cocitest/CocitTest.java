package com.jiongsoft.cocitest;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mockit.Expectations;
import mockit.Mocked;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitBeanFactory;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.coft.Coft;
import com.jiongsoft.cocit.impl.demsy.DemsyCocitHttpContext;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.sms.impl.ZrSmsClient;
import com.jiongsoft.cocit.sms.impl.ZucpSmsClient;
import com.jiongsoft.cocit.utils.Json;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.entity.IDemsySoft;
import com.kmetop.demsy.config.SoftConfigManager;

public class CocitTest {
	MockServletContext context;

	@BeforeMethod
	public void setUp() {
		context = new MockServletContext();
		context.setContextPath("/");

		Cocit.init(context);
	}

	@Test
	public void destroy() {
		Cocit.destroy(context);

		assertNull(Cocit.getContextPath());

		try {
			Cocit.getBean("");
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}

		try {
			Cocit.getHttpContext();
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	public void getContextPath() {
		MockServletContext context = new MockServletContext();

		context.setContextPath(null);
		Cocit.init(context);
		assertEquals("", Cocit.getContextPath());

		context.setContextPath("/");
		Cocit.init(context);
		assertEquals("", Cocit.getContextPath());

		context.setContextPath("");
		Cocit.init(context);
		assertEquals("", Cocit.getContextPath());

		context.setContextPath("/test");
		Cocit.init(context);
		assertEquals("/test", Cocit.getContextPath());

		context.setContextPath("/test/");
		Cocit.init(context);
		assertEquals("/test", Cocit.getContextPath());

		context.setContextPath("test");
		Cocit.init(context);
		assertEquals("/test", Cocit.getContextPath());
	}

	@Test
	public void getBean() {
		Json json = Cocit.getBean(Json.class);
		assertNull(json);

		json = Cocit.getBean("json");
		assertNull(json);

		CocitBeanFactory beanFactory = Cocit.getBean(CocitBeanFactory.class);
		assertNotNull(beanFactory);

		beanFactory = Cocit.getBean("cocitBeanAssist");
		assertNotNull(beanFactory);

		Coft soft = Cocit.getCoft(1L);
		assertNull(soft);

		assertEquals(beanFactory.getCocitHttpContext(), DemsyCocitHttpContext.class.getName());
		assertEquals(beanFactory.getSmsClient_zr(), ZrSmsClient.class.getName());
		assertEquals(beanFactory.getSmsClient_zucp(), ZucpSmsClient.class.getName());
	}

	@Test
	public void makeAndGetHttpContext() {
		final Long softID = 1L;
		final HttpServletRequest req = new MockHttpServletRequest();
		final HttpServletResponse res = new MockHttpServletResponse();
		new Expectations(Demsy.class, SoftConfigManager.class) {
			@Mocked
			Demsy mockDemsy;
			@Mocked
			IDemsySoft mockDemsySoft;
			@Mocked
			SoftConfigManager mockDemsyConfig;
			{
				Demsy.me();
				result = null;
				Demsy.initMe(req, res);
				result = mockDemsy;
				mockDemsy.getSoft();
				result = mockDemsySoft;
				mockDemsySoft.getId();
				result = softID;
				SoftConfigManager.me();
				result = mockDemsyConfig;
				mockDemsyConfig.get("sms.type", "");
				result = "zr";
				mockDemsyConfig.get(SmsClient.CFG_PROXY_HOST, "");
				result = "192.168.128.3";
				mockDemsyConfig.get(SmsClient.CFG_PROXY_PORT, "");
				result = "80";
				mockDemsyConfig.get(SmsClient.CFG_URL, "");
				result = "http://oa.zrsms.com";
				mockDemsyConfig.get(SmsClient.CFG_UID, "");
				result = "zlsandi";
				mockDemsyConfig.get(SmsClient.CFG_PWD, "");
				result = "zlsandi";
			}
		};

		CocitHttpContext ctx = Cocit.initHttpContext(req, res);
		assertNotNull(ctx);

		ctx = Cocit.getHttpContext();
		assertNotNull(ctx);

		assertNotNull(ctx.getCoft());

		assertNotNull(ctx.getCoft().getSmsClient());

		assertNotNull(ctx.getCoft().getSmsClient() instanceof ZrSmsClient);
	}
}
