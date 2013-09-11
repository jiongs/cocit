package com.jiongsoft.cocit.ui.widget.jcocitrender;

import java.io.Writer;

import com.jiongsoft.cocit.ui.widget.EntityFormWidgetData;
import com.jiongsoft.cocit.ui.widget.WidgetRender;
import com.jiongsoft.cocit.utils.Json;

public class JCocitFormDataRender extends WidgetRender<EntityFormWidgetData> {

	@Override
	public void render(Writer writer, EntityFormWidgetData model) throws Throwable {

		String message = "操作成功！";
		int statusCode = 200;

		Throwable ex = model.getException();
		if (ex != null) {
			statusCode = 300;
			message = ex.getMessage();
		}

		StringBuffer sb = new StringBuffer();
		sb.append('{');
		sb.append("\"statusCode\" : " + statusCode);
		sb.append(", \"content\" : " + Json.toJson(message));
		sb.append(", \"data\": ").append(Json.toJson(model.getData()));
		sb.append('}');
		print(writer, sb.toString());
	}
}
