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

		// CSS
		print(out, "<link href=\"%s/jCocit/css/jCocit.min.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);

		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.button.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.datagrid.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.searchbox.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		// print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.panel.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		print(out, "<link href=\"%s/jCocit-src/css/jCocit.alerts.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);

		print(out, "<link href=\"%s/jCocit-src/css/jCocit.plugin.bizmodule.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);

		// JAVASCTIPTS
		print(out, "<script src=\"%s/jCocit/jquery.min.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit/js/jCocit.src.js\" type=\"text/javascript\"></script>", contextPath);

		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.searchbox.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.window.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.dialog.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.tree.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.datagrid.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.pagination.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.nls.zh.js\" type=\"text/javascript\"></script>", contextPath);

		print(out, "<script src=\"%s/jCocit-src/js/jCocit.plugin.bizmodule.js\" type=\"text/javascript\"></script>", contextPath);

		print(out, "</head>");
		print(out, "<body>");

		CocitHttpContext ctx = Cocit.getHttpContext();

		int width = model.get("width", ctx.getClientWindowWidth());
		int height = model.get("height", ctx.getClientWindowHeight());
		int mainTabsHeight = height / 2;
		int childrenTabsHeight = height - mainTabsHeight - 1;

		CuiBizTableModel mainModel = model.getMainBizTableModel();
		List<CuiBizTableModel> children = model.getChildrenBizTableModels();
		if (Lang.isNil(children)) {
			mainTabsHeight = height;
		}

		print(out, "<div id=\"module_%s\" class=\"jCocit-ui jCocit-bizmodule\">", model.getId());

		// biz module model
		// print(out, "<table><tr><td>");

		if (mainModel != null) {

			mainModel.set("width", "" + width);
			mainModel.set("height", "" + mainTabsHeight);

			// tabs
			print(out, "<div class=\"jCocit-ui jCocit-tabs\" data-options=\"\" style=\"width:%spx;height:%spx;\">", width, mainTabsHeight);
			print(out, "<div title=\"%s\" data-options=\"bizTableID: %s\" style=\"padding:5px; overflow:hidden;\">", model.getName(), mainModel.getId());

			mainModel.render(out);

			// tabs end
			print(out, "</div>");
			print(out, "</div>");

		}

		if (!Lang.isNil(children)) {
			// print(out, "</td></tr><tr><td>");

			/*
			 * 子模块TABS
			 */
			// print tabs of children biz table models
			print(out, "<div style=\"height: 5px;\"></div>");
			print(out, "<div class=\"jCocit-ui jCocit-tabs\" data-options=\"\" style=\"width:%spx; height:%spx; \">", width, childrenTabsHeight);

			for (CuiBizTableModel child : children) {
				print(out, "<div title=\"%s\" data-options=\"bizTableID: %s, url: '%s?_windowHeight=%s&_windowWidth=%s',closable: false, cache: true\" style=\"padding:5px\"></div>", child.getName(),
						child.getId(), child.getLoadUrl(), childrenTabsHeight, width);
			}

			print(out, "</div>");
			// 子系统TABS end！

			// 子模块工具菜单——位于主模块TABS右边
			// print(out, "<div id=\"tabtools_%s\">", model.getId());
			// for (CuiBizTableModel child : children) {
			// print(out, "<a href=\"javascript:void(0)\" class=\"jCocit-ui jCocit-button\" data-options=\"plain: false");
			// // print(out, ", iconCls:'icon-add'");
			// print(out, "\">%s</a>", child.getName());
			// }
			// print(out, "</div>");

		}

		// biz module model
		// print(out, "</td></tr></table>");

		print(out, "</div>");

		print(out, "</body>");
		print(out, "</html>");

		writer.write(out.toString());
	}
}
