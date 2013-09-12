package com.jiongsoft.cocit.ui.model;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.jiongsoft.cocit.service.CocEntityActionService;
import com.jiongsoft.cocit.ui.CuiModel;

/**
 * JSP模型： 环境context中可以包含
 * <UL>
 * <LI>actionService: {@link CocEntityActionService}对象
 * </UL>
 * 
 * @author yongshan.ji
 * 
 */
public class JSPModel implements CuiModel {

	private String contextPath;

	private String jsp;

	private Map<String, Object> context;

	/**
	 * 
	 * 软件环境路径 = WEB环境路径 + 软件JSP根路径。
	 * <p>
	 * 软件JSP根路径是软件编号替换“.”为“_”后的路径。如软件编号 www.yunnanbaiyao.com.cn 替换后变成 www_yunnanbaiyao_com_cn
	 * <p>
	 * JSP相对路径是相对于软件JSP根路径而言。
	 * 
	 * @param contextPath
	 *            软件环境路径
	 * @param jspPath
	 *            JSP相对路径
	 * @return
	 */
	public static JSPModel make(String contextPath, String jspPath) {
		JSPModel ret = new JSPModel();

		ret.contextPath = contextPath;
		ret.jsp = contextPath + jspPath;

		return ret;
	}

	protected JSPModel() {
		context = new HashMap();
	}

	@Override
	public void render(Writer out) throws Throwable {

	}

	@Override
	public String getContentType() {
		return null;
	}

	public String getJsp() {
		return jsp;
	}

	public <T> T get(String key) {
		return (T) context.get(key);
	}

	public void set(String key, Object value) {
		context.put(key, value);
	}

	/**
	 * WEB环境路径 + 软件环境路径
	 * 
	 * @param contextPath
	 */
	public String getContextPath() {
		return contextPath;
	}

}
