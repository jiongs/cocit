package com.jiongsoft.cocit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.coft.CoftConfig;
import com.jiongsoft.cocit.coft.CoduleFactory;
import com.jiongsoft.cocit.coft.Coft;
import com.jiongsoft.cocit.cui.CuiModelFactory;
import com.jiongsoft.cocit.sms.SmsClient;

/**
 * Cocit：组件化自定义平台。
 * <P>
 * <b>Cocit含义：</b>
 * <UL>
 * <LI>组件化自定义信息技术：Componentization of custom information technology；
 * <LI>组件化自定义它：Componentization of custom it，“it”包括“软件、系统、报表、流程、操作、展现视图、网站、权限 ......”；
 * <LI>全称“组件化自定义平台”，简称“自定义平台”、“CoC平台”、“Cocit平台”、“Cocit”、“CoC”等；
 * </UL>
 * <p>
 * <b>功能说明：</b>
 * <UL>
 * <LI>调用该类的静态方法之前，必须先调用{@link #init(ServletContext)}方法对CoC平台进行初始化；
 * <LI>通过该类的静态方法可以获得Cocit平台中的其他管理接口；
 * </UL>
 * <b>名词解释：</b>
 * <UL>
 * <LI>Cocit: 组件化自定义平台（Componentization of custom IT）；
 * <LI>Coft: 组件化自定义软件（Componentization of custom software）；
 * <LI>Codule: 组件化自定义模块（Componentization of custom module）；
 * <LI>Cui: 组件化自定义界面（Componentization of custom UI）;
 * </UL>
 * 
 * @author jiongs753
 * 
 */
public abstract class Cocit {

	private static String contextPath;

	private static ThreadLocal<CocitHttpContext> httpContext;

	private static CocitBeanFactory beanFactory;

	public static void init(ServletContext context) {
		// init contextPath
		contextPath = context.getContextPath().trim();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		if (contextPath.length() > 0 && !contextPath.startsWith("/")) {
			contextPath = "/" + contextPath;
		}

		// init httpContext
		httpContext = new ThreadLocal<CocitHttpContext>();

		// init beanAssist
		beanFactory = CocitBeanFactory.make(context);
	}

	public static void destroy(ServletContext context) {
		beanFactory.clear();

		contextPath = null;
		beanFactory = null;
		httpContext = null;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static CocitHttpContext initHttpContext(HttpServletRequest req, HttpServletResponse res) {
		CocitHttpContext ret = beanFactory.makeHttpContext(req, res);

		if (httpContext == null)
			return ret;

		httpContext.remove();
		httpContext.set(ret);

		return ret;
	}

	public static CocitHttpContext getHttpContext() {
		if (httpContext == null)
			return null;

		return httpContext.get();
	}

	public static <T> T getBean(String name) {
		return beanFactory.getBean(name);
	}

	public static <T> T getBean(Class<T> type) {
		return beanFactory.getBean(type);
	}

	public static SmsClient makeSmsClient(String type) {
		return beanFactory.makeSmsClient(type);
	}

	public static Coft getCoft(Long softID) {
		return beanFactory.getCoft(softID);
	}

	public static CoftConfig makeCoftConfig() {
		return beanFactory.makeCoftConfig();
	}

	public static CoduleFactory getCoduleFactory() {
		return beanFactory.getBean(CoduleFactory.class);
	}

	public static CuiModelFactory getCuiModelFactory() {
		return beanFactory.getBean(CuiModelFactory.class);
	}

}
