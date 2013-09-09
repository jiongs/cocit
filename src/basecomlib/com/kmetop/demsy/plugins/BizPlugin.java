package com.kmetop.demsy.plugins;

import com.kmetop.demsy.biz.BizEvent;
import com.kmetop.demsy.biz.IBizPlugin;

public class BizPlugin<T> implements IBizPlugin<T> {

	@Override
	public void before(BizEvent<T> event) {
	}

	@Override
	public void after(BizEvent<T> event) {
	}

	@Override
	public void load(BizEvent<T> event) {
	}

	@Override
	public void loaded(BizEvent<T> event) {
	}

}
