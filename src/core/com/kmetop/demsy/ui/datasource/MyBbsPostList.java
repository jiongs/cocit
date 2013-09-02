package com.kmetop.demsy.ui.datasource;

import com.jiongsoft.cocit.corm.expr.CndExpr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.mvc.ui.UIBlockContext;

public class MyBbsPostList extends UiDataset {

	protected CndExpr getExpr(UIBlockContext parser) {
		return CndExpr.eq("createdBy", Demsy.me().username());
	}
}
