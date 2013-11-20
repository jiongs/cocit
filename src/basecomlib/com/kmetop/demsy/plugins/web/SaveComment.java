package com.kmetop.demsy.plugins.web;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.base.security.Module;
import com.kmetop.demsy.comlib.impl.sft.web.content.Comment;
import com.kmetop.demsy.comlib.security.IUser;
import com.kmetop.demsy.lang.Str;
import com.kmetop.demsy.orm.IOrm;

public class SaveComment extends BasePlugin {

	@Override
	public void before(ActionEvent event) {
		Comment comment = (Comment) event.getEntity();

		// 回复
		if (comment.getId() != null && comment.getId() > 0) {

		} else {// 评论
			IOrm orm = (IOrm) event.getOrm();
			Module m = comment.getModule();
			m = orm.load(m.getClass(), m.getId());
			Class type = Demsy.bizEngine.getType(Demsy.moduleEngine.getSystem(m));
			Object subject = orm.load(type, comment.getSubjectID());
			comment.setName(subject.toString());
			IUser user = Demsy.me().loginUser();
			if (user != null) {
				if (!Str.isEmpty(user.getName()))
					comment.setCommenter(user.getName());
				else
					comment.setCommenter(user.getCode());
			}

			Demsy.moduleEngine.increase(orm, subject, "commentNum");
		}
	}

	@Override
	public void after(ActionEvent event) {

	}

	@Override
	public void loaded(ActionEvent event) {

	}

}
