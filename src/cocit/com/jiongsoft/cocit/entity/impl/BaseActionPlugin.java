package com.jiongsoft.cocit.entity.impl;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.ActionPlugin;

public abstract class BaseActionPlugin<T> implements ActionPlugin<T> {

	@Override
	public void before(ActionEvent<T> event) {
	}

	@Override
	public void after(ActionEvent<T> event) {
	}

	@Override
	public void load(ActionEvent<T> event) {
	}

	@Override
	public void loaded(ActionEvent<T> event) {
	}

}
