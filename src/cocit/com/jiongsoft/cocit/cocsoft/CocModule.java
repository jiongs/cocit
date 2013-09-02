package com.jiongsoft.cocit.cocsoft;

/**
 * CocModule：组件化自定义软件模块，也称“组件化自定义模块/自定义模块/模块”等。
 * <UL>
 * <LI>代表一个运行时的自定义模块，通常由定义在数据库中的数据实体解析而来；
 * <LI>“Componentization of custom module[it]”，“it = module”；
 * <LI>Module是在Cocit平台上定制出来的软件模块；
 * <LI>全称“组件化自定义软件模块”；简称“CocModule”、“CoC模块”、“软件功能模块”、“软件模块”、“模块”等；
 * <LI>软件模块：可以在功能菜单树上显示的节点；
 * <LI>自定义软件模块包括：数据模块、工作流模块、报表模块等；
 * <LI>软件模块集是一棵功能树，且只有文件夹模块才能作为其他模块的父模块；
 * </UL>
 * 
 * @author jiongs753
 * 
 * @author yongshan.ji
 * 
 */
public interface CocModule extends CocObject {

	// /**
	// * 获取上级模块，每个模块的上级模块必须是一个文件夹模块。
	// *
	// * @return
	// */
	// CocFolderModule getParent();

	/**
	 * 获取模块徽标
	 * 
	 * @return
	 */
	String getLogo();
}
