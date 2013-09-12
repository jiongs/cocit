package com.jiongsoft.cocit.entity;

public interface ActionPlugin<T> {
	public void before(ActionEvent<T> event);

	public void after(ActionEvent<T> event);

	public void load(ActionEvent<T> event);

	public void loaded(ActionEvent<T> event);
}
