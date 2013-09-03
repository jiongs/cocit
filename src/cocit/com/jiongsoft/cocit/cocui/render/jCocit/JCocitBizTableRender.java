package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiMenuModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;

class JCocitBizTableRender extends BaseCuiRender<CuiBizTableModel> {

	@Override
	public void render(Writer out, CuiBizTableModel model) throws Throwable {
		int width = model.get("width", 1000);
		int height = model.get("height", 400);
		int widthTree = 240;
		int widthGrid = width - widthTree - 28;

		//
		print(out, "<table><tr><td valign=\"top\">");

		// 打印Tree
		CuiTreeModel tree = model.getNaviTreeModel();
		if (tree != null) {
			tree.set("width", "" + widthTree);
			tree.set("height", "" + (height - 52));

			tree.render(out);
			print(out, "</td><td valign=\"top\">");
		}

		// 打印Grid
		CuiGridModel grid = model.getGridModel();
		if (grid != null) {
			grid.set("width", "" + (widthGrid));
			grid.set("height", "" + (height - 47));

			grid.render(out);
		}

		// 打印Menu
		CuiMenuModel menu = model.getOperationMenuModel();
		if (menu != null) {
			menu.render(out);
		}

		//
		print(out, "</td></tr></table>");
	}
}
