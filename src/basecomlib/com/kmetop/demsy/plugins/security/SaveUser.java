package com.kmetop.demsy.plugins.security;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.jiongsoft.cocit.util.StringUtil;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.LibConst;
import com.kmetop.demsy.comlib.entity.base.BaseUser;
import com.kmetop.demsy.comlib.impl.base.security.WebUser;
import com.kmetop.demsy.lang.DemsyException;
import com.kmetop.demsy.orm.IOrm;

public class SaveUser extends BasePlugin {

	@Override
	public void before(ActionEvent event) {
		BaseUser user = (BaseUser) event.getEntity();
		String code = user.getCode();

		IOrm orm = (IOrm) event.getOrm();
		int count = orm.count(user.getClass(), Expr.eq(LibConst.F_SOFT_ID, Demsy.me().getSoft()).and(Expr.eq(LibConst.F_CODE, code)));
		if (count > 0) {
			throw new DemsyException("登录帐号已被使用，请重新填写登录帐号!");
		}

		/*
		 * 验证用户名
		 */
		char c = code.charAt(0);
		if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
			throw new DemsyException("登录帐号必须以字母开头！且只能由数字、字母、下划线组成！");
		}

		for (int i = code.length() - 1; i > 0; i--) {
			c = code.charAt(i);
			if (!(c >= '0' && c <= '9')// is not number
					&& !(c >= 'a' && c <= 'z')// is not lower case
					&& !(c >= 'A' && c <= 'Z')// is not lower case
					&& c != '_'//
			) {
				throw new DemsyException("登录帐号必须以字母开头！且只能由数字、字母、下划线组成！");
			}
		}

		/*
		 * 验证手机号码
		 */
		if (user instanceof WebUser) {
			WebUser webUser = (WebUser) user;
			if (
			// !StringUtil.isNil(webUser.getTel()) &&//
			!StringUtil.isMobile(webUser.getTel())//
			) {
				throw new DemsyException("非法手机号码！");
			}
			if (//
				// !StringUtil.isNil(webUser.getQq()) &&//
			(!StringUtil.isNumber(webUser.getQq()) || webUser.getQq().trim().length() < 5)//
			) {
				throw new DemsyException("非法QQ号码！");
			}
		}

		/*
		 * 验证密码
		 */
		String newPassword = user.getRawPassword();
		int minLength = 8;
		int maxLength = 16;
		if (newPassword.length() < minLength || newPassword.length() > maxLength) {
			throw new DemsyException("密码长度必须为8-16位！");
		}

		// if (newPassword.toLowerCase().equals(newPassword)) {
		// throw new DemsyException("密码必须包含至少一个大写字母！");
		// }
		//
		// if (newPassword.toUpperCase().equals(newPassword)) {
		// throw new DemsyException("密码必须包含至少一个小写字母！");
		// }
		//
		// boolean containsNumber = false;
		// for (int i = newPassword.length() - 1; i >= 0; i--) {
		// char c = newPassword.charAt(i);
		// if (c >= '0' && c <= '9') {
		// containsNumber = true;
		// break;
		// }
		// }
		// if (!containsNumber) {
		// throw new DemsyException("密码必须包含至少一个数字！");
		// }

		// boolean containsSymbols = false;
		// for (int i = newPassword.length() - 1; i >= 0; i--) {
		// char c = newPassword.charAt(i);
		// if (!(c >= '0' && c <= '9')// is not number
		// && !(c >= 'a' && c <= 'z')// is not lower case
		// && !(c >= 'A' && c <= 'Z')// is not lower case
		// && !(c == ' ')// is not space
		// ) {
		// containsSymbols = true;
		// break;
		// }
		// }
		// if (!containsSymbols) {
		// throw new DemsyException("密码必须包含至少一个特殊字符（出数字、字母以外的字符）！");
		// }
	}

	@Override
	public void after(ActionEvent event) {
	}

	@Override
	public void loaded(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}
