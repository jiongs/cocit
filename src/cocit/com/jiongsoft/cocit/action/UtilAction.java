package com.jiongsoft.cocit.action;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.ActionContext;
import com.jiongsoft.cocit.service.ConfigService;
import com.jiongsoft.cocit.service.SoftService;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.ui.UIModelView;
import com.jiongsoft.cocit.ui.model.AlertsModel;
import com.jiongsoft.cocit.util.ActionUtil;
import com.jiongsoft.cocit.util.Log;
import com.jiongsoft.cocit.util.HttpUtil;

@Ok(UIModelView.VIEW_TYPE)
@Fail(UIModelView.VIEW_TYPE)
public class UtilAction {

	@At(ActionUtil.GET_IMAGE_VERIFICATION_CODE)
	public void getImageVerificationCode() {
		ActionContext ctx = Cocit.getActionContext();
		HttpUtil.makeImageVerificationCode(ctx.getRequest(), ctx.getResponse());
	}

	@At(ActionUtil.CHECK_VERIFICATION_CODE)
	public AlertsModel checkVerificationCode(String code) {
		int statusCode = 200;
		String message = "";
		try {
			ActionContext ctx = Cocit.getActionContext();
			HttpUtil.checkVerificationCode(ctx.getRequest(), code, null);

			message = "检查验证码成功！";
		} catch (Throwable e) {
			Log.warn("", e);
			statusCode = 300;
			message = "验证码非法！";
		}

		return AlertsModel.make(statusCode, message);
	}

	@At(ActionUtil.GET_SMS_VERIFICATION_CODE)
	public AlertsModel getSmsVerificationCode(String tel) {
		int statusCode = 200;
		String message = "";
		try {
			ActionContext ctx = Cocit.getActionContext();

			String code = HttpUtil.makeVerificationCode(ctx.getRequest());

			SoftService soft = ctx.getSoftService();
			SmsClient smsClient = soft.getSmsClient();
			String tpl = soft.getConfig(ConfigService.CFG_VERIFICATION_CODE_TEMPLATE, "请输入您的验证码 %s");

			String content = String.format(tpl, code);

			smsClient.send(tel, content, "", "", "");

			message = "获取短信验证码成功！请注意查看您的手机短信。";
		} catch (Throwable e) {
			Log.warn("", e);
			statusCode = 300;
			message = "获取短信验证码失败！";
		}

		return AlertsModel.make(statusCode, message);
	}
}
