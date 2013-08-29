package com.jiongsoft.cocit.coft;

import com.jiongsoft.cocit.sms.SmsClient;

/**
 * Coft： 组件化自定义软件
 * <UL>
 * <LI>“Componentization of custom software[it]”，“it = software”；
 * <LI>Coft是在Cocit平台上定制出来的软件；
 * <LI>全称“组件化自定义软件”；简称“Coft”、“Cocit软件”、“CoC软件”、“平台软件”等；
 * <LI>Cocit平台中可以定制并运行多套软件，该接口的实例即代表一套软件；
 * </UL>
 * 
 * @author jiongs753
 * 
 */
public interface Coft {

	/**
	 * 获取软件ID
	 * 
	 * @return
	 */
	public Long getCoftID();

	/**
	 * 获取短信客户端API接口，用于收发短信。
	 * 
	 * @return 短信客户端API接口
	 */
	public SmsClient getSmsClient();

	/**
	 * 获取软件配置项
	 * 
	 * @param configKey
	 *            配置项KEY
	 * @param defaultReturn
	 *            默认返回值
	 * @return 配置项数据，如果指定的配置项不存在，则返回指定默认值。
	 */
	public <T> T getConfig(String configKey, T defaultReturn);
}
