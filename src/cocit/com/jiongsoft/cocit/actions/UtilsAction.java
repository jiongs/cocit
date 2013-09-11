package com.jiongsoft.cocit.actions;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.service.CocConfigService;
import com.jiongsoft.cocit.service.CocSoftService;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.ui.CuiModelView;
import com.jiongsoft.cocit.ui.model.AlertsModel;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.VerificationCodeUtil;

@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
public class UtilsAction {

	@At(ActionUtil.GET_IMAGE_VERIFICATION_CODE)
	public void getImageVerificationCode() {
		CocitHttpContext ctx = Cocit.getHttpContext();
		VerificationCodeUtil.makeImageVerificationCode(ctx.getRequest(), ctx.getResponse());
	}

	@At(ActionUtil.CHECK_VERIFICATION_CODE)
	public AlertsModel checkVerificationCode(String code) {
		int statusCode = 200;
		String message = "";
		try {
			CocitHttpContext ctx = Cocit.getHttpContext();
			VerificationCodeUtil.checkVerificationCode(ctx.getRequest(), code, null);

			message = "检查验证码成功！";
		} catch (Throwable e) {
			Log.warn("", e);
			statusCode = 300;
			message = "验证码非法！";
		}

		return new AlertsModel(statusCode, message);
	}

	@At(ActionUtil.GET_SMS_VERIFICATION_CODE)
	public AlertsModel getSmsVerificationCode(String tel) {
		int statusCode = 200;
		String message = "";
		try {
			CocitHttpContext ctx = Cocit.getHttpContext();

			String code = VerificationCodeUtil.makeVerificationCode(ctx.getRequest());

			CocSoftService soft = ctx.getSoft();
			SmsClient smsClient = soft.getSmsClient();
			String tpl = soft.getConfig(CocConfigService.CFG_VERIFICATION_CODE_TEMPLATE, "请输入您的验证码 %s");

			String content = String.format(tpl, code);

			smsClient.send(tel, content, "", "", "");

			message = "获取短信验证码成功！请注意查看您的手机短信。";
		} catch (Throwable e) {
			Log.warn("", e);
			statusCode = 300;
			message = "获取短信验证码失败！";
		}

		return new AlertsModel(statusCode, message);
	}
}
