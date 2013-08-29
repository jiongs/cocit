package com.jiongsoft.cocit.impl.demsy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.impl.BaseCocitHttpContext;
import com.kmetop.demsy.Demsy;

public class DemsyCocitHttpContext extends BaseCocitHttpContext {

	public DemsyCocitHttpContext(HttpServletRequest req, HttpServletResponse res) {
		super(req, res);
	}

	@Override
	protected Long getCoftID() {
		return Demsy.me().getSoft().getId();
	}

}
