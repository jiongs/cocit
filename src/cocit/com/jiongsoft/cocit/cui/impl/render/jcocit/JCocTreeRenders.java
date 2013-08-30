package com.jiongsoft.cocit.cui.impl.render.jcocit;

import java.io.Writer;

import com.jiongsoft.cocit.cui.CuiRender;
import com.jiongsoft.cocit.cui.model.CuiTreeModel;
import com.jiongsoft.cocit.cui.model.CuiTreeModelData;

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
