package com.jiongsoft.cocit.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.coft.Coft;

public abstract class BaseCocitHttpContext implements CocitHttpContext {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Coft soft;

	protected BaseCocitHttpContext(HttpServletRequest req, HttpServletResponse res) {
		this.request = req;
		this.response = res;

		//
		soft = Cocit.getCoft(getCoftID());
	}

	protected abstract Long getCoftID();

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
	public Coft getCoft() {
		return soft;
	}

	/**
	 * 获取软件配置
	 * 
	 * @return
	 */
	public <T> T getCoftConfig(String configKey, T defaultReturn) {
		return soft.getConfig(configKey, defaultReturn);
	}

}
