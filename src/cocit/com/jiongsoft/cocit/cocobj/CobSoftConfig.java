package com.jiongsoft.cocit.cocobj;

/**
 * 软件配置助理：用于辅助Cocit软件{@link CobSoft}完成配置项管理工作。
 * 
 * @author jiongs753
 * 
 */
public interface CobSoftConfig {

	public <T> T get(String configKey, T defaultReturn);
}
