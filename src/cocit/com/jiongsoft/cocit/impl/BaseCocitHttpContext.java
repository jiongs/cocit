package com.jiongsoft.cocit.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.cocobj.CobSoft;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

public abstract class BaseCocitHttpContext implements CocitHttpContext {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected CobSoft soft;

	protected BaseCocitHttpContext(HttpServletRequest req, HttpServletResponse res) {
		this.request = req;
		this.response = res;

		String domain = request.getServerName();

		this.soft = Cocit.getCocSoftFactory().getSoft(domain);
	}

	/**
	 * 获取当前HttpServletRequest对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 获取当前HttpServletResponse对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * 获取当前HTTP请求所对应的软件
	 * 
	 * @return 正在通过HTTP请求访问的软件
	 */
	public CobSoft getSoft() {
		return soft;
	}

	/**
	 * 获取软件配置
	 * 
	 * @return
	 */
	public <T> T getSoftConfig(String configKey, T defaultReturn) {
		return soft.getConfig(configKey, defaultReturn);
	}

	@Override
	public <T> T getRequestParam(String key, T defaultReturn) {
		String value = request.getParameter(key);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) value;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.cast(value, valueType);
		} catch (Throwable e) {
			Log.error("StringUtil.getRequestParam: 出错！ {key:%s, defaultReturn:%s, valueType:%s}", key, defaultReturn, valueType.getName());
		}

		return defaultReturn;
	}

	@Override
	public <T> T getRequestProp(String key) {
		return (T) request.getAttribute(key);
	}

	@Override
	public <T> T setRequestProp(String key, T value) {
		request.setAttribute(key, value);

		return value;
	}

	@Override
	public <T> T getSessionProp(String key) {
		return (T) request.getSession().getAttribute(key);
	}

	@Override
	public <T> T setSessionProp(String key, T value) {
		request.getSession().setAttribute(key, value);

		return value;
	}
}
