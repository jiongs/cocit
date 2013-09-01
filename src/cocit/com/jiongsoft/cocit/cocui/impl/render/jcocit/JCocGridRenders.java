package com.jiongsoft.cocit.cocui.impl.render.jcocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.CuiRender;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;

abstract class JCocGridRenders {

	static class ModelRender implements CuiRender<CuiGridModel> {

		@Override
		public void render(Writer out, CuiGridModel model) throws Throwable {

		}
	}

	static class DataRender implements CuiRender<CuiGridModelData> {

		@Override
		public void render(Writer out, CuiGridModelData model) throws Throwable {

		}
	}
}
