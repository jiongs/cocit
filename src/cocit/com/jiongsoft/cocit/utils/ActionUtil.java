package com.jiongsoft.cocit.utils;

public abstract class ActionUtil {

	/**
	 * 编码路径参数
	 * 
	 * @param args
	 * @return
	 */
	public static String encodeArgs(Object... args) {
		StringBuffer sb = new StringBuffer();
		for (Object str : args) {
			if (str == null)
				str = "";

			sb.append(str + ":");
		}

		return sb.toString();

		// return StringUtil.encodeHex(sb.toString());
	}

	/**
	 * 解码路径参数
	 * 
	 * @param args
	 * @return
	 */
	public static String[] decodeArgs(String args) {
		String str = args;

		// String str = StringUtil.decodeHex(args);

		return StringUtil.toArray(str, ":");
	}

	/*
	 * 以下是数据管理模块相关功能的访问路径。
	 */

	/**
	 * “业务模块”之相关管理功能的根路径
	 */
	static final String ROOT_PATH = "/coc";

	/**
	 * “业务模块”之模块界面的访问路径，模块界面可以包括一个主表界面和多个从表界面组成的Tabs界面。
	 */
	public static final String GET_BIZ_MODULE_MODEL = ROOT_PATH + "/getBizModuleModel/*";

	/**
	 * “业务模块”之数据表管理界面的访问路径，数据表管理界面包括左边导航树、顶部操作菜单、GRID；不包括子表。
	 */
	public static final String GET_BIZ_TABLE_MODEL = ROOT_PATH + "/getBizTableModel/*";

	/**
	 * “业务模块”之Grid数据的访问路径，数据格式通常为JSON或XML格式。
	 */
	public static final String GET_BIZ_TABLE_GRID_DATA = ROOT_PATH + "/getBizTableGridData/*";

	/**
	 * “业务模块”之导航树数据的访问路径，数据格式通常为JSON或XML格式。
	 */
	public static final String GET_BIZ_TABLE_NAVI_TREE_DATA = ROOT_PATH + "/getBizTableNaviTreeData/*";

	/*
	 * 以下是报表管理模块相关功能的访问路径
	 */
	// TODO:

	/*
	 * 以下是流程管理模块相关功能的访问路径
	 */
	// TODO:

	/*
	 * 以下是访问网站页面的相关路径
	 */
	// TODO:
}
