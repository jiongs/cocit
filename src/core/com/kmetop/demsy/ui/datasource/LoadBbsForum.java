package com.kmetop.demsy.ui.datasource;

import static com.kmetop.demsy.Demsy.bizEngine;
import static com.kmetop.demsy.Demsy.moduleEngine;
import static com.kmetop.demsy.mvc.MvcConst.MvcUtil.contextPath;

import java.util.Date;
import java.util.Map;

import com.jiongsoft.cocit.orm.expr.Expr;
import com.jiongsoft.cocit.service.SecurityManager;
import com.jiongsoft.cocit.util.CocCalendar;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.security.IModule;
import com.kmetop.demsy.comlib.web.IBbsTopic;
import com.kmetop.demsy.mvc.MvcConst;
import com.kmetop.demsy.mvc.MvcConst.MvcUtil;
import com.kmetop.demsy.mvc.ui.UIBlockContext;
import com.kmetop.demsy.orm.IOrm;

public class LoadBbsForum extends UiDataset {

	public Map process(UIBlockContext parser) {
		Map context = super.process(parser);

		IModule topicMdl = moduleEngine.getModule(Demsy.me().getSoft(), bizEngine.getSystem(IBbsTopic.SYS_CODE));
		context.put("topicUrl", MvcUtil.contextPath(MvcConst.URL_BZ_SAVE, topicMdl.getId() + ":", "c", Demsy.me().addToken()));
		context.put("uploadUrl", contextPath(MvcConst.URL_UPLOAD, topicMdl.getId()));
		Demsy.security.addPermission("block" + parser.getBlock().getId(), SecurityManager.ROLE_ANONYMOUS, topicMdl.getId(), "c");

		if (parser.getCatalogObj() != null && parser.getPageView() != null)
			parser.getPageView().set("subtitle", parser.getCatalogObj().toString());
		
		IOrm orm = Demsy.orm();
		Class cls =Demsy.bizEngine.getType(Demsy.moduleEngine.getSystem(topicMdl));
		int total = orm.count(cls);
		Date todayDate=CocCalendar.parse(CocCalendar.getNowDate());
		int today = orm.count(cls, Expr.gt("created", todayDate));
		int yesterday = orm.count(cls, Expr.gt("created", CocCalendar.getNext(todayDate, -1)).and(Expr.lt("created", todayDate)));
		context.put("topicCountTotal", total);
		context.put("topicCountToday", today);
		context.put("topicCountYesterday", yesterday);

		return context;
	}
}
