package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModelData;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;

abstract class JCocitTreeRenders {

	static class ModelRender extends BaseCuiRender<CuiTreeModel> {

		@Override
		public void render(Writer out, CuiTreeModel model) throws Throwable {

		}
	}

	static class DataRender extends BaseCuiRender<CuiTreeModelData> {

		@Override
		public void render(Writer out, CuiTreeModelData model) throws Throwable {

		}
	}
}
