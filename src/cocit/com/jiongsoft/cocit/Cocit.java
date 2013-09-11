package com.jiongsoft.cocit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.orm.CormFactory;
import com.jiongsoft.cocit.service.CocServiceFactory;
import com.jiongsoft.cocit.service.CocSoftService;
import com.jiongsoft.cocit.service.CocEntityManagerFactory;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.ui.CuiRenderFactory;
import com.jiongsoft.cocit.ui.model.CuiModelFactory;
import com.jiongsoft.cocit.utils.Log;

/**
 * Cocit：组件化自定义平台，也称“CoC平台”。
 * <P>
 * <b>Cocit含义：</b>
 * <UL>
 * <LI>组件化自定义信息技术：Componentization of custom information technology；
 * <LI>组件化自定义它：Componentization of custom it，“it”包括“软件、系统、报表、流程、操作、展现视图、网站、权限 ......”；
 * <LI>全称“组件化自定义平台”，简称“自定义平台”、“CoC平台”、“CoC”等；
 * </UL>
 * <p>
 * <b>功能说明：</b>
 * <UL>
 * <LI>调用该类的静态方法之前，必须先调用{@link #init(ServletContext)}方法对CoC平台进行初始化；
 * <LI>通过该类的静态方法可以获得Cocit平台中的其他管理接口；
 * </UL>
 * <b>名词解释：</b>
 * <UL>
 * <LI>Cocit: 组件化自定义平台（Componentization of custom IT），也称“CoC平台”；
 * <LI>Coc: 组件化自定义
 * <LI>cocsoft: 组件化自定义软件（Componentization of custom software）；
 * <LI>cocui(Cui): 组件化自定义界面（Componentization of custom UI），也称“CoC界面”;
 * </UL>
 * 
 * @author jiongs753
 * 
 */
public abstract class Cocit {

	private static String contextPath;

	private static ThreadLocal<CocitHttpContext> httpContext;

	private static CocitBeanFactory beanFactory;

	/**
	 * 初始化CoC平台
	 * 
	 * @param context
	 */
	public static void init(ServletContext context) {
		Log.info("Cocit.init......");

		// init contextPath
		contextPath = context.getContextPath().trim();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		if (contextPath.length() > 0 && contextPath.charAt(0) != '/') {
			contextPath = "/" + contextPath;
		}

		// init httpContext
		httpContext = new ThreadLocal<CocitHttpContext>();

		// init beanAssist
		beanFactory = CocitBeanFactory.make(context);

		Log.info("Cocit.init: end! {contextPath: %s, beanFactory: %s}", contextPath, beanFactory);
	}

	/**
	 * 释放CoC平台
	 * 
	 * @param context
	 */
	public static void destroy(ServletContext context) {
		beanFactory.clear();

		contextPath = null;
		beanFactory = null;
		httpContext = null;
	}

	/**
	 * 获取Servlet环境路径，如果路径长度大于1则以/开头，否则路径为空串。
	 * 
	 * @return
	 */
	public static String getContextPath() {
		return contextPath;
	}

	/**
	 * 初始化HTTP环境，即初始化当前请求的运行环境。
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public static CocitHttpContext initHttpContext(HttpServletRequest req, HttpServletResponse res) {
		CocitHttpContext ret = beanFactory.makeHttpContext(req, res);

		if (httpContext == null)
			return ret;

		httpContext.remove();
		httpContext.set(ret);

		Log.debug("Cocit.initHttpContext: ret = %s", ret);

		return ret;
	}

	/**
	 * 获取当前请求的HTTP环境。该方法只有在调用过{@link #initHttpContext(HttpServletRequest, HttpServletResponse)}之后才会有返回值，否则返回null。
	 * 
	 * @return
	 */
	public static CocitHttpContext getHttpContext() {
		if (httpContext == null)
			return null;

		return httpContext.get();
	}

	/**
	 * 获取配置中的Bean对象
	 * 
	 * @param name
	 *            Bean名称
	 * @return
	 */
	public static <T> T getBean(String name) {
		return beanFactory.getBean(name);
	}

	/**
	 * 获取平台配置中的Bean对象
	 * 
	 * @param type
	 *            Bean类
	 * @return
	 */
	public static <T> T getBean(Class<T> type) {
		return beanFactory.getBean(type);
	}

	/**
	 * 根据指定的类型创建一个短信客户端接口对象，该方法被通常被{@link CocSoftService}调用，且每个{@link CocSoftService}对象只会调用该方法一次来创建短信第三方接口对象，之后将缓存在{@link CocSoftService}对象中。
	 * 
	 * @param type
	 * @return 返回一个新建的短信客户端接口。
	 */
	public static SmsClient makeSmsClient(String type) {
		return beanFactory.makeSmsClient(type);
	}

	// /**
	// * 创建一个软件对象
	// * <p>
	// * 通常供CocitHttpContext构造函数调用，用来构造一个软件对象。
	// */
	// public static CocSoftService makeSoft() {
	// return beanFactory.makeSoft();
	// }
	//
	// /**
	// * 创建CoC软件配置助手
	// *
	// * @return 返回一个全新的CoC软件配置助手实例对象
	// */
	// public static CocConfigService makeSoftConfig() {
	// return beanFactory.makeSoftConfig();
	// }

	/**
	 * 获取CoC组工厂。
	 * 
	 * @return 返回已被缓存的CoC组件库{@link CocServiceFactory}的单例对象。
	 */
	public static CocServiceFactory getServiceFactory() {
		return beanFactory.getBean(CocServiceFactory.class);
	}

	/**
	 * 获取CoC UI模型工厂
	 * 
	 * @return 返回已被缓存的CoC UI模型工厂{@link CuiModelFactory}的单例对象。
	 */
	public static CuiModelFactory getUIModelFactory() {
		return beanFactory.getBean(CuiModelFactory.class);
	}

	/**
	 * 获取CoC UIRender 工厂
	 * 
	 * @return 返回已被缓存的CoC UI Render工厂{@link CuiRenderFactory}的单例对象。
	 */
	public static CuiRenderFactory getUIRenderFactory() {
		return beanFactory.getBean(CuiRenderFactory.class);
	}

	public static CormFactory getOrmFactory() {
		return beanFactory.getBean(CormFactory.class);
	}

	public static CocEntityManagerFactory getEntityManagerFactory() {
		return beanFactory.getBean(CocEntityManagerFactory.class);
	}

}
