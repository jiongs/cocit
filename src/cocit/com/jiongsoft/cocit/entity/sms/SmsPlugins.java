package com.jiongsoft.cocit.entity.sms;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.jiongsoft.cocit.service.CocSoftService;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.utils.CocException;

public abstract class SmsPlugins {
	public static class SendSMS extends BaseEntityPlugin<MTSmsEntity> {
		@Override
		public void before(CocEntityEvent<MTSmsEntity> event) {
			MTSmsEntity entity = event.getEntity();
			CocSoftService softService = Cocit.getHttpContext().getSoftService();
			SmsClient smsClient = softService.getSmsClient();

			if (smsClient == null)
				throw new CocException("短信客户端接口不可用！");

			String returnValue = smsClient.send(entity.getMobiles(), entity.getContent(), "", "", "");
			entity.setResult(returnValue);
		}
	}
}
