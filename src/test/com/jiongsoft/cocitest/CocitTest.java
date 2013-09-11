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
import com.jiongsoft.cocit.service.CocConfigService;
import com.jiongsoft.cocit.sms.impl.ZrSmsClient;
import com.jiongsoft.cocit.utils.Json;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.base.lib.DemsySoft;
import com.kmetop.demsy.config.SoftConfigManager;
import com.kmetop.demsy.engine.BizEngine;
import com.kmetop.demsy.engine.ModuleEngine;

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

		assertNull(Cocit.getHttpContext());
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

		beanFactory = Cocit.getBean("cocitBeanFactory");
		assertNotNull(beanFactory);

		assertNotNull(Cocit.getServiceFactory());
		assertNotNull(Cocit.getUIModelFactory());
		assertNotNull(Cocit.getUIRenderFactory());
		assertNotNull(Cocit.getEntityManagerFactory());
		assertNull(Cocit.getOrmFactory());

	}

	@Test
	public void makeAndGetHttpContext() {
		final HttpServletRequest req = new MockHttpServletRequest();
		final HttpServletResponse res = new MockHttpServletResponse();
		new Expectations(Demsy.class, SoftConfigManager.class) {
			@Mocked
			DemsySoft mockDemsySoft;
			@Mocked
			BizEngine bizEngine;
			@Mocked
			ModuleEngine moduleEngine;
			@Mocked
			SoftConfigManager mockDemsyConfig;
			{
				Demsy.bizEngine = bizEngine;
				Demsy.moduleEngine = moduleEngine;
				moduleEngine.getSoft(anyString);
				result = mockDemsySoft;
				SoftConfigManager.me();
				result = mockDemsyConfig;
				mockDemsyConfig.get("sms.type", "");
				result = "zr";
				mockDemsyConfig.get(CocConfigService.CFG_PROXY_HOST, "");
				result = "192.168.128.3";
				mockDemsyConfig.get(CocConfigService.CFG_PROXY_PORT, "");
				result = "80";
				mockDemsyConfig.get(CocConfigService.CFG_URL, "");
				result = "http://oa.zrsms.com";
				mockDemsyConfig.get(CocConfigService.CFG_UID, "");
				result = "zlsandi";
				mockDemsyConfig.get(CocConfigService.CFG_PWD, "");
				result = "zlsandi";
			}
		};

		CocitHttpContext ctx = Cocit.initHttpContext(req, res);
		assertNotNull(ctx);

		ctx = Cocit.getHttpContext();
		assertNotNull(ctx);

		assertNotNull(ctx.getSoft());

		assertNotNull(ctx.getSoft().getSmsClient());

		assertNotNull(ctx.getSoft().getSmsClient() instanceof ZrSmsClient);
	}
}
