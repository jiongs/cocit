package com.jiongsoft.cocit.utils;

public abstract class ActionUtil {

	/**
	 * 编码路径参数
	 * 
	 * @param operationArgs
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
	 * @param operationArgs
	 * @return
	 */
	public static String[] decodeArgs(String args) {
		String str = args;

		// String str = StringUtil.decodeHex(operationArgs);

		return StringUtil.toArray(str, ":");
	}

	/*
	 * 参数默认值
	 */

	public static final int DEFAULT_PAGE_SIZE = 20;

	/*
	 * 以下是数据管理模块相关功能的访问路径。
	 */

	/**
	 * “业务模块”访问路径之路径前缀。
	 */
	public static final String ACTION_PATH_PREFIX = "/coc";

	/**
	 * “业务模块”访问路径之模块界面，模块界面可以包括一个主表界面和多个从表界面组成的Tabs界面。
	 */
	public static final String GET_BIZ_MODULE_UI = ACTION_PATH_PREFIX + "/getBizModuleUI/*";

	/**
	 * “业务模块”访问路径之数据表管理界面，数据表管理界面包括左边导航树、顶部操作菜单、GRID；不包括子表。
	 */
	public static final String GET_BIZ_TABLE_UI = ACTION_PATH_PREFIX + "/getBizTableUI/*";

	/**
	 * “业务模块”访问路径之Grid数据，数据格式通常为JSON或XML格式。
	 */
	public static final String GET_BIZ_GRID_DATA = ACTION_PATH_PREFIX + "/getBizGridData/*";

	/**
	 * “业务模块”访问路径之导航树数据，数据格式通常为JSON或XML格式。
	 */
	public static final String GET_BIZ_NAVI_DATA = ACTION_PATH_PREFIX + "/getBizNaviData/*";

	/**
	 * “业务模块”访问路径之加载业务表单
	 */
	public static final String GET_BIZ_FORM_UI = ACTION_PATH_PREFIX + "/getBizFormUI/*";

	/**
	 * “业务模块”访问路径之保存业务表单
	 */
	public static final String SAVE_BIZ_FORM_DATA = ACTION_PATH_PREFIX + "/saveBizFormData/*";

	/**
	 * “业务模块”访问路径之删除业务数据
	 */
	public static final String DELETE_BIZ_DATA = ACTION_PATH_PREFIX + "/deleteBizData/*";

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
