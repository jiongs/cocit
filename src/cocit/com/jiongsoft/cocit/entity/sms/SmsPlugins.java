package com.jiongsoft.cocit.entity.sms;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.jiongsoft.cocit.service.SoftService;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.util.CocException;

public abstract class SmsPlugins {
	public static class SendSMS extends BasePlugin<MTSmsEntity> {
		@Override
		public void before(ActionEvent<MTSmsEntity> event) {
			MTSmsEntity entity = event.getEntity();
			SoftService softService = Cocit.getActionContext().getSoftService();
			SmsClient smsClient = softService.getSmsClient();

			if (smsClient == null)
				throw new CocException("短信客户端接口不可用！");

			String returnValue = smsClient.send(entity.getMobiles(), entity.getContent(), "", "", "");
			entity.setResult(returnValue);
		}
	}
}
