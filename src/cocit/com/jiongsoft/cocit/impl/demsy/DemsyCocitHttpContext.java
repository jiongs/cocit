package com.jiongsoft.cocit.impl.demsy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.impl.BaseCocitHttpContext;

public class DemsyCocitHttpContext extends BaseCocitHttpContext {
	// private Demsy demsyContext;

	public DemsyCocitHttpContext(HttpServletRequest req, HttpServletResponse res) {
		super(req, res);

		//
		// demsyContext = Demsy.me();
	}

}
