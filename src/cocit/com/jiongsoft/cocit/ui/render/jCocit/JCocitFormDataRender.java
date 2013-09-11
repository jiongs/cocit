package com.jiongsoft.cocit.ui.render.jCocit;

import java.io.Writer;

import com.jiongsoft.cocit.ui.model.CuiFormModelData;
import com.jiongsoft.cocit.ui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.Json;

public class JCocitFormDataRender extends BaseCuiRender<CuiFormModelData> {

	@Override
	public void render(Writer writer, CuiFormModelData model) throws Throwable {

		String message = "操作成功！";
		int statusCode = 200;

		CocException ex = model.getException();
		if (ex != null) {
			statusCode = 300;
			message = ex.getMessage();
		}

		StringBuffer sb = new StringBuffer();
		sb.append('{');
		sb.append("\"statusCode\" : " + statusCode);
		sb.append(", \"message\" : " + Json.toJson(message));
		sb.append(", \"data\": ").append(Json.toJson(model.getData()));
		sb.append('}');
		print(writer, sb.toString());
	}
}
