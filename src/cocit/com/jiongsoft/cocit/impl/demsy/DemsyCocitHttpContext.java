package com.jiongsoft.cocit.impl.demsy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiongsoft.cocit.impl.BaseCocitHttpContext;
import com.kmetop.demsy.Demsy;

public class DemsyCocitHttpContext extends BaseCocitHttpContext {
	private Demsy demsyContext;

	public DemsyCocitHttpContext(HttpServletRequest req, HttpServletResponse res) {
		super(req, res);

		//
		demsyContext = Demsy.me();
	}

	@Override
	public int getBrowserWidth() {
		Double d = demsyContext.login().getClientWidth();
		return d.intValue();
	}

	@Override
	public int getBrowserHeight() {
		Double d = demsyContext.login().getClientHeight();
		return d.intValue();
	}

	@Override
	public int getAdminTopHeight() {
		int ret = 0;
		int browserWidth = getBrowserHeight();
		String w = this.getSoftConfig("admin.ui.topHeight", "");

		try {
			if (w.endsWith("%"))
				ret = browserWidth * Integer.parseInt(w.substring(0, w.length() - 1)) / 100;
			else
				ret = Integer.parseInt(w);
		} catch (Throwable e) {
			ret = 95;
		}

		return ret + 45;
	}

	@Override
	public int getAdminLeftWidth() {
		int ret = 0;
		int browserWidth = getBrowserWidth();
		String w = this.getSoftConfig("admin.ui.leftWidth", "");
		try {
			if (w.endsWith("%"))
				ret = browserWidth * Integer.parseInt(w.substring(0, w.length() - 1)) / 100;
			else
				ret = Integer.parseInt(w);
		} catch (Throwable e) {
			ret = new Double(browserWidth * 0.2).intValue();
		}

		return ret + 30;
	}

	@Override
	public int getClientWindowHeight() {
		int ret = this.getParameterValue("_windowHeight", 0);
		if (ret == 0)
			ret = this.getBrowserHeight() - this.getAdminTopHeight();

		return ret;
	}

	@Override
	public int getClientWindowWidth() {
		int ret = this.getParameterValue("_windowWidth", 0);
		if (ret == 0)
			ret = this.getBrowserWidth() - this.getAdminLeftWidth();

		return ret;
	}
}
