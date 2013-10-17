package com.jiongsoft.cocit.util;

/**
 * Action 工具类: 服务于 actions 包下的 XxxAction 类。
 * 
 * @author yongshan.ji
 * 
 */
public abstract class UrlAPI {

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
	 * “业务模块”命名控件：路径前缀。
	 */
	public static final String URL_NS = "/coc";

	/**
	 * “业务模块”访问路径之模块界面，模块界面可以包括一个主表界面和多个从表界面组成的Tabs界面。
	 * <p>
	 * 参数：moduleID
	 */
	public static final String GET_ENTITY_MODULE_UI = URL_NS + "/getEntityModuleUI/*";

	/**
	 * “业务模块”访问路径之数据表管理界面，数据表管理界面包括左边导航树、顶部操作菜单、GRID；不包括子表。
	 * <p>
	 * 参数：moduleID:tableID
	 */
	public static final String GET_ENTITY_TABLE_UI = URL_NS + "/getEntityTableUI/*";

	/**
	 * “业务模块”访问路径之Grid数据，数据格式通常为JSON或XML格式。
	 * <p>
	 * 参数：moduleID:tableID
	 */
	public static final String GET_ENTITY_GRID_DATA = URL_NS + "/getEntityGridData/*";

	/**
	 * “业务模块”访问路径之List数据，数据格式通常为JSON或XML格式，List数据主要用于Combobox下拉列表。
	 * <p>
	 * 参数：moduleID:tableID
	 */
	public static final String GET_ENTITY_LIST_DATA = URL_NS + "/getEntityListData/*";

	/**
	 * “业务模块”访问路径之导航树数据，数据格式通常为JSON或XML格式。
	 * <p>
	 * 参数：moduleID:tableID
	 */
	public static final String GET_ENTITY_NAVI_DATA = URL_NS + "/getEntityNaviData/*";

	/**
	 * “业务模块”访问路径之加载业务表单，添加、修改数据时使用。
	 * <p>
	 * 参数：moduleID:tableID:operationID
	 */
	public static final String GET_ENTITY_ROW_FORM = URL_NS + "/getEntityRowForm/*";

	/**
	 * 保存实体行：
	 */
	public static final String SAVE_ENTITY_ROW = URL_NS + "/saveEntityRow/*";

	/**
	 * 删除业务数据行集，即批量删除实体数据。操作码：299
	 * <p>
	 * 参数：moduleID:tableID:operationID
	 */
	public static final String DEL_ENTITY_ROWS = URL_NS + "/delEntityRows/*";

	/**
	 * 立即执行实体行任务：即立即对选中的单条记录执行业务逻辑。
	 */
	public static final String RUN_ENTITY_ROW = URL_NS + "/runEntityRow/*";

	/**
	 * 立即执行实体行集任务：即立即对满足条件的记录集合执行业务逻辑。操作码：204
	 */
	public static final String RUN_ENTITY_ROWS = URL_NS + "/runEntityRows/*";

	// public static final String RUN_ENTITY_ASYN_ROWS = URL_NS + "/runEntityAsynRows/*";

	public static final String GET_EXPORT_XLS_FORM = URL_NS + "/getExportXlsForm/*";

	public static final String DO_EXPORT_XLS_ROWS = URL_NS + "/doExportXlsRows/*";

	public static final String GET_IMPORT_XLS_FORM = URL_NS + "/getImportXlsForm/*";

	public static final String DO_IMPORT_XLS_ROWS = URL_NS + "/doImportXlsRows/*";

	/**
	 * 获取手机验证码
	 */
	public static final String GET_IMG_VERIFY_CODE = URL_NS + "/getImgVerifyCode";

	public static final String CHK_IMG_VERIFY_CODE = URL_NS + "/chkImgVerifyCode/*";

	/**
	 * 获取短信验证码
	 * <p>
	 * 参数：手机号码
	 */
	public static final String GET_SMS_VERIFY_CODE = URL_NS + "/getSmsVerifyCode/*";

	public static final String CHK_SMS_VERIFY_CODE = URL_NS + "/chkSmsVerifyCode/*";

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
