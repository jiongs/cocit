package com.jiongsoft.cocit.cocsoft;

import java.util.List;

/**
 * 自定义软件文件夹模块。
 * <p>
 * 只有文件夹模块才能作为其他模块的父模块。
 * 
 * @author jiongs753
 * 
 */
public interface CocFolderModule extends CocModule {

	List<CocModule> getChildrenModules();
}