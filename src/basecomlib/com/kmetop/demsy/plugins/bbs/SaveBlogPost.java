package com.kmetop.demsy.plugins.bbs;

import java.util.Date;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.sft.lybbs.LyblogPosts;
import com.kmetop.demsy.lang.DemsyException;

public class SaveBlogPost extends BaseEntityPlugin {

	@Override
	public void before(CocEntityEvent event) {
		LyblogPosts obj = (LyblogPosts) event.getEntity();

		// 回复
		if (obj.getId() != null && obj.getId() > 0) {

		} else {// 论坛
			Demsy ctx = Demsy.me();

			if (ctx.login() == null) {
				throw new DemsyException("你尚未登录，请先登录!");
			}
			obj.setCreatedIP(ctx.request().getRemoteAddr());
			obj.setCreated(new Date());
			obj.setCreatedBy(ctx.username());
		}
	}

	@Override
	public void after(CocEntityEvent event) {

	}

	@Override
	public void loaded(CocEntityEvent event) {

	}

}
