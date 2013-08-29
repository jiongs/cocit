package com.jiongsoft.cocit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.coft.Coft;

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
	public HttpServletRequest getRequest();

	/**
	 * 获取当前HttpServletResponse对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse();

	/**
	 * 获取当前HTTP请求所对应的软件
	 * 
	 * @return 正在通过HTTP请求访问的软件
	 */
	public Coft getCoft();

	/**
	 * 获取软件配置
	 * 
	 * @return
	 */
	public <T> T getCoftConfig(String configKey, T defaultReturn);

}
