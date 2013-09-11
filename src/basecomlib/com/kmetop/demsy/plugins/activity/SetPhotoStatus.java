package com.kmetop.demsy.plugins.activity;

import java.util.List;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.kmetop.demsy.comlib.impl.sft.activity.ActivityCatalog;
import com.kmetop.demsy.comlib.impl.sft.activity.PhotoActivity;
import com.kmetop.demsy.orm.IOrm;

public class SetPhotoStatus extends BaseEntityPlugin {

	@Override
	public synchronized void before(CocEntityEvent event) {
		IOrm orm = (IOrm) event.getOrm();
		Object obj = event.getEntity();
		if (obj instanceof List) {
			List<PhotoActivity> list = (List) obj;
			for (PhotoActivity photo : list) {
				ActivityCatalog catalog = photo.getCatalog();
				PhotoActivity first = (PhotoActivity) orm.load(PhotoActivity.class, Expr.eq("catalog", catalog).addAsc("id"));
				long firstID = first.getId();
				photo.setCode("" + (photo.getId() - firstID + 1));
			}
		} else {
			PhotoActivity photo = (PhotoActivity) obj;
			ActivityCatalog catalog = photo.getCatalog();
			PhotoActivity first = (PhotoActivity) orm.load(PhotoActivity.class, Expr.eq("catalog", catalog).addAsc("id"));
			long firstID = first.getId();
			photo.setCode("" + (photo.getId() - firstID + 1));
		}
	}

	@Override
	public void after(CocEntityEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loaded(CocEntityEvent event) {
		// TODO Auto-generated method stub

	}

}
