package com.jiongsoft.cocit.cui.impl.render.jcocit;

import java.io.Writer;

import com.jiongsoft.cocit.cui.CuiRender;
import com.jiongsoft.cocit.cui.model.CuiGridModel;
import com.jiongsoft.cocit.cui.model.CuiGridModelData;

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
