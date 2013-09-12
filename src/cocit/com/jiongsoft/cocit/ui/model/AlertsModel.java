package com.jiongsoft.cocit.ui.model;

import com.jiongsoft.cocit.util.Json;

/**
 * Alerts模型：用于产生客户端AJAX所需要的JSON对象，通常用于提示框。
 * <p>
 * <code>
 * {
 * 	"statusCode": 200,
 * 	"message": "操作成功"
 * }
 * <p>
 * {
 * 	"statusCode": 300,
 * 	"message": "操作失败"
 * }
 * <p>
 * {
 * 	"statusCode": 301,
 * 	"message": "无权执行该操作"
 * }
 * </code>
 * 
 * @author jiongsoft
 * 
 */
public class AlertsModel extends JSONModel {

	/**
	 * 
	 * <UL>
	 * <LI>SUCCESS : 200, 操作成功
	 * <LI>SUCCESS_EXTRA_CHANNEL_USER : 201,
	 * <LI>ERROR : 300, 操作失败
	 * <LI>ERROR_NO_ACCESS : 301, 无权执行该操作
	 * <LI>ERROR_WHETHER_KICK_OUT : 302,
	 * <LI>ERROR_DONT_KICK_OUT : 303
	 * </UL>
	 * 
	 * @param statusCode
	 * @param message
	 */
	public static AlertsModel make(int statusCode, String message) {
		AlertsModel model = new AlertsModel();

		StringBuffer sb = new StringBuffer();
		sb.append('{');
		sb.append("\"statusCode\" : " + statusCode);
		sb.append(", \"message\" : " + Json.toJson(message));
		sb.append('}');

		model.content = sb.toString();

		return model;
	}
}
