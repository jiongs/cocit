package com.jiongsoft.ynby.plugins;

import java.util.Date;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.impl.BaseActionPlugin;
import com.jiongsoft.cocit.util.CocException;
import com.jiongsoft.cocit.util.DateUtil;
import com.jiongsoft.cocit.util.HttpUtil;
import com.jiongsoft.ynby.entity.VisitActivity;
import com.jiongsoft.ynby.entity.VisitActivityRegister;
import com.kmetop.demsy.Demsy;

public class VisitActivityPlugins {

	/**
	 * 保存（包括新增、修改）活动计划
	 * 
	 * @author JiongSoft
	 * 
	 */
	public static class SaveActivity extends BaseActionPlugin<VisitActivity> {
		@Override
		public void before(ActionEvent<VisitActivity> event) {
			VisitActivity entity = event.getEntity();

			Date date = entity.getPlanDate();
			String dateStr = DateUtil.formatDate(date, "yyyy年MM月dd日 E");

			Date to = entity.getExpiredTo();
			if (to.getTime() >= date.getTime()) {
				throw new CocException("报名结束时间非法：%s", DateUtil.formatDateTime(to));
			}

			Date from = entity.getExpiredFrom();
			if (from != null && from.getTime() >= to.getTime()) {
				throw new CocException("报名有效期非法：从 %s 到 %s", DateUtil.formatDateTime(from), DateUtil.formatDateTime(to));
			}

			int num = entity.getPlanPersonNumber();
			if (num <= 0) {
				throw new CocException("计划人数非法：%s", num);
			}

			entity.setName(dateStr);
		}
	}

	/**
	 * 保存（包括新增、修改）活动报名
	 * 
	 * @author JiongSoft
	 * 
	 */
	public static class SaveRegister extends BaseActionPlugin<VisitActivityRegister> {
		@Override
		public synchronized void before(ActionEvent<VisitActivityRegister> event) {
			VisitActivityRegister entity = event.getEntity();
			VisitActivity activity = entity.getActivity();

			// 检查短信验证码
			String code = entity.getVerificationCode();
			HttpUtil.checkVerificationCode(Demsy.me().request(), code, "手机验证码非法！");

			// 检查报名有效期
			if (activity.isExpired()) {
				Date from = activity.getExpiredFrom();
				Date to = activity.getExpiredTo();

				throw new CocException("报名有效期从 %s 到 %s！", DateUtil.formatDateTime(from), DateUtil.formatDateTime(to));
			}

			// 计算已报名人数、计划人数
			Integer regNum = activity.getRegisterPersonNumber();
			if (regNum == null) {
				regNum = 0;
			}
			Integer num = entity.getPersonNumber();
			if (num < 1) {
				// num = 1;
				throw new CocException("报名人数非法！%s", num);
			}

			Integer planNum = activity.getPlanPersonNumber();
			if (planNum == null)
				planNum = 0;

			// 检查报名人数是否超额
			if (planNum > 0) {
				int remainNum = planNum - regNum;

				if (remainNum <= 0) {
					throw new CocException("[%s]只剩余[%s]个名额！", activity.getName(), remainNum);
				}
			}

			// 修改计划中的报名人数
			regNum += num;
			activity.setRegisterPersonNumber(regNum);

			// 保存修改后的活动实体
			event.getOrm().save(activity);
		}
	}
}
