package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.cocui.model.CuiBizModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Lang;

class JCocitBizModuleRender extends BaseCuiRender<CuiBizModuleModel> {

	@Override
	public void render(Writer writer, CuiBizModuleModel model) throws Throwable {
		StringWriter out = new StringWriter();

		String contextPath = Cocit.getContextPath();

		print(out, "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		print(out, "<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		print(out, "<head>");
		print(out, "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		print(out, "<title>%s</title>", model.getName());

		/*
		 * CSS
		 */
		print(out, "<link href=\"%s/jCocit/css/jCocit.min.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.icon.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		print(out, "<link href=\"%s/jCocit-src/css/jCocit.plugin.bizmodule.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);

		// 调试 CSS
		print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.searchbox.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.panel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.window.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.dialog.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.datagrid.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.calendar.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);

		/*
		 * JS
		 */
		print(out, "<script src=\"%s/jCocit/jquery.min.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit/js/jCocit.src.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.plugin.bizmodule.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.nls.zh.js\" type=\"text/javascript\"></script>", contextPath);

		// 调试 JS
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.menu.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.searchbox.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.utils.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.dialog.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.tree.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.datagrid.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.pagination.js\" type=\"text/javascript\"></script>", contextPath);

		print(out, "</head>");
		print(out, "<body><div>");

		/**
		 * 准备参数:
		 * <UL>
		 * <LI>token: 操作令牌，通过该令牌将“NaviTree,Toolbar,DataGrid,ChildrenTabs,SearchBox”等串联在一起。
		 * <LI>width: 模块界面宽度
		 * <LI>height: 模块界面高度
		 * <LI>mainTabsHeight: 业务主表Tabs高度
		 * <LI>childrenTabsHeight: 业务子表Tabs高度
		 * </UL>
		 */
		CocitHttpContext ctx = Cocit.getHttpContext();
		String token = model.get("token", Long.toHexString(System.currentTimeMillis()));
		int width = model.get("width", ctx.getClientUIWidth());
		int height = model.get("height", ctx.getClientUIHeight());
		int mainTabsHeight = height / 2;
		int childrenTabsHeight = height - mainTabsHeight - 1;

		CuiBizTableModel mainBizTable = model.getMainBizTableModel();
		if (mainBizTable == null) {
			childrenTabsHeight = height;
		}
		List<CuiBizTableModel> childrenBizTables = model.getChildrenBizTableModels();
		if (Lang.isNil(childrenBizTables)) {
			mainTabsHeight = height;
		}

		// 模块界面 TABLE
		// print(out, "<table><tr><td>");

		/*
		 * 业务主表 Tabs
		 */
		if (mainBizTable != null) {

			mainBizTable.set("width", "" + width);
			mainBizTable.set("height", "" + mainTabsHeight);

			// 业务主表 Tabs DIV
			print(out, "<div class=\"jCocit-ui jCocit-tabs\" data-options=\"\" style=\"width:%spx;height:%spx;\">"//
					, width, mainTabsHeight);

			// 业务主表 Tab DIV
			print(out, "<div title=\"%s\" class=\"jCocit-gridtab\" data-options=\"\" style=\"padding:5px;\">"//
					, model.getName());

			mainBizTable.set("token", token);
			mainBizTable.render(out);

			print(out, "</div>");// end: 业务主表 Tab DIV
			print(out, "</div>");// end: 业务主表 Tabs DIV

		}

		/**
		 * 业务子表 Tabs
		 */
		if (!Lang.isNil(childrenBizTables)) {
			// 业务子表 TR
			// print(out, "</td></tr><tr><td>");

			// 业务主表与业务子表之间的间隙
			print(out, "<div style=\"height: 5px;\"></div>");

			// 业务子表 Tabs DIV
			print(out, "<div id=\"childrentabs_%s\" class=\"jCocit-ui jCocit-tabs\" data-options=\"onSelect: jCocit.bizmodule.doTabsSelect\" style=\"width:%spx; height:%spx; \">"//
					, token, width, childrenTabsHeight);

			// fkfield: 表示业务子表将通过哪个外键字段关联到业务主表？
			String fkfield;
			for (CuiBizTableModel childBizTable : childrenBizTables) {
				fkfield = childBizTable.get("fkfield", "");
				print(out, "<div title=\"%s\" class=\"jCocit-gridtab\" data-options=\"bizToken:'%s', fkfield: '%s', url: '%s?_uiHeight=%s&_uiWidth=%s',closable: false, cache: true\" style=\"padding:5px\"></div>"//
						, childBizTable.getName(), token, fkfield, childBizTable.getLoadUrl(), childrenTabsHeight, width);
			}

			print(out, "</div>");// end: 业务子表 Tabs DIV

			// 子模块工具菜单——位于主模块TABS右边
			// print(out, "<div id=\"tabtools_%s\">", model.getId());
			// for (CuiBizTableModel child : children) {
			// print(out, "<a href=\"javascript:void(0)\" class=\"jCocit-ui jCocit-button\" data-options=\"plain: false");
			// // print(out, ", iconCls:'icon-add'");
			// print(out, "\">%s</a>", child.getName());
			// }
			// print(out, "</div>");

		}

		// print(out, "</td></tr></table>");//end: 模块界面 TABLE

		print(out, "</div></body></html>");

		writer.write(out.toString());
	}
}
