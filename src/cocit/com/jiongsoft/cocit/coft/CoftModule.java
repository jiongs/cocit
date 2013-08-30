package com.jiongsoft.cocit.coft;

import java.util.List;

/**
 * CoftModule：组件化自定义模块
 * <UL>
 * <LI>“Componentization of custom module[it]”，“it = module”。
 * <LI>Module是在Cocit平台上定制出来的软件模块；
 * <LI>全称“组件化自定义软件模块”；简称“CoftModule”、“CoC模块”、“软件功能模块”、“软件模块”、“模块”等；
 * <LI>软件模块：可以在功能菜单树上显示的节点；
 * <LI>自定义软件模块包括：数据模块；工作流模块；报表模块等；
 * </UL>
 * 
 * @author jiongs753
 * 
 * @author yongshan.ji
 * 
 */
public interface CoftModule {
	public static final byte TYPE_FOLDER = 90;

	public static final byte TYPE_STATIC = 1;
	/**
	 * 数据模块
	 */
	public static final byte TYPE_DATA = 2;
	/**
	 * 报表模块
	 */
	public static final byte TYPE_REPORT = 4;
	/**
	 * 工作流模块
	 */
	public static final byte TYPE_WORKFLOW = 5;

	public static final byte TYPE_WEB = 3;

	public static final byte TYPE_DYN = 6;

	/**
	 * 获取模块类型，可选值参见 {@link #TYPE_DATA}/{@link #TYPE_REPORT}/{@link #TYPE_WORKFLOW} 等。
	 * 
	 * @return
	 */
	byte getType();

	/**
	 * 获取模块ID
	 * 
	 * @return
	 */
	Long getID();

	/**
	 * 获取模块名称
	 * 
	 * @return
	 */
	String getName();

	List<CoftModule> getChildren();

}
