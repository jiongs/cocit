package com.jiongsoft.cocit.cocui;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

import com.jiongsoft.cocit.utils.Log;

/**
 * 该类是 Nutz MVC 模型中 View 的实现，用于将 {@link CuiModel}的输出工作分派到对应的{@link CuiRender}。
 * 
 * @author jiongs753
 * 
 */
public class CuiModelView implements View {
	public static final String VIEW_TYPE = "coc";

	private static CuiModelView me;

	public static CuiModelView make() {
		synchronized (CuiModelView.class) {
			if (me == null) {
				me = new CuiModelView();
			}
			return me;
		}
	}

	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Throwable {
		if (obj instanceof CuiModel) {
			CuiModel model = (CuiModel) obj;

			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", -1);
			resp.setContentType(model.getContentType());

			Writer out = null;
			try {
				out = resp.getWriter();

				model.render(out);
			} finally {
				resp.flushBuffer();
				try {
					if (out != null)
						out.close();
				} catch (Throwable iglore) {
				}
			}
		} else {
			Log.error("UIModelRenderView.render: 不支持的输出类型！{type:%s}", obj == null ? "<NULL>" : obj.getClass().getName());
		}
	}

}
