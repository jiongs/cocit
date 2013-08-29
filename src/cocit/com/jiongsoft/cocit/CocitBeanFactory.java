package com.jiongsoft.cocit;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.jiongsoft.cocit.coft.CoftConfig;
import com.jiongsoft.cocit.coft.Coft;
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

	// <softID, JSoft>
	private Map<Long, Coft> coftCache;

	/*
	 * 接口实现类全名
	 */
	private String cocitHttpContext;
	private String smsClient_zucp;
	private String smsClient_zr;
	private String coft;
	private String coftConfig;

	public CocitBeanFactory() {
		this.coftCache = new Hashtable();
	}

	void clear() {
		this.coftCache.clear();
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
			return beans.get(type);
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

	private Coft makeCoft(Long softID) {
		try {
			return ClassUtil.newInstance(coft, softID);
		} catch (Throwable e) {
			Log.error("CocitBeanFactory.makeCoft: 失败! {coft:%s}", coft, e);
		}

		return null;
	}

	CoftConfig makeCoftConfig() {
		try {
			return ClassUtil.newInstance(coftConfig);
		} catch (Throwable e) {
			Log.error("CocitBeanFactory.makeCoftConfig: 失败! {coftConfig:%s}", coftConfig, e);
		}

		return null;
	}

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

	Coft getCoft(Long softID) {
		Coft soft = coftCache.get(softID);
		if (soft == null) {
			soft = this.makeCoft(softID);
		}

		return soft;
	}

	public String getCocitHttpContext() {
		return cocitHttpContext;
	}

	public String getSmsClient_zucp() {
		return smsClient_zucp;
	}

	public String getSmsClient_zr() {
		return smsClient_zr;
	}

	public String getCoft() {
		return coft;
	}

	public String getCoftConfig() {
		return coftConfig;
	}
}
