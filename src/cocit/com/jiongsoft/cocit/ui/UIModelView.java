package com.jiongsoft.cocit.ui;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

import com.jiongsoft.cocit.util.Log;

/**
 * 该类是 Nutz MVC 模型中 View 的实现，用于将 {@link UIModel}的输出工作分派到对应的{@link UIRender}。
 * 
 * @author jiongs753
 * 
 */
public class UIModelView implements View {
	public static final String VIEW_TYPE = "coc";

	private static UIModelView me;

	public static UIModelView make() {
		synchronized (UIModelView.class) {
			if (me == null) {
				me = new UIModelView();
			}
			return me;
		}
	}

	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Throwable {
		if (obj == null)
			return;

		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", -1);

		PrintWriter out = null;

		try {
			out = resp.getWriter();

			if (obj instanceof UIModel) {
				UIModel model = (UIModel) obj;

				resp.setContentType(model.getContentType());

				StringWriter str = new StringWriter();

				try {
					model.render(str);

					out.write(str.toString());
				} catch (Throwable ex) {
					Log.error("", ex);

					ex.printStackTrace(out);
				}

			} else {
				if (obj instanceof Throwable) {
					Throwable ex = (Throwable) obj;

					resp.setContentType("text/html; charset=UTF-8");

					Log.error("", ex);

					ex.printStackTrace(out);

				} else
					Log.error("UIModelRenderView.render: 不支持的输出类型！{type:%s}", obj == null ? "<NULL>" : obj.getClass().getName());
			}
		} finally {
			resp.flushBuffer();
			try {
				if (out != null)
					out.close();
			} catch (Throwable e) {
				Log.warn("", e);
			}
		}
	}

}
