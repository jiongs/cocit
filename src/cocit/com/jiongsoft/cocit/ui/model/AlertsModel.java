package com.jiongsoft.cocit.ui.model;

import java.io.Writer;

import com.jiongsoft.cocit.ui.CuiModel;
import com.jiongsoft.cocit.utils.Json;

/**
 * Alerts模型：用于产生客户端AJAX所需要的提示信息。
 * 
 * @author jiongsoft
 * 
 */
public class AlertsModel implements CuiModel {
	/**
	 * <UL>
	 * <LI>SUCCESS : 200, 操作成功
	 * <LI>SUCCESS_EXTRA_CHANNEL_USER : 201,
	 * <LI>ERROR : 300, 操作失败
	 * <LI>ERROR_NO_ACCESS : 301, 无权执行该操作
	 * <LI>ERROR_WHETHER_KICK_OUT : 302,
	 * <LI>ERROR_DONT_KICK_OUT : 303
	 * </UL>
	 */
	int statusCode;

	/**
	 * 提示信息
	 */
	String message;

	public AlertsModel(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	@Override
	public void render(Writer out) throws Throwable {

		StringBuffer sb = new StringBuffer();
		sb.append('{');
		sb.append("\"statusCode\" : " + statusCode);
		sb.append(", \"message\" : " + Json.toJson(message));
		sb.append('}');

		out.write(sb.toString());
	}

	@Override
	public String getContentType() {
		return CONTENT_TYPE_JSON;
	}

}
