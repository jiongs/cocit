package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.cocui.model.CuiBizModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Lang;

class JCocitBizModuleRender extends BaseCuiRender<CuiBizModuleModel> {

	@Override
	public void render(Writer out, CuiBizModuleModel model) throws Throwable {
		int width = model.get("width", 1000);
		int height = model.get("height", 800);
		int tabsHeight = height / 2;

		String contextPath = Cocit.getContextPath();
		print(out, "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		print(out, "<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		print(out, "<head>");
		print(out, "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		print(out, "<link rel=\"icon\" href=\"\" type=\"image/x-icon\" />");
		print(out, "<link rel=\"shortcut icon\" href=\"\" type=\"image/x-icon\" />");
		print(out, "<title></title>");

		print(out, "<link href=\"%s/jCocit/css/jCocit.min.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		print(out, "<link href=\"%s/jCocit-src/css/jCocit.ui.window.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);

		print(out, "<script src=\"%s/jCocit/jquery.min.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit/js/jCocit.src.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.plugin.bizmodule.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.button.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.menu.js\" type=\"text/javascript\"></script>", contextPath);
		// print(out, "<script src=\"%s/jCocit-src/js/jCocit.ui.tabs.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit-src/js/jCocit.nls.zh.js\" type=\"text/javascript\"></script>", contextPath);

		print(out, "</head>");
		print(out, "<body>");

		print(out, "<div id=\"module_%s\" class=\"jCocit-ui jCocit-bizmodule\">", model.getId());

		// biz module model
		print(out, "<table><tr><td>");

		CuiBizTableModel mainModel = model.getMainBizTableModel();
		if (mainModel != null) {

			mainModel.set("width", "" + (width));
			mainModel.set("height", "" + tabsHeight);

			// tabs
			print(out, "<div class=\"jCocit-ui jCocit-tabs\" data-options=\"\" style=\"width:%spx;height:%spx;\">", width, tabsHeight);

			print(out, "<div title=\"%s\" data-options=\"bizTableID: %s\" style=\"padding:5px\">", mainModel.getName(), mainModel.getId());
			mainModel.render(out);
			print(out, "</div>");

			// tabs end
			print(out, "</div>");

		}

		List<CuiBizTableModel> children = model.getChildrenBizTableModels();
		if (!Lang.isNil(children)) {
			print(out, "</td></tr><tr><td>");
			// print tabs of children biz table models

			print(out, "<div class=\"jCocit-ui jCocit-tabs\" data-options=\"\" style=\"width:%spx; height:%spx; \">", width, tabsHeight);

			for (CuiBizTableModel child : children) {
				print(out, "<div title=\"%s\" data-options=\"bizTableID: %s, url: '%s',closable: false, cache: true\" style=\"padding:5px\"></div>", child.getName(), child.getId(), child.getLoadUrl());
			}

			print(out, "</div>");

		}

		// biz module model
		print(out, "</td></tr></table>");

		print(out, "</div>");

		print(out, "</body>");
		print(out, "</html>");
	}
}
