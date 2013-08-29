package com.jiongsoft.cocit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import com.jiongsoft.cocit.CocitBeanFactory;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.utils.ClassUtil;
import com.jiongsoft.cocit.utils.Json;
import com.jiongsoft.cocit.utils.json.JsonImpl;

public class CocitBeanFactoryTest {
	private CocitBeanFactory beanFactory;

	@Before
	public void setUp() throws Exception {
		ServletContext context = new MockServletContext();
		beanFactory = CocitBeanFactory.make(context);
	}

	@After
	public void tearDown() throws Exception {
		beanFactory = null;
	}

	@Test
	public void testInit() throws Exception {
		assertNotNull(beanFactory);
	}

	@Test
	public void testClear() throws Exception {
		beanFactory.clear();
	}

	@Test
	public void testGet() throws Exception {
		Json result = beanFactory.getBean((String) null);
		assertNull(result);

		result = beanFactory.getBean("");
		assertNull(result);

		result = beanFactory.getBean((Class) null);
		assertNull(result);

		JsonImpl jsonImpl = beanFactory.getBean(JsonImpl.class);
		assertNull(jsonImpl);

		jsonImpl = beanFactory.getBean("jsonImpl");
		assertNull(jsonImpl);

		result = beanFactory.getBean(Json.class);
		assertNull(result);

		result = beanFactory.getBean("json");
		assertNull(result);

		CocitBeanFactory factory = beanFactory.getBean(CocitBeanFactory.class);
		assertNotNull(factory);

		factory = beanFactory.getBean("cocitBeanFactory");
		assertNotNull(factory);
	}

	// @Test
	// public void testMakeSoft() throws Throwable {
	// final Long softID = new Long(1L);
	// // new NonStrictExpectations(ClassUtil.class) {
	// new Expectations(ClassUtil.class) {
	// @Mocked
	// CoudSoft mockObj;
	// {
	// ClassUtil.newInstance("com.jiongsoft.cocit.impl.CoudSoftImpl", softID);
	// result = mockObj;
	// ClassUtil.newInstance("com.jiongsoft.cocit.impl.CoudSoftImpl", softID);
	// result = new Exception();
	// }
	// };
	//
	// CoudSoft result = cocitBeanAssist.makeSoft(softID);
	// assertNotNull(result);
	//
	// result = cocitBeanAssist.makeSoft(softID);
	// assertNull(result);
	// }
	//
	// @Test
	// public void testGetSoft() throws Throwable {
	// final Long softID = new Long(1L);
	//
	// new Expectations(ClassUtil.class) {
	// @Mocked
	// CoudSoft mockObj;
	// {
	// ClassUtil.newInstance("com.jiongsoft.cocit.impl.CoudSoftImpl", softID);
	// result = mockObj;
	// }
	// };
	//
	// CoudSoft result = cocitBeanAssist.getSoft(softID);
	// assertNotNull(result);
	//
	// result = cocitBeanAssist.getSoft(softID);
	// assertNotNull(result);
	// }

	//
	// @Test
	// public void testGetSoft_isNull() throws Throwable {
	// final Long softID = new Long(1L);
	//
	// new Expectations(ClassUtil.class) {
	// {
	// ClassUtil.newInstance("com.jiongsoft.cocit.impl.CoudSoft", softID);
	// result = null;
	// }
	// };
	//
	// CoudSoft result = cocitBeanAssist.getSoft(softID);
	// assertNull(result);
	//
	// }

	@Test
	public void testMakeSmsClient() throws Throwable {

		new Expectations(ClassUtil.class) {
			@Mocked
			SmsClient mockObj;
			{
				ClassUtil.newInstance("com.jiongsoft.cocit.sms.impl.ZucpSmsClient");
				result = mockObj;
				ClassUtil.newInstance("com.jiongsoft.cocit.sms.impl.ZrSmsClient");
				result = mockObj;
				ClassUtil.newInstance("com.jiongsoft.cocit.sms.impl.ZucpSmsClient");
				result = new Exception();
			}
		};

		SmsClient result = beanFactory.makeSmsClient("zucp");
		assertNotNull(result);

		result = beanFactory.makeSmsClient("zr");
		assertNotNull(result);

		result = beanFactory.makeSmsClient("zucp");
		assertNull(result);
	}

	@Test
	public void testMakeSmsClient_nullType() throws Exception {
		final String type = null;

		SmsClient result = beanFactory.makeSmsClient(type);
		assertNull(result);
	}

	// @Test
	// public void testMakeSoftConfig() throws Throwable {
	//
	// new Expectations(ClassUtil.class) {
	// @Mocked
	// CoudSoftConfigAssist mockObj;
	// {
	// ClassUtil.newInstance("com.jiongsoft.cocit.config.impl.demsy.DemsyCoudSoftConfig");
	// result = mockObj;
	//
	// ClassUtil.newInstance("com.jiongsoft.cocit.config.impl.demsy.DemsyCoudSoftConfig");
	// result = new Exception();
	// }
	// };
	//
	// CoudSoftConfigAssist result = cocitBeanAssist.makeSoftConfigAssist();
	// assertNotNull(result);
	//
	// result = cocitBeanAssist.makeSoftConfigAssist();
	// assertNull(result);
	// }

	@Test
	public void testMakeHttpContext() throws Throwable {
		final HttpServletRequest req = new MockHttpServletRequest();
		final HttpServletResponse res = new MockHttpServletResponse();

		new Expectations(ClassUtil.class) {
			@Mocked
			CocitHttpContext mockObj;
			{
				ClassUtil.newInstance("com.jiongsoft.cocit.impl.demsy.DemsyCocitHttpContext", req, res);
				result = mockObj;

				ClassUtil.newInstance("com.jiongsoft.cocit.impl.demsy.DemsyCocitHttpContext", req, res);
				result = new Exception();
			}
		};

		CocitHttpContext result = beanFactory.makeHttpContext(req, res);
		assertNotNull(result);

		result = beanFactory.makeHttpContext(req, res);
		assertNull(result);
	}

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}