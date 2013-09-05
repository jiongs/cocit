package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiMenuModel;
import com.jiongsoft.cocit.cocui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;

class JCocitBizTableRender extends BaseCuiRender<CuiBizTableModel> {

	@Override
	public void render(Writer out, CuiBizTableModel model) throws Throwable {
		int width = model.get("width", 1000);
		int height = model.get("height", 655);
		int widthTree = 240;
		int widthGrid = width - widthTree - 20;

		String token = Long.toHexString(System.currentTimeMillis());

		// 打印Menu
		CuiMenuModel menu = model.getOperationMenuModel();
		if (menu != null) {
			menu.set("token", token);
			menu.render(out);
		}

		//
		print(out, "<table><tr>");

		// 打印Tree
		CuiTreeModel tree = model.getNaviTreeModel();
		if (tree != null) {

			tree.set("height", "" + (height - 80));

			tree.set("token", token);

			print(out, "<td valign=\"top\" width=\"%s\">", widthTree);

			CuiSearchBoxModel searchModel = model.getSearchBoxModel();
			if (searchModel != null) {
				searchModel.set("width", "" + widthTree);
				searchModel.render(out);
				print(out, "<div style=\"height: 1px;\"></div>");
			}
			tree.render(out);
			print(out, "</td>");
		} else {
			widthGrid = widthGrid + widthTree + 10;
		}

		// 打印Grid
		CuiGridModel grid = model.getGridModel();
		if (grid != null) {
			grid.set("height", "" + (height - 47));

			grid.set("token", token);

			print(out, "<td valign=\"top\" width=\"%s\">", widthGrid);
			grid.render(out);
			print(out, "</td>");
		}

		//
		print(out, "</tr></table>");
	}
}
