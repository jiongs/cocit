package com.jiongsoft.cocit.ui.render.jcocit;

import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.ui.model.widget.GridWidgetData;
import com.jiongsoft.cocit.ui.model.widget.GridWidgetModel;
import com.jiongsoft.cocit.ui.model.widget.GridWidgetModel.GridColumn;
import com.jiongsoft.cocit.ui.render.WidgetRender;
import com.jiongsoft.cocit.util.Json;
import com.jiongsoft.cocit.util.ObjectUtil;

public abstract class JCocitGridRenders {

	public static class ModelRender extends WidgetRender<GridWidgetModel> {

		@Override
		public void render(Writer out, GridWidgetModel model) throws Throwable {
			String title = "";// model.getName()
			int height = model.get("height", 353);
			int colTotalWidth = model.getColumnsTotalWidth();
			int width = model.get("width", colTotalWidth) - 90;
			double fixColRate = 1.0;
			if (width > colTotalWidth) {
				fixColRate = new Double(width) / new Double(colTotalWidth);
			}

			// Grid: id = "datagrid_" + token
			String token = model.get("token", "");
			print(out, "<table id=\"datagrid_%s\" class=\"jCocit-ui jCocit-datagrid\" title=\"%s\" style=\"height: %spx;\" data-options=\"",//
					token, title, height);
			print(out, "token: '%s'", token);// 主表Grid通过该令牌获取子表Tabs对象，以便于选中主表记录后刷新Tabs中当前子表的Grid
			print(out, ",url: '%s'", model.getDataLoadUrl());
			print(out, ",rownumbers: true");
			print(out, ",sortField: 'id'");
			print(out, ",sortOrder: 'desc'");
			// print(out, ",fitColumns: true");
			print(out, ",pagination: true");
			print(out, ",singleSelect: true");
			print(out, ",selectOnCheck: false");
			print(out, ",checkOnSelect: false");
			print(out, ",onSelect: jCocit.entity.doGridSelect");
			print(out, ",onCheck: jCocit.entity.doGridSelect");
			print(out, ",onUncheck: jCocit.entity.doGridSelect");
			print(out, ",onCheckAll: jCocit.entity.doGridSelect");
			print(out, ",onUncheckAll: jCocit.entity.doGridSelect");
			print(out, ",onBeforeLoad: jCocit.entity.doGridBeforeLoad");
			print(out, ",onHeaderContextMenu: jCocit.entity.doGridHeaderContextMenu");
			print(out, ",pageSize: %s", model.getPageSize());

			/*
			 * 引用工具栏菜单ID
			 */
			// 使用HTML菜单
			print(out, ",toolbar: '#toolbar_%s'", model.get("token", ""));
			// 使用JS菜单
			// print(out, ",toolbar: toolbar_%s", model.get("token", ""));

			print(out, ",pageButtons:[");
			print(out, "{title: '系统设置', iconCls: 'icon-setting', token:'%s', onClick:jCocit.entity.doSetting}", token);
			print(out, "]");

			print(out, "\">");
			print(out, "<thead>");
			print(out, "<tr>");

			List<GridColumn> columns = model.getColumns();

			print(out, "<th data-options=\"field: 'id', checkbox:true\"></th>");
			int colWidth;
			for (GridColumn col : columns) {
				colWidth = col.getWidth();
				if (fixColRate > 1)
					colWidth = new Double(colWidth * fixColRate).intValue();

				print(out, "<th data-options=\"field: '%s', width: %s, sortable: true, align: '%s'\">%s</th>", col.getField(), colWidth, col.getAlign(), col.getTitle());
			}
			print(out, "</tr>");
			print(out, "</thead>");
			print(out, "</table>");
		}
	}

	public static class DataRender extends WidgetRender<GridWidgetData> {

		@Override
		public void render(Writer out, GridWidgetData model) throws Throwable {
			print(out, "{\"total\":%s,\"rows\":[", model.getTotal());

			GridWidgetModel gridModel = model.getModel();
			List<GridColumn> columns = gridModel.getColumns();
			List data = model.getData();
			if (data != null) {

				boolean noFirstRow = false;
				for (Object obj : data) {

					StringBuffer sb = new StringBuffer();
					if (noFirstRow) {
						sb.append(",{");
					} else {
						sb.append('{');
						noFirstRow = true;
					}

					sb.append(String.format("\"id\":%s", Json.toJson(ObjectUtil.getValue(obj, "id"))));
					for (GridColumn col : columns) {
						String prop = col.getField();
						Object value = ObjectUtil.getValue(obj, prop);
						value = col.getEntityField().format(value);
						if (value == null)
							value = "";

						sb.append(String.format(",\"%s\":%s", prop, Json.toJson(value)));
					}

					sb.append('}');

					print(out, sb.toString());
				}
			}

			print(out, "]}");
		}
	}
}
