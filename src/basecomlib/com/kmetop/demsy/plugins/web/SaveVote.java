package com.kmetop.demsy.plugins.web;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.base.web.Vote;
import com.kmetop.demsy.comlib.security.IModule;
import com.kmetop.demsy.orm.IOrm;

public class SaveVote extends BasePlugin {

	@Override
	public void before(ActionEvent event) {
		Vote vote = (Vote) event.getEntity();

		// 回复
		if (vote.getId() != null && vote.getId() > 0) {

		} else {// 评论
			IOrm orm = (IOrm) event.getOrm();

			IModule module = Demsy.moduleEngine.getModule(vote.getModuleID());
			Object subject = orm.load(Demsy.bizEngine.getType(Demsy.moduleEngine.getSystem(module)), vote.getSubjectID());
			// vote.setName(subject.toString());
			// IUser user = Demsy.me().loginUser();

			if (vote.getType() == 1)
				Demsy.moduleEngine.increase(orm, subject, "voteAgreeNum");
			else
				Demsy.moduleEngine.increase(orm, subject, "voteOpposeNum");
		}
	}

	@Override
	public void after(ActionEvent event) {

	}

	@Override
	public void loaded(ActionEvent event) {

	}

}
