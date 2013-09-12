package com.jiongsoft.cocit.service;

/**
 * 软件配置助理：用于辅助Cocit软件{@link SoftService}完成配置项管理工作。
 * 
 * @author jiongs753
 * 
 */
public interface ConfigService {

	/**
	 * 软件配置项KEY：短信服务商类型
	 * <p>
	 * 可选值：
	 * <UL>
	 * <LI>zucp: 漫道短信
	 * </UL>
	 */
	public static String CFG_TYPE = "sms.type";
	/**
	 * 代理服务器主机IP
	 */
	public static String CFG_PROXY_HOST = "sms.proxy.host";
	/**
	 * 代理服务器主机端口
	 */
	public static String CFG_PROXY_PORT = "sms.proxy.port";
	/**
	 * 软件配置项KEY：uid
	 */
	public static String CFG_UID = "sms.uid";
	/**
	 * 软件配置项KEY：密码
	 */
	public static String CFG_PWD = "sms.pwd";
	/**
	 * 软件配置项KEY：URL
	 */
	public static String CFG_URL = "sms.url";
	/**
	 * 手机短信验证码模版。如：“欢迎走进云南白药，请输入您的验证码 %s”
	 */
	public static String CFG_VERIFICATION_CODE_TEMPLATE = "sms.verification_code_tpl";

	public <T> T get(String configKey, T defaultReturn);
}
