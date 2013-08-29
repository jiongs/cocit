package com.jiongsoft.cocit.cui.datagrid;

import java.io.Writer;

import com.jiongsoft.cocit.cui.BaseCuiModel;

public class DataGridModel extends BaseCuiModel {

	@Override
	public void render(Writer out) throws Throwable {
		new DataGridRender().render(out, this);
	}

}
