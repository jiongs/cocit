package com.jiongsoft.ynby.plugins;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;

import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.VerificationCodeUtil;
import com.jiongsoft.ynby.entity.VisitActivityPlan;
import com.jiongsoft.ynby.entity.VisitActivityRegister;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.biz.BizEvent;
import com.kmetop.demsy.plugins.BizPlugin;

public class VisitActivityPlugins {

	/**
	 * 保存（包括新增、修改）活动计划
	 * 
	 * @author jiongsoft
	 * 
	 */
	public static class SavePlan extends BizPlugin<VisitActivityPlan> {
		@Override
		public void before(BizEvent<VisitActivityPlan> event) {
			VisitActivityPlan entity = event.getEntity();
			Date date = entity.getPlanDate();
			String dateStr = DateUtil.formatDate(date);
			entity.setName(dateStr);
		}
	}

	/**
	 * 保存（包括新增、修改）活动报名
	 * 
	 * @author jiongsoft
	 * 
	 */
	public static class SaveRegister extends BizPlugin<VisitActivityRegister> {
		@Override
		public void before(BizEvent<VisitActivityRegister> event) {
			VisitActivityRegister entity = event.getEntity();

			// 检查短信验证码
			String code = entity.getVerificationCode();
			VerificationCodeUtil.checkVerificationCode(Demsy.me().request(), code, "手机验证码非法！");
		}
	}
}
