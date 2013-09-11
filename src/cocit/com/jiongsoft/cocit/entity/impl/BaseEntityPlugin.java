package com.jiongsoft.cocit.entity.impl;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.CocEntityPlugin;

public abstract class BaseEntityPlugin<T> implements CocEntityPlugin<T> {

	@Override
	public void before(CocEntityEvent<T> event) {
	}

	@Override
	public void after(CocEntityEvent<T> event) {
	}

	@Override
	public void load(CocEntityEvent<T> event) {
	}

	@Override
	public void loaded(CocEntityEvent<T> event) {
	}

}
