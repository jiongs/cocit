package com.jiongsoft.cocit.cocui.render;

import java.io.IOException;
import java.io.Writer;

import com.jiongsoft.cocit.cocui.CuiModel;
import com.jiongsoft.cocit.cocui.CuiRender;

public abstract class BaseCuiRender<T extends CuiModel> implements CuiRender<T> {

	protected void print(Writer out, String format, Object... args) throws IOException {
		out.write("\n");
		if (args.length == 0)
			out.write(format);
		else
			out.write(String.format(format, args));
	}

	protected void print(StringBuffer sb, String format, Object... args) {
		sb.append("\n");
		if (args.length == 0)
			sb.append(format);
		else
			sb.append(String.format(format, args));
	}

}
