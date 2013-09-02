package com.jiongsoft.cocit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.cocsoft.CocSoft;

/**
 * Cocit HTTP环境：用来管理HTTP请求的一次生命周期。
 * 
 * @author jiongs753
 * 
 */
public interface CocitHttpContext {

	/**
	 * 获取当前HttpServletRequest对象
	 * 
	 * @return
	 */
	HttpServletRequest getRequest();

	/**
	 * 获取当前HttpServletResponse对象
	 * 
	 * @return
	 */
	HttpServletResponse getResponse();

	/**
	 * 获取当前HTTP请求所对应的软件
	 * 
	 * @return 正在通过HTTP请求访问的软件
	 */
	CocSoft getSoft();

	/**
	 * 获取软件配置
	 * 
	 * @return
	 */
	<T> T getSoftConfig(String configKey, T defaultReturn);

	// /**
	// * 获取当前登录用户
	// *
	// * @return
	// */
	// CocLoginUser getLoginUser();

	String[] getParameterValues(String key);

	<T> T getParameterValue(String key, T defaultReturn);

	<T> T getAttributeFromRequest(String key);

	<T> T setAttributeToRequest(String key, T value);

	<T> T getAttributeFromSession(String key);

	<T> T setAttributeToSession(String key, T value);

}
