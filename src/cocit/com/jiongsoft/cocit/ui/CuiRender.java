package com.jiongsoft.cocit.ui;

import java.io.Writer;

/**
 * CuiRender： 用于输出指定类型的{@link CuiModel}到浏览器。
 * 
 * @author jiongs753
 * 
 */
public interface CuiRender<T extends CuiModel> {

	/**
	 * 输出窗体界面
	 * 
	 * @param out
	 * @param model
	 * @throws Throwable
	 */
	void render(Writer out, T model) throws Throwable;

}
