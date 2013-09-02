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
		String contextPath = Cocit.getContextPath();
		print(out, "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		print(out, "<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		print(out, "<head>");
		print(out, "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		print(out, "<link rel=\"icon\" href=\"\" type=\"image/x-icon\" />");
		print(out, "<link rel=\"shortcut icon\" href=\"\" type=\"image/x-icon\" />");
		print(out, "<title></title>");
		print(out, "<link href=\"%s/jCocit/css/jCocit.min.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />", contextPath);
		print(out, "<script src=\"%s/jCocit/jquery.min.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit/js/jCocit.src.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "<script src=\"%s/jCocit/js/jCocit.nls.zh.js\" type=\"text/javascript\"></script>", contextPath);
		print(out, "</head>");
		print(out, "<body>");

		// print model
		print(out, "<div>");
		CuiBizTableModel mainModel = model.getMainBizTableModel();
		if (mainModel != null) {
			print(out, "<div>");
			mainModel.render(out);
			print(out, "</div>");
		}

		List<CuiBizTableModel> children = model.getChildrenBizTableModels();
		if (Lang.hasContent(children)) {
			// print tabs of children biz table models

		}

		// print model end
		print(out, "</div>");

		print(out, "</body>");
		print(out, "</html>");
	}

}
