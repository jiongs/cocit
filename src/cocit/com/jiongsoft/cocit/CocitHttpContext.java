package com.jiongsoft.cocit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.cocobj.CobSoft;
import com.jiongsoft.cocit.security.CocLoginUser;

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
	CobSoft getSoft();

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

	<T> T getRequestParam(String key, T defaultReturn);

	<T> T getRequestProp(String key);

	<T> T setRequestProp(String key, T value);

	<T> T getSessionProp(String key);

	<T> T setSessionProp(String key, T value);

}
