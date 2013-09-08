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
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocui.model.CuiFormModel;
import com.jiongsoft.cocit.cocui.model.CuiFormModel.FormField;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.StringUtil;

public class JCocitFormRender extends BaseCuiRender<CuiFormModel> {

	@Override
	public void render(Writer writer, CuiFormModel model) throws Throwable {
		StringBuffer sb = new StringBuffer();

		Object formData = model.getData();
		print(sb, "<form class=\"bizform\">");
		print(sb, "<input name=\"data.id\" type=\"hidden\" value=\"%s\">", Lang.idOrtoString(formData));

		CocBizField bizfield;
		String propName;
		Object objFldValue;
		/*
		 * 计算可见字段、隐藏字段、不显示的字段等
		 */
		for (FormField group : model.getGroupFields()) {
			List<FormField> fields = group.getChildren();
			for (FormField field : fields) {

				bizfield = field.getBizField();
				propName = bizfield.getPropName();
				objFldValue = Lang.getValue(formData, propName);
				String strFldValue = Lang.idOrtoString(objFldValue);
				String mode = field.getMode();

				// N：不显示
				if (mode.equals("N"))
					continue;

				// P：字段有值，则显示
				if (mode.equals("P") && (StringUtil.isNil(strFldValue)))
					continue;

				// H：隐藏字段
				if (mode.equals("H")) {
					print(sb, "\"<input type=\"hidden\" name=\"data.%s\" value=\"%s\" />\"", propName, strFldValue);
					continue;
				}

				// 需显示的字段
				group.addVisibleChild(field);
			}
		}

		print(sb, "<div class=\"bizgroups\">");
		for (FormField group : model.getGroupFields()) {
			List<FormField> visibleFields = group.getVisibleChildren();

			// 该分组中没有需要显示的字段
			if (visibleFields.size() == 0)
				continue;

			print(sb, "<div class=\"bizgroup\"><div class=\"bizgroup_header\">%s</div>", group.getTitle());

			// fields table
			print(sb, "<table valign=\"top\" width=\"100%%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");

			for (FormField field : visibleFields) {

				bizfield = field.getBizField();
				propName = bizfield.getPropName();
				objFldValue = Lang.getValue(formData, propName);
				String strFldValue = Lang.idOrtoString(objFldValue);

				String mode = field.getMode();

				print(sb, "<tr>");
				print(sb, "<th class=\"bizfield_header\">");

				printFieldLabel(sb, model, bizfield);

				print(sb, "</td>");
				print(sb, "<td class=\"bizfield_box\">");

				// I：检查字段值，字段值被隐藏且可以提交。
				if (mode.equals("I")) {
					print(sb, "\"<input type=\"hidden\" name=\"data.%s\" value=\"%s\" />\"", propName, strFldValue);
					print(sb, "<span>%s</span>", bizfield.format(objFldValue));
				}
				// R：只读字段显示的是字段文本，隐藏的是字段值。
				else if (mode.equals("R")) {
					print(sb, "\"<input type=\"hidden\" name=\"data.%s\" value=\"%s\" />\"", propName, strFldValue);
					print(sb, "\"<input value=\"%s\" readonly=\"true\" />\"", propName, bizfield.format(objFldValue));
				}
				// D：禁用字段，字段值不能被提交
				else if (mode.equals("D")) {
					print(sb, "\"<input value=\"%s\" disabled=\"true\" />\"", propName, bizfield.format(objFldValue));
				}
				// S：显示字段值文本
				else if (mode.equals("P") || mode.equals("S")) {
					print(sb, "<span>%s</span>", bizfield.format(objFldValue));
				}
				// E：编辑模式
				else if (mode.equals("E") || mode.equals("M")) {
					printEditBox(sb, model, bizfield, objFldValue);

					// M：字段必填
					if (mode.equals("M")) {
						print(sb, "<span class=\"icon-mode-%s\">&nbsp;&nbsp;&nbsp;&nbsp;</span>", mode);
					}
				}

				print(sb, "</td>");
				print(sb, "</tr>");
			}

			print(sb, "</table></div>");// end bizgroup
		}

		print(sb, "</div></form>");

		print(writer, sb.toString());
	}

	private void printFieldLabel(StringBuffer sb, CuiFormModel model, CocBizField bizField) throws IOException {
		print(sb, bizField.getName());
	}

	private void printEditBox(StringBuffer sb, CuiFormModel model, CocBizField bizField, Object fldvalue) throws IOException {

		byte type = bizField.getType();
		switch (type) {
		case TYPE_NUMBER:
			printNum(sb, bizField, (Number) fldvalue);
			break;
		case TYPE_DATE:
			printDate(sb, bizField, (Date) fldvalue);
			break;
		case TYPE_TEXT:
			printText(sb, bizField, fldvalue);
			break;
		case TYPE_BOOL:
			printDic(sb, bizField, (Boolean) fldvalue);
			break;
		case TYPE_UPLOAD:
			break;
		case TYPE_FK:
			this.printFK(sb, bizField, fldvalue);
			break;
		case TYPE_RICH_TEXT:
			printText(sb, bizField, fldvalue);
			break;
		default:
			printStr(sb, bizField, (String) fldvalue);
		}
	}

	private boolean printDic(StringBuffer sb, CocBizField fieldDesc, Object fieldValue) {
		KeyValue[] options = fieldDesc.getDicOptions();

		if (options == null || options.length == 0)
			return false;

		KeyValue selected = fieldDesc.getDicOption(fieldValue);
		String selectedValue = selected == null ? "" : selected.getValue();

		// Combobox
		// print(sb, "<select name=\"data.%s\" value=\"%s\" class=\"jCocit-ui jCocit-combobox\" data-options=\"width:202\">", fieldDesc.getPropName(),
		// (selectedItem == null ? "" : selectedItem.getValue()));

		// Select
		print(sb, "<select name=\"data.%s\" value=\"%s\" class=\"select\">", fieldDesc.getPropName(), selectedValue);
		print(sb, "<option value=\"\">--请选择--</option>");

		// options
		for (KeyValue option : options) {
			print(sb, "<option %s value=\"%s\">%s</option>", ((option.getValue().equals(selectedValue)) ? "selected" : ""), option.getValue(), option.getKey());
		}
		print(sb, "</select>");

		return true;
	}

	private void printStr(StringBuffer sb, CocBizField fieldDesc, String fieldValue) {
		boolean isDic = this.printDic(sb, fieldDesc, fieldValue);
		if (!isDic) {
			String str = fieldValue == null ? "" : fieldValue.toString();
			print(sb, "<input name=\"data.%s\" value=\"%s\" class=\"input\" />", fieldDesc.getPropName(), str);
		}
	}

	private void printText(StringBuffer sb, CocBizField fieldDesc, Object fieldValue) {
		String str = fieldValue == null ? "" : fieldValue.toString();
		print(sb, "<textarea name=\"data.%s\" class=\"textarea\">%s</textarea>", fieldDesc.getPropName(), str);
	}

	private void printDate(StringBuffer sb, CocBizField fieldDesc, Date fieldValue) {
		boolean isDic = this.printDic(sb, fieldDesc, fieldValue);
		if (!isDic) {
			String str = Lang.format(fieldValue, fieldDesc.getPattern());
			print(sb, "<input name=\"data.%s\" value=\"%s\" class=\"jCocit-ui jCocit-combodate\" data-options=\"width:202\"/>", fieldDesc.getPropName(), str);
		}
	}

	private void printNum(StringBuffer sb, CocBizField fieldDesc, Number fieldValue) {
		boolean isDic = this.printDic(sb, fieldDesc, fieldValue);
		if (!isDic) {
			String str = Lang.format(fieldValue, fieldDesc.getPattern());
			print(sb, "<input name=\"data.%s\" value=\"%s\" class=\"input jCocit-ui jCocit-numberbox\" data-options=\"precision:%s,groupSeparator:','\"/>", fieldDesc.getPropName(), str,
					fieldDesc.getScale());
		}
	}

	private void printFK(StringBuffer sb, CocBizField fieldDesc, Object fieldValue) {
		CocBizTable bizTable = fieldDesc.getFkBizTable();
		CuiGridModel gridModel = Cocit.getCuiModelFactory().getGridModel(null, bizTable);
		List<GridColumn> columns = gridModel.getColumns();

		String id = Lang.idOrtoString(fieldValue);
		String text = fieldValue == null ? "" : fieldValue.toString();
		print(sb, "<input name=\"data.%s.id\" class=\"jCocit-ui jCocit-combogrid\" data-options=\"value:'%s',text:'%s',width:202,", fieldDesc.getPropName(), id, text);
		print(sb, "panelWidth: 600,");
		print(sb, "panelHeight: 300,");
		print(sb, "idField: 'id',");
		print(sb, "textField: '%s',", columns.get(0).getField());
		print(sb, "url: '%s',", gridModel.getDataLoadUrl());
		print(sb, "rownumbers: true,");
		print(sb, "pagination: true,");
		print(sb, "pageSize: %s,", 20);
		print(sb, "columns: [[");
		print(sb, "{field:'id',title:'ID',width:80,align:'right'},");

		int count = 0;
		for (GridColumn col : columns) {
			print(sb, "{field:'%s',title:'%s',width:%s,sortable:true,align:'%s'},", col.getField(), col.getTitle(), col.getWidth(), col.getAlign());
			count++;
			if (count == 4)
				break;
		}

		print(sb, "]],");
		print(sb, "fitColumns: true");
		print(sb, "\" />");
	}
}
