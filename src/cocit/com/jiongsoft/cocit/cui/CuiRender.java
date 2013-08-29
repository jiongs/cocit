package com.jiongsoft.cocit.cui;

import java.io.Writer;

/**
 * CuiRender： 用于输出指定类型的{@link CuiModel}到浏览器。
 * 
 * @author jiongs753
 * 
 */
public interface CuiRender<T extends CuiModel> {

	/**
	 * 
	 * @param out
	 * @param model
	 * @throws Throwable
	 */
	void render(Writer out, T model) throws Throwable;
}
