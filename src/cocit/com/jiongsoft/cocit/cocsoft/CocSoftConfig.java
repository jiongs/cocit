package com.jiongsoft.cocit.cocsoft;

/**
 * 软件配置助理：用于辅助Cocit软件{@link CocSoft}完成配置项管理工作。
 * 
 * @author jiongs753
 * 
 */
public interface CocSoftConfig {

	public <T> T get(String configKey, T defaultReturn);
}
