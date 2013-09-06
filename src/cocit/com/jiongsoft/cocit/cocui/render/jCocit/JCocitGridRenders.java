package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Json;
import com.jiongsoft.cocit.utils.Lang;

abstract class JCocitGridRenders {

	static class ModelRender extends BaseCuiRender<CuiGridModel> {

		@Override
		public void render(Writer out, CuiGridModel model) throws Throwable {
			String title = "";// model.getName()

			// Grid: id = "datagrid_" + token
			print(out, "<table id=\"datagrid_%s\" class=\"jCocit-ui jCocit-datagrid\" title=\"%s\" style=\"height: %spx;\" data-options=\"",//
					model.get("token", ""), title, model.get("height", 353));
			print(out, "url: '%s'", model.getDataLoadUrl());
			print(out, ",rownumbers: true");
			print(out, ",sortField: 'id'");
			print(out, ",pagination: true");
			print(out, ",pageSize: %s", model.getPageSize());

			/*
			 * 引用工具栏菜单ID
			 */
			// 使用HTML菜单
			print(out, ",toolbar: '#toolbar_%s'", model.get("token", ""));
			// 使用JS菜单
			// print(out, ",toolbar: toolbar_%s", model.get("token", ""));

			print(out, ",pageButtons:[");
			print(out, "{title: '系统设置', iconCls: 'icon-setting', onClick:jCocit.bizmodule.doSetting}");
			print(out, "]");

			print(out, "\">");
			print(out, "<thead>");
			print(out, "<tr>");

			List<GridColumn> columns = model.getColumns();

			print(out, "<th data-options=\"field: 'id', checkbox:true\"></th>");
			for (GridColumn col : columns) {
				int w = col.getWidth();
				print(out, "<th data-options=\"field: '%s', width: %s, align: '%s'\">%s</th>", col.getField(), w, col.getAlign(), col.getTitle());
			}
			print(out, "</tr>");
			print(out, "</thead>");
			print(out, "</table>");
		}
	}

	static class DataRender extends BaseCuiRender<CuiGridModelData> {

		@Override
		public void render(Writer out, CuiGridModelData model) throws Throwable {
			print(out, "{\"total\":%s,\"rows\":[", model.getTotal());

			CuiGridModel gridModel = model.getModel();
			List<GridColumn> columns = gridModel.getColumns();
			List data = model.getData();
			if (data != null) {

				boolean noFirstRow = false;
				for (Object obj : data) {

					StringBuffer sb = new StringBuffer();
					if (noFirstRow) {
						sb.append(",{");
					} else {
						sb.append("{");
						noFirstRow = true;
					}

					sb.append(String.format("\"id\":%s", Json.toJson(Lang.getValue(obj, "id"))));
					for (GridColumn col : columns) {
						String prop = col.getField();
						Object value = Lang.getValue(obj, prop);
						value = Lang.format(value, col.getPattern());
						if (value == null)
							value = "";

						sb.append(String.format(",\"%s\":%s", prop, Json.toJson(value)));
					}

					sb.append("}");

					print(out, sb.toString());
				}
			}

			print(out, "]}");
		}
	}
}
