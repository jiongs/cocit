package com.jiongsoft.cocit.coft;

/**
 * Coft模块工厂：用于辅助{@link Coft}“创建、缓存”软件模块等管理工作。
 * 
 * @author yongshan.ji
 * 
 */
public interface CoduleFactory {

	/**
	 * 获取软件模块
	 * 
	 * @param moduleID
	 *            模块ID
	 * @return Cocit软件模块
	 */
	Codule getModule(Long moduleID);

}
