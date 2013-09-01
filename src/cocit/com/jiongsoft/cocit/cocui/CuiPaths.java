package com.jiongsoft.cocit.cocui;

import com.jiongsoft.cocit.utils.StringUtil;

public abstract class CuiPaths {

	public static String encodeArgs(String args) {
		return StringUtil.encodeHex(args);
	}

	public static String decodeArgs(String args) {
		return StringUtil.decodeHex(args);
	}

	/*
	 * 以下是数据管理模块相关功能的访问路径。
	 */

	/**
	 * “数据模块”之相关管理功能的根路径
	 */
	static final String DATA_ROOT = "/coc/data";

	/**
	 * “数据模块”之模块界面的访问路径，模块界面可以包括一个主表界面和多个从表界面组成的Tabs界面。
	 */
	public static final String DATA_MODULE = DATA_ROOT + "/module/*";

	/**
	 * “数据模块”之数据表管理界面的访问路径，数据表管理界面包括左边导航树、顶部操作菜单、GRID；不包括子表。
	 */
	public static final String DATA_TABLE = DATA_ROOT + "/table/*";

	/**
	 * “数据模块”之导航树数据的访问路径，数据格式通常为JSON或XML格式。
	 */
	public static final String DATA_NAVITREEDATA = DATA_ROOT + "/naviTreeData/*";

	/**
	 * “数据模块”之Grid数据的访问路径，数据格式通常为JSON或XML格式。
	 */
	public static final String DATA_GRIDDATA = DATA_ROOT + "/gridData/*";

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
