package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Lang;

abstract class JCocitGridRenders {

	static class ModelRender extends BaseCuiRender<CuiGridModel> {

		@Override
		public void render(Writer out, CuiGridModel model) throws Throwable {

			print(out, "<table class=\"jCocit-ui jCocit-datagrid\" title=\"%s\" style=\"width: 700px; height: 250px\" data-options=\"", model.getName());
			print(out, "singleSelect: false,");
			print(out, "collapsible: true,");
			print(out, "rownumbers: true,");
			print(out, "autoRowHeight: false,");
			print(out, "pagination: true,");
			print(out, "pageSize: %s,", model.getPageSize());
			print(out, "url: '%s'", model.getDataLoadUrl());
			print(out, "\">");
			print(out, "<thead>");
			print(out, "<tr>");

			List<GridColumn> columns = model.getColumns();

			print(out, "<th data-options=\"field:'id',checkbox:true\"></th>");
			for (GridColumn col : columns) {
				print(out, "<th data-options=\"field:'%s', width:%s ,align:'%s'\">%s</th>", col.getField(), col.getWidth(), col.getAlign(), col.getTitle());
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

					sb.append(String.format("\"id\":%s", Lang.getValue(obj, "id")));
					for (GridColumn col : columns) {
						String prop = col.getField();
						Object value = Lang.getValue(obj, prop);
						value = Lang.format(value, col.getPattern());
						if (value == null)
							value = "";

						sb.append(String.format(",\"%s\":\"%s\"", prop, value));
					}

					sb.append("}");

					print(out, sb.toString());
				}
			}

			print(out, "]}");
		}
	}
}
