package com.kmetop.demsy.biz;

public interface IBizPlugin<T> {
	public void before(BizEvent<T> event);

	public void after(BizEvent<T> event);

	public void load(BizEvent<T> event);

	public void loaded(BizEvent<T> event);
}
