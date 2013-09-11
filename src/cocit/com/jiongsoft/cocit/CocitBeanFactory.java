package com.jiongsoft.cocit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.utils.ClassUtil;
import com.jiongsoft.cocit.utils.Log;

/**
 * Cocit Bean工厂助理：辅助Cocit平台完成Bean的“创建、缓存”等管理工作。
 * <UL>
 * <LI>配置文件：类路径下的com/jiongssoft/cocit/cocit.json；
 * </UL>
 * 
 * @author jiongs753
 * 
 */
public class CocitBeanFactory {

	private static NutIoc beans;

	static CocitBeanFactory make(ServletContext context) {
		String config = Cocit.class.getName().replace(".", "/").toLowerCase() + ".json";

		Log.info("CocitBeanFactory.init: 加载Cocit平台配置文件...{config:%s}", config);

		beans = new NutIoc(new JsonLoader(config));

		return beans.get(CocitBeanFactory.class);
	}

	// // <softID, CocSoftService>
	// private Map<Long, CocSoftService> softCache;

	/*
	 * 接口实现类全名
	 */
	private String cocitHttpContext;
	private String smsClient_zucp;
	private String smsClient_zr;

	// private String soft;
	// private String softConfig;
	// private String loginUser;

	public CocitBeanFactory() {
		// this.softCache = new Hashtable();
	}

	void clear() {
		// this.softCache.clear();
	}

	<T> T getBean(String name) {
		try {
			return beans.get(null, name);
		} catch (Exception e) {
			Log.warn("CocitBeanFactory.get: 失败! {name:%s}", name, e);
		}

		return null;
	}

	<T> T getBean(Class<T> type) {
		try {
			String name = type.getSimpleName();
			name = name.substring(0, 1).toLowerCase() + name.substring(1);
			return beans.get(null, name);
		} catch (Exception e) {
			Log.warn("CocitBeanFactory.get: 失败! {type:%s}", type, e);
		}

		return null;
	}

	CocitHttpContext makeHttpContext(HttpServletRequest req, HttpServletResponse res) {
		try {
			return ClassUtil.newInstance(cocitHttpContext, req, res);
		} catch (Throwable e) {
			Log.error("CocitBeanFactory.makeHttpContext: 失败! {req:%s, res:%s, cocitHttpContext:%s}", req, res, cocitHttpContext, e);
		}

		return null;
	}

	// CocSoftService makeSoft() {
	// try {
	// return ClassUtil.newInstance(soft);
	// } catch (Throwable e) {
	// Log.error("CocitBeanFactory.makeSoft: 失败! {soft:%s}", soft, e);
	// }
	//
	// return null;
	// }
	//
	// CocConfigService makeSoftConfig() {
	// try {
	// return ClassUtil.newInstance(softConfig);
	// } catch (Throwable e) {
	// Log.error("CocitBeanFactory.makeSoftConfig: 失败! {softConfig:%s}", softConfig, e);
	// }
	//
	// return null;
	// }

	SmsClient makeSmsClient(String type) {

		try {
			if ("zucp".equals(type)) {
				Log.info("CocitBeanFactory.makeSmsClient ... {type:%s, smsClient_zucp:%s}", type, smsClient_zucp);

				return (SmsClient) ClassUtil.newInstance(smsClient_zucp);

			} else if ("zr".equals(type)) {
				Log.info("CocitBeanFactory.makeSmsClient ... {type:%s, smsClient_zr:%s}", type, smsClient_zr);

				return (SmsClient) ClassUtil.newInstance(smsClient_zr);

			} else {
				Log.warn("CocitBeanFactory.makeSmsClient: 指定的短信接口类型不存在！{type:%s}", type);
			}
		} catch (Throwable e) {
			Log.error("CocitBeanFactory.makeSmsClient: 失败! {type:%s}", type, e);
		}

		return null;
	}

	// CocLoginUser makeLoginUser() {
	// try {
	// return ClassUtil.newInstance(loginUser);
	// } catch (Throwable e) {
	// Log.error("CocitBeanFactory.makeLoginUser: 失败! {loginUser:%s}", loginUser, e);
	// }
	//
	// return null;
	// }

	String getCocitHttpContext() {
		return cocitHttpContext;
	}

	String getSmsClient_zucp() {
		return smsClient_zucp;
	}

	String getSmsClient_zr() {
		return smsClient_zr;
	}

	// String getSoft() {
	// return soft;
	// }
	//
	// String getSoftConfig() {
	// return softConfig;
	// }
}
