package com.kmetop.demsy.plugins.bbs;

import java.util.Date;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.impl.BaseActionPlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.sft.lybbs.LybbsDb;
import com.kmetop.demsy.comlib.impl.sft.lybbs.LybbsPosttopic;
import com.kmetop.demsy.lang.DemsyException;
import com.kmetop.demsy.lang.Str;
import com.kmetop.demsy.orm.IOrm;

public class SaveBbsTopic extends BaseActionPlugin {

	@Override
	public void before(ActionEvent event) {
		LybbsPosttopic obj = (LybbsPosttopic) event.getEntity();

		// 回复
		if (obj.getId() != null && obj.getId() > 0) {

		} else {// 论坛
			Demsy ctx = Demsy.me();
			obj.setCreatedIP(ctx.request().getRemoteAddr());
			obj.setCreated(new Date());

			LybbsDb forum = obj.getForum();
			forum = (LybbsDb) Demsy.orm().load(LybbsDb.class, forum.getId());

			// 检查：正规论坛只允许登录用户发帖
			if (ctx.loginUser() != null) {
				obj.setCreatedBy(ctx.username());
			} else {
				String type = forum.getType();
				if ("yes".equals(type)) {
					throw new DemsyException("正规论坛只允许登录用户发帖, 请先登录!");
				}
			}

			// 查看方式为“指定用户”，则指定用户必须填写
			if (obj.getViewMode() == 4) {
				if (Str.isEmpty(obj.getViewUsers())) {
					throw new DemsyException("必需填写指定用户!");
				}
			}

			IOrm orm = (IOrm) event.getOrm();
			Demsy.moduleEngine.increase(orm, obj.getForum(), "topicnumber");
		}
	}

	@Override
	public void after(ActionEvent event) {

	}

	@Override
	public void loaded(ActionEvent event) {

	}

}
