package com.jiongsoft.cocit.coft;

/**
 * 软件配置助理：用于辅助Cocit软件{@link Coft}完成配置项管理工作。
 * 
 * @author jiongs753
 * 
 */
public interface CoftConfig {

	public <T> T get(String configKey, T defaultReturn);
}
