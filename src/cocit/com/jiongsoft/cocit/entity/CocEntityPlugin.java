package com.jiongsoft.cocit.entity;

public interface CocEntityPlugin<T> {
	public void before(CocEntityEvent<T> event);

	public void after(CocEntityEvent<T> event);

	public void load(CocEntityEvent<T> event);

	public void loaded(CocEntityEvent<T> event);
}
