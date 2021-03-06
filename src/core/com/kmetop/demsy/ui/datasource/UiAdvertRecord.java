package com.kmetop.demsy.ui.datasource;

import java.util.List;

import com.jiongsoft.cocit.orm.expr.Expr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.mvc.ui.UIBlockContext;
import com.kmetop.demsy.mvc.ui.UIBlockDataModel;
import com.kmetop.demsy.orm.IOrm;
import com.kmetop.demsy.orm.Pager;

public class UiAdvertRecord extends UiBaseDataSource {

	public UIBlockDataModel process(UIBlockContext maker) {

		List list = query(maker);
		if (list != null && list.size() > 0) {
			Object obj = list.get(0);
			try {
				Demsy.moduleEngine.increase(Demsy.orm(), obj, "clickNum");
			} catch (Throwable e) {
				log.warn(e);
			}
			return maker.makeDataModel(obj);
		}

		return null;
	}

	private List query(UIBlockContext maker) {
		IOrm orm = Demsy.orm();
		if (orm != null) {
			Class type = maker.getType();

			int size = orm.count(type, maker.getExpr());
			int page = new java.util.Random().nextInt(size);

			Pager pager = new Pager(type);
			if (maker.getExpr() != null)
				pager.setQueryExpr(maker.getExpr().setPager(page, 1));
			else
				pager.setQueryExpr(Expr.page(page, 1));

			return orm.query(pager);
		}

		return null;
	}
}
