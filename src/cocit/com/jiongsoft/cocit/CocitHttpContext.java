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

	/**
	 * 获取客户端浏览器窗口可用宽度：登录成功后自带的参数值(clientWidth)。
	 * 
	 * @return
	 */
	int getBrowserWidth();

	/**
	 * 获取客户端浏览器窗口可用高度：登录成功后自带的参数值(clientHeight)。
	 * 
	 * @return
	 */
	int getBrowserHeight();

	/**
	 * 获取后台主界面top高度：通过配置项计算(admin.ui.topHeight)。
	 * 
	 * @return
	 */
	int getAdminTopHeight();

	/**
	 * 获取后台主界面左边功能树宽度：通过配置项计算(admin.ui.leftWidth)。
	 * 
	 * @return
	 */
	int getAdminLeftWidth();

	/**
	 * 获取客户端窗口高度：优先获取客户端参数(_windowHeight)，如果未指定参数则返回{@link #getBrowserHeight()}减{@link #getAdminLeftHeight()}的结果。
	 * 
	 * @return
	 */
	int getClientWindowHeight();

	/**
	 * 获取客户端窗口宽度：优先获取客户端参数(_windowWidth)，，如果未指定参数则返回{@link #getBrowserWidth()}减{@link #getAdminLeftWidth()}的结果。
	 * 
	 * @return
	 */
	int getClientWindowWidth();

}
