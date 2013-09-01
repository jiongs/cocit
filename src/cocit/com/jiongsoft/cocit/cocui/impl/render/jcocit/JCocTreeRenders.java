package com.jiongsoft.cocit.cocui.impl.render.jcocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.CuiRender;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModelData;

abstract class JCocTreeRenders {

	static class ModelRender implements CuiRender<CuiTreeModel> {

		@Override
		public void render(Writer out, CuiTreeModel model) throws Throwable {

		}
	}

	static class DataRender implements CuiRender<CuiTreeModelData> {

		@Override
		public void render(Writer out, CuiTreeModelData model) throws Throwable {

		}
	}
}
