package com.jiongsoft.ynby.plugins;

import java.util.Date;

import com.jiongsoft.cocit.action.ActionHelper;
import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.jiongsoft.cocit.orm.Orm;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.jiongsoft.cocit.util.CocCalendar;
import com.jiongsoft.cocit.util.CocException;
import com.jiongsoft.cocit.util.HttpUtil;
import com.jiongsoft.cocit.util.Log;
import com.jiongsoft.ynby.entity.VisitActivity;
import com.jiongsoft.ynby.entity.VisitActivityAddress;
import com.jiongsoft.ynby.entity.VisitActivityRegister;
import com.kmetop.demsy.Demsy;

public class VisitActivityPlugins {

	public static class SaveYearActivity extends BasePlugin<ActionHelper> {

		@Override
		public void before(ActionEvent<ActionHelper> event) {
			ActionHelper helper = event.getEntity();
			Orm orm = helper.orm;

			VisitActivityAddress address = orm.get(VisitActivityAddress.class, null);
			if (address == null) {
				throw new CocException("请先设置活动地点！");
			}

			CocCalendar cal = CocCalendar.now();
			cal.setTime(0, 0, 0, 0);

			int year = cal.getYear();
			int month = cal.getMonth();

			Date now = new Date();

			// 添加12个月的计划
			for (int i = 1; i <= 12; i++) {

				// 设置月份
				cal.setYear(year);
				cal.setMonth(month + i);

				// 从月末的一周中找出周四和周五
				cal.setDay(-7);
				int date = cal.getDay();
				for (int j = 1; j <= 7; j++) {

					cal.setDay(date + j);

					int week = cal.getWeek();

					// 将周四和周五作为活动计划时间并保存
					if (week == 4 || week == 5) {
						Date activityDate = cal.get();

						// 活动时间已过期，忽略
						if (activityDate.getTime() < now.getTime())
							continue;

						// 该时间内的活动已经存在，则忽略
						if (orm.get(VisitActivity.class, Expr.eq("planDate", activityDate)) != null)
							continue;

						// 创建活动实体
						VisitActivity entity = new VisitActivity();

						// 设置活动“时间、人数、地点”
						entity.setPlanDate(activityDate);
						entity.setPlanPersonNumber(60);
						entity.setAddress(address);

						// 设置活动“报名结束日期”为活动前一天
						CocCalendar expiredTo = CocCalendar.make(activityDate);
						expiredTo.setDay(expiredTo.getDay() - 1);
						entity.setExpiredTo(expiredTo.get());
						try {
							helper.entityManager.save(entity, "c");
						} catch (Throwable e) {
							Log.warn("", e);
						}
					}
				}
			}

			event.setReturnValue("添加一年的活动计划成功！");
		}
	}

	/**
	 * 保存（包括新增、修改）活动计划
	 * 
	 * @author JiongSoft
	 * 
	 */
	public static class SaveActivity extends BasePlugin<VisitActivity> {
		@Override
		public void before(ActionEvent<VisitActivity> event) {
			VisitActivity entity = event.getEntity();

			Date date = entity.getPlanDate();
			String dateStr = CocCalendar.format(date, "yyyy年MM月dd日 E");

			Date to = entity.getExpiredTo();
			if (to.getTime() >= date.getTime()) {
				throw new CocException("报名结束时间非法：%s", CocCalendar.formatDateTime(to));
			}

			Date from = entity.getExpiredFrom();
			if (from != null && from.getTime() >= to.getTime()) {
				throw new CocException("报名有效期非法：从 %s 到 %s", CocCalendar.formatDateTime(from), CocCalendar.formatDateTime(to));
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
	public static class SaveRegister extends BasePlugin<VisitActivityRegister> {
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

				throw new CocException("报名有效期从 %s 到 %s！", CocCalendar.formatDateTime(from), CocCalendar.formatDateTime(to));
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
