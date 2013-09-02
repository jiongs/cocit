package com.jiongsoft.cocit.cocui.impl.render;

import java.io.IOException;
import java.io.Writer;

import com.jiongsoft.cocit.cocui.CuiModel;
import com.jiongsoft.cocit.cocui.CuiRender;

public abstract class BaseCuiRender<T extends CuiModel> implements CuiRender<T> {

	protected void print(Writer out, String format, Object... args) throws IOException {
		out.write("\n");
		out.write(String.format(format, args));
	}

}
