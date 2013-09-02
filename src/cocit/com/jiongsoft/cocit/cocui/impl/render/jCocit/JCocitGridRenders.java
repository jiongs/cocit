package com.jiongsoft.cocit.cocui.impl.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.impl.render.BaseCuiRender;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;

abstract class JCocitGridRenders {

	static class ModelRender extends BaseCuiRender<CuiGridModel> {

		@Override
		public void render(Writer out, CuiGridModel model) throws Throwable {

		}
	}

	static class DataRender extends BaseCuiRender<CuiGridModelData> {

		@Override
		public void render(Writer out, CuiGridModelData model) throws Throwable {

		}
	}
}
