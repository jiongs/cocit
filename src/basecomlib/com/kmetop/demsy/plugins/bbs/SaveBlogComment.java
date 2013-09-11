package com.kmetop.demsy.plugins.bbs;

import java.util.Date;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.sft.lybbs.LyblogComment;
import com.kmetop.demsy.orm.IOrm;

public class SaveBlogComment extends BaseEntityPlugin {

	@Override
	public void before(CocEntityEvent event) {
		LyblogComment obj = (LyblogComment) event.getEntity();

		// 回复
		if (obj.getId() != null && obj.getId() > 0) {

		} else {// 论坛
			Demsy ctx = Demsy.me();
			obj.setCreatedIP(ctx.request().getRemoteAddr());
			obj.setCreated(new Date());

			IOrm orm = (IOrm) event.getOrm();
			Demsy.moduleEngine.increase(orm, obj.getPost(), "commentNum");
		}
	}

	@Override
	public void after(CocEntityEvent event) {

	}

	@Override
	public void loaded(CocEntityEvent event) {

	}

}
