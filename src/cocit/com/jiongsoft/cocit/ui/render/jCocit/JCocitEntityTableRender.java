package com.jiongsoft.cocit.ui.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.ui.model.CuiEntityTableModel;
import com.jiongsoft.cocit.ui.model.CuiGridModel;
import com.jiongsoft.cocit.ui.model.CuiMenuModel;
import com.jiongsoft.cocit.ui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.ui.model.CuiTreeModel;
import com.jiongsoft.cocit.ui.render.BaseCuiRender;

class JCocitEntityTableRender extends BaseCuiRender<CuiEntityTableModel> {

	@Override
	public void render(Writer out, CuiEntityTableModel model) throws Throwable {
		CocitHttpContext ctx = Cocit.getHttpContext();
		int width = model.get("width", ctx.getClientUIWidth());
		int height = model.get("height", ctx.getClientUIHeight());

		int treeWidth = 240;
		int gridWidth = width - treeWidth - 30;
		int treeHeight = height - 56;
		int gridHeight = height - 50;

		String token = model.get("token", Long.toHexString(System.currentTimeMillis()));

		/*
		 * 工具栏菜单：工具栏菜单ID将被DataGrid引用
		 */
		CuiMenuModel menu = model.getOperationMenuModel();
		if (menu != null) {
			menu.set("token", token);
			menu.render(out);
		}

		/**
		 * 用一个Table将数据表界面分成两部分：1.左边为导航树，2.右边为DataGrid。
		 * <UL>
		 * <LI>token: 业务令牌，工具栏操作菜单通过业务令牌与该table相关联；
		 * </UL>
		 */
		print(out, "<table token=\"%s\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr>", token);

		/*
		 * 1.左边为导航树
		 */
		CuiTreeModel tree = model.getNaviTreeModel();
		if (tree != null) {

			tree.set("height", "" + treeHeight);
			tree.set("token", token);

			print(out, "<td valign=\"top\" width=\"%s\" style=\"padding:5px;\">", treeWidth);

			CuiSearchBoxModel searchModel = model.getSearchBoxModel();
			if (searchModel != null) {
				searchModel.set("width", "" + treeWidth);
				searchModel.render(out);
				print(out, "<div style=\"height: 1px;\"></div>");
			}
			tree.render(out);

			print(out, "</td>");
		} else {
			gridWidth = width - 23;
		}

		/*
		 * 2.右边为DataGrid
		 */
		CuiGridModel grid = model.getGridModel();
		if (grid != null) {
			grid.set("width", "" + gridWidth);
			grid.set("height", "" + gridHeight);
			grid.set("token", token);

			print(out, "<td valign=\"top\" width=\"%s\" style=\"padding:5px;0 5px 5px;\">", gridWidth);

			grid.render(out);

			print(out, "</td>");
		}

		//
		print(out, "</tr></table>");
	}
}
