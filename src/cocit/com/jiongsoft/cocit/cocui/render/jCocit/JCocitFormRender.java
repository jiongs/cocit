package com.jiongsoft.cocit.cocui.render.jCocit;

import static com.jiongsoft.cocit.cocsoft.CocBizField.TYPE_BOOL;
import static com.jiongsoft.cocit.cocsoft.CocBizField.TYPE_DATE;
import static com.jiongsoft.cocit.cocsoft.CocBizField.TYPE_FK;
import static com.jiongsoft.cocit.cocsoft.CocBizField.TYPE_NUMBER;
import static com.jiongsoft.cocit.cocsoft.CocBizField.TYPE_RICH_TEXT;
import static com.jiongsoft.cocit.cocsoft.CocBizField.TYPE_TEXT;
import static com.jiongsoft.cocit.cocsoft.CocBizField.TYPE_UPLOAD;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.cocui.model.CuiFormModel;
import com.jiongsoft.cocit.cocui.model.CuiFormModel.FormField;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Lang;

public class JCocitFormRender extends BaseCuiRender<CuiFormModel> {

	@Override
	public void render(Writer out, CuiFormModel model) throws Throwable {
		print(out, "<form><table width=\"100%%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");

		List<FormField> fields = model.getFields();
		if (fields != null) {
			for (FormField field : fields) {
				print(out, "<tr>");
				print(out, "<th width=\"120\">");
				printInputLabel(out, model, field);
				print(out, "</td>");
				print(out, "<td>");
				printInputBox(out, model, field);
				print(out, "</td>");
				print(out, "</tr>");
			}
		}

		print(out, "</table></form>");
	}

	private void printInputLabel(Writer out, CuiFormModel model, FormField field) throws IOException {
		print(out, field.getTitle());
	}

	private void printInputBox(Writer out, CuiFormModel model, FormField field) throws IOException {
		Object obj = model.getData();
		String prop = field.getField();
		Object value = Lang.getValue(obj, prop);
		switch (field.getType()) {
		case TYPE_NUMBER:
			print(out, "<input name=\"%s\" value=\"%s\" class=\"jCocit-ui jCocit-numberbox\" data-options=\"precision:2,groupSeparator:','\"/>"//
					, prop//
					, (value == null ? "" : value.toString())// value
			);
			break;
		case TYPE_DATE:
			print(out, "<input name=\"%s\" class=\"jCocit-ui jCocit-datebox\" value=\"%s\" data-options=\"\"/>"//
					, prop//
					, (value == null ? "" : value.toString())// value
			);
			break;
		case TYPE_TEXT:
			print(out, "<textarea name=\"%s\">%s</textarea>"//
					, prop//
					, (value == null ? "" : value.toString())// value
			);
			break;
		case TYPE_BOOL:
			break;
		case TYPE_UPLOAD:
			break;
		case TYPE_FK:
			break;
		case TYPE_RICH_TEXT:
			print(out, "<textarea name=\"%s\">%s</textarea>"//
					, prop//
					, (value == null ? "" : value.toString())// value
			);
			break;
		default:
			print(out, "<input name=\"%s\" value=\"%s\"/>"//
					, prop//
					, (value == null ? "" : value.toString())// value
			);
		}
	}
}
