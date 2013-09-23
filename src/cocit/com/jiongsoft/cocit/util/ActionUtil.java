package com.jiongsoft.cocit.util;


/**
 * Action 工具类: 服务于 actions 包下的 XxxAction 类。
 * 
 * @author yongshan.ji
 * 
 */
public abstract class ActionUtil {

	/**
	 * 编码路径参数
	 * 
	 * @param opArgs
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
	 * @param opArgs
	 * @return
	 */
	public static String[] decodeArgs(String args) {
		String str = args;

		// String str = StringUtil.decodeHex(opArgs);

		return StringUtil.toArray(str, ":");
	}

	/**
	 * 将JSP路径参数转换成以“/”开头的相对路径
	 * 
	 * @param jspPathArgs
	 *            JSP页面路径，有子目录的用冒号分隔，如: /visit/index 路径为： visit:index
	 * @return 返回 /visit/index
	 */
	public static String makeJspPath(String jspPathArgs) {
		return "/" + jspPathArgs.replace(':', '/');
	}

	/*
	 * 参数默认值
	 */

	public static final int DEFAULT_PAGE_SIZE = 20;

	/**
	 * JSP 目录
	 */
	public static final String JSP_DIR = "/WEB-INF/jsp/coc";

	/*
	 * 以下是数据管理模块相关功能的访问路径。
	 */

	/**
	 * “业务模块”访问路径之路径前缀。
	 */
	public static final String ACTION_PATH_PREFIX = "/coc";

	/**
	 * “业务模块”访问路径之模块界面，模块界面可以包括一个主表界面和多个从表界面组成的Tabs界面。
	 * <p>
	 * 参数：moduleID
	 */
	public static final String GET_ENTITY_MODULE_UI = ACTION_PATH_PREFIX + "/getEntityModuleUI/*";

	/**
	 * “业务模块”访问路径之数据表管理界面，数据表管理界面包括左边导航树、顶部操作菜单、GRID；不包括子表。
	 * <p>
	 * 参数：moduleID:tableID
	 */
	public static final String GET_ENTITY_TABLE_UI = ACTION_PATH_PREFIX + "/getEntityTableUI/*";

	/**
	 * “业务模块”访问路径之Grid数据，数据格式通常为JSON或XML格式。
	 * <p>
	 * 参数：moduleID:tableID
	 */
	public static final String GET_ENTITY_GRID_DATA = ACTION_PATH_PREFIX + "/getEntityGridData/*";

	/**
	 * “业务模块”访问路径之导航树数据，数据格式通常为JSON或XML格式。
	 * <p>
	 * 参数：moduleID:tableID
	 */
	public static final String GET_ENTITY_NAVI_DATA = ACTION_PATH_PREFIX + "/getEntityNaviData/*";

	/**
	 * “业务模块”访问路径之加载业务表单
	 * <p>
	 * 参数：moduleID:tableID:operationID
	 */
	public static final String GET_ENTITY_FORM = ACTION_PATH_PREFIX + "/getEntityForm/*";

	/**
	 * “业务模块”访问路径之保存业务表单
	 */
	public static final String SAVE_ENTITY_FORM_DATA = ACTION_PATH_PREFIX + "/saveEntityFormData/*";

	/**
	 * “业务模块”访问路径之删除业务数据
	 * <p>
	 * 参数：moduleID:tableID:operationID
	 */
	public static final String DELETE_ENTITY_DATA = ACTION_PATH_PREFIX + "/deleteEntityData/*";

	public static final String EXEC_ENTITY_TASK = ACTION_PATH_PREFIX + "/execEntityTask/*";

	public static final String EXEC_ENTITY_ASYN_TASK = ACTION_PATH_PREFIX + "/execEntityAsynTask/*";

	public static final String GET_EXPORT_XLS_FORM = ACTION_PATH_PREFIX + "/getExportXlsForm/*";

	public static final String GET_IMPORT_XLS_FORM = ACTION_PATH_PREFIX + "/getImportXlsForm/*";

	public static final String EXPORT_XLS = ACTION_PATH_PREFIX + "/exportXls/*";

	public static final String IMPORT_XLS = ACTION_PATH_PREFIX + "/importXls/*";

	/**
	 * 获取手机验证码
	 */
	public static final String GET_IMG_VERIFY_CODE = ACTION_PATH_PREFIX + "/getImgVerifyCode";

	public static final String CHECK_IMG_VERIFY_CODE = ACTION_PATH_PREFIX + "/checkImgVerifyCode/*";

	/**
	 * 获取短信验证码
	 * <p>
	 * 参数：手机号码
	 */
	public static final String GET_SMS_VERIFY_CODE = ACTION_PATH_PREFIX + "/getSmsVerifyCode/*";

	public static final String CHECK_SMS_VERIFY_CODE = ACTION_PATH_PREFIX + "/checkSmsVerifyCode/*";

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
	/**
	 * 获取JSP页面
	 */
	public static final String GET_JSP_MODEL = "/jsp/*";

	// TODO:
}
