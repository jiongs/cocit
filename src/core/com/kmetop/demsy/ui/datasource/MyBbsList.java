package com.kmetop.demsy.ui.datasource;

import static com.kmetop.demsy.Demsy.bizEngine;
import static com.kmetop.demsy.Demsy.moduleEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.LibConst;
import com.kmetop.demsy.comlib.biz.IBizSystem;
import com.kmetop.demsy.comlib.security.IModule;
import com.kmetop.demsy.comlib.web.IBbsReply;
import com.kmetop.demsy.comlib.web.IBbsTopic;
import com.kmetop.demsy.mvc.ui.UIBlockContext;
import com.kmetop.demsy.mvc.ui.UIBlockDataModel;
import com.kmetop.demsy.orm.Pager;

public class MyBbsList extends UiDataset {

	public Map process(UIBlockContext parser) {
		Map ctx = new HashMap();
		Demsy me = Demsy.me();

		Integer page = me.param("page", Integer.class, 1);

		UIBlockDataModel data = parser.getCatalog();

		/*
		 * 检索我的论坛帖子
		 */
		IBizSystem sys = bizEngine.getSystem(IBbsTopic.SYS_CODE);
		IModule module = moduleEngine.getModule(me.getSoft(), sys);
		Pager pager1 = this.query(parser, bizEngine.getType(sys), page, LibConst.F_CREATED, null);
		List result1 = pager1.getResult();

		// 按一列显示
		if (parser.getCellCount() == 1) {
			UIBlockDataModel item = null;
			for (Object obj : result1) {
				if (obj == null)
					item = parser.makeDataModel(null);
				else
					item = parser.makeDataModel(obj, null, module.getId());

				data.addItem(item);
			}
		}
		// 按多列显示
		else {
			List<List> listlist = parser.querySplit(result1);
			UIBlockDataModel item = null;
			for (List list : listlist) {
				UIBlockDataModel row = new UIBlockDataModel();
				data.addItem(row);
				for (Object obj : list) {
					if (obj == null)
						item = parser.makeDataModel(null);
					else
						item = parser.makeDataModel(obj, null, module.getId());

					data.addItem(item);
				}
			}
		}

		/*
		 * 检索我的论坛回帖
		 */
		sys = bizEngine.getSystem(IBbsReply.SYS_CODE);
		Pager pager2 = this.query(parser, bizEngine.getType(sys), page, LibConst.F_CREATED, null);
		List<IBbsReply> result2 = pager2.getResult();
		// 按一列显示
		if (parser.getCellCount() == 1) {
			UIBlockDataModel item = null;
			for (IBbsReply obj : result2) {
				if (obj == null)
					item = parser.makeDataModel(null);
				else
					item = parser.makeDataModel(obj, null, module.getId(), Long.parseLong("" + obj.getTopic().getId()));

				data.addItem(item);
			}
		}
		// 按多列显示
		else {
			List<List> listlist = parser.querySplit(result2);
			UIBlockDataModel item = null;
			for (List<IBbsReply> list : listlist) {
				UIBlockDataModel row = new UIBlockDataModel();
				data.addItem(row);
				for (IBbsReply obj : list) {
					if (obj == null)
						item = parser.makeDataModel(null);
					else
						item = parser.makeDataModel(obj, null, module.getId(), Long.parseLong("" + obj.getTopic().getId()));

					data.addItem(item);
				}
			}
		}
		pager2.setTotalRecord(Math.max(pager1.getTotalRecord(), pager2.getTotalRecord()));

		ctx.put("data", data);
		ctx.put("pager", pager2);

		return ctx;
	}

	private Pager query(UIBlockContext parser, Class kls, int page, String order, CndExpr cnd) {
		CndExpr expr = CndExpr.eq("createdBy", Demsy.me().username());
		if (cnd != null) {
			expr = expr.and(cnd);
		}
		expr.setPager(page, parser.getPageSize());
		expr.addDesc(order);
		Pager pager = new Pager(kls);
		pager.setQueryExpr(expr);
		Demsy.orm().query(pager);

		return pager;
	}
}
