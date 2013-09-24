package com.jiongsoft.ynby.plugins;

import java.util.Date;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.action.ActionHelper;
import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.jiongsoft.cocit.entity.sms.MTSmsEntity;
import com.jiongsoft.cocit.orm.Orm;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.jiongsoft.cocit.service.SoftService;
import com.jiongsoft.cocit.util.CocCalendar;
import com.jiongsoft.cocit.util.CocException;
import com.jiongsoft.cocit.util.HttpUtil;
import com.jiongsoft.cocit.util.Log;
import com.jiongsoft.cocit.util.StringUtil;
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
			cal.setTime(14, 0, 0, 0);

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
		public void before(ActionEvent<VisitActivityRegister> event) {
			synchronized (SaveRegister.class) {
				Orm orm = event.getOrm();
				VisitActivityRegister entity = event.getEntity();

				Long oldID = entity.getId();
				if (oldID != null && oldID > 0) {
					/*
					 * 修改报名：恢复原来的活动报名人数
					 */

					VisitActivityRegister oldEntity = orm.load(VisitActivityRegister.class, oldID);
					VisitActivity oldActivity = oldEntity.getActivity();
					int oldNum = oldEntity.getPersonNumber();
					int oldRegNum = oldActivity.getRegisterPersonNumber();
					oldRegNum -= oldNum;
					oldActivity.setRegisterPersonNumber(oldRegNum);

					orm.save(oldActivity);
				} else {
					/*
					 * 检查该手机号是否已经报过名；且活动时间尚未到来。
					 */

					// String tel = entity.getTel();
					// VisitActivityRegister oldEntity = orm.get(VisitActivityRegister.class, Expr.eq("tel", tel).addDesc("id"));
					// if (oldEntity != null && oldEntity.getActivity().getPlanDate().getTime() > System.currentTimeMillis()) {
					// throw new CocException("该手机号已经报名参加【%s】的活动，不允许重复报名！", oldEntity.getActivity().getName());
					// }
				}

				VisitActivity activity = entity.getActivity();
				activity = orm.load(activity.getClass(), activity.getId());

				// 检查短信验证码
				if (oldID == null || oldID == 0) {
					String tel = entity.getTel();
					String code = entity.getTelVerifyCode();
					HttpUtil.checkSmsVerifyCode(Demsy.me().request(), tel, code, "手机验证码非法！");
				}

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
					int remainNum = planNum - regNum;// - num;

					if (remainNum <= 0) {
						throw new CocException("[%s]名额已满！", activity.getName());
					}
				}

				// 修改计划中的报名人数
				regNum += num;
				activity.setRegisterPersonNumber(regNum);
				entity.setActivity(activity);

				// 生成邀请函验证码
				Date date = CocCalendar.now().get();
				Integer time = new Long(date.getTime()).intValue();
				entity.setVerificationCode(Integer.toHexString(time).toUpperCase());

				// 保存修改后的活动实体
				orm.save(activity);
			}
		}

		/**
		 * 报名成功后发送邀请函和验证码
		 */
		public void after(ActionEvent<VisitActivityRegister> event) {
			SoftService soft = Cocit.getActionContext().getSoftService();
			VisitActivityRegister entity = event.getEntity();

			String tpl = null;
			if (entity.getSex() == 0) {
				tpl = soft.getConfig("sms.visit.invitation0", "");
			} else if (entity.getSex() == 1) {
				tpl = soft.getConfig("sms.visit.invitation1", "");
			}
			if (StringUtil.isNil(tpl)) {
				tpl = soft.getConfig("sms.visit.invitation", "邀请函：尊敬的%s先生/女士：您好，感谢您对云南白药的关注。我们诚邀您参加于%s在云南白药产业园区举办的“走进云南白药”活动，届时欢迎您的到来。验证码：%s");
			}
			String content = String.format(tpl, entity.getName(), CocCalendar.format(entity.getActivity().getPlanDate(), "MM月dd日HH:mm"), entity.getVerificationCode());

			/**
			 * 发送短信邀请函
			 */
			MTSmsEntity sms = MTSmsEntity.make("“走进云南白药”邀请函", entity.getTel(), content);
			ActionHelper actionHelper = ActionHelper.make("0:MTSmsEntity:c");
			actionHelper.entityManager.save(sms, "c");
		}
	}
}
