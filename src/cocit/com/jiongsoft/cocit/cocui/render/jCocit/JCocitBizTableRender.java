package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;

class JCocitBizTableRender extends BaseCuiRender<CuiBizTableModel> {

	@Override
	public void render(Writer out, CuiBizTableModel model) throws Throwable {
		model.getGridModel().render(out);
	}
}
