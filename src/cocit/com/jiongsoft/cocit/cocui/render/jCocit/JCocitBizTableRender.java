package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;

class JCocitBizTableRender extends BaseCuiRender<CuiBizTableModel> {

	@Override
	public void render(Writer out, CuiBizTableModel model) throws Throwable {
		//
		print(out, "<table><tr><td valign=\"top\">");

		// 打印Tree
		CuiTreeModel tree = model.getNaviTreeModel();
		if (tree != null) {
			tree.render(out);
			print(out, "</td><td valign=\"top\">");
		}

		// 打印Grid
		CuiGridModel grid = model.getGridModel();
		if (grid != null) {
			grid.render(out);
		}

		//
		print(out, "</td></tr></table>");
	}
}
