package com.jiongsoft.cocit.ui.render.jCocit;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.ui.model.CuiTreeModel;
import com.jiongsoft.cocit.ui.model.CuiTreeModelData;
import com.jiongsoft.cocit.ui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Json;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;

abstract class JCocitTreeRenders {

	static class ModelRender extends BaseCuiRender<CuiTreeModel> {

		@Override
		public void render(Writer out, CuiTreeModel model) throws Throwable {
			if (model.getData() != null) {
				print(out, "<script type=\"text/javascript\">var treedata_%s=", model.get("token", ""));
				new DataRender().outNodes(out, model.getData().getChildren());
				print(out, "</script>");
			}

			// Tree容器：DIV
			print(out, "<div style=\"height:%spx; position: relative; border:1px solid #95B8E7; padding: 2px; overflow: hidden;\">", model.get("height", 300));

			// Tree：id = "tree_" + token
			String token = model.get("token", "");
			print(out, "<ul id=\"tree_%s\" class=\"jCocit-ui jCocit-tree\" data-options=\"", token);

			print(out, "token: '%s'", token);// 导航树Tree通过该令牌查找DataGrid对象
			print(out, ",checkbox: %s", (boolean) model.get("checkbox", true));
			print(out, ",onlyLeafCheck: %s", (boolean) model.get("onlyLeafCheck", false));
			print(out, ",onlyLeafValue: %s", (boolean) model.get("onlyLeafValue", false));

			if (model.getData() == null) {
				print(out, ",url: '%s'", model.getDataLoadUrl());
			} else {
				print(out, ",data: treedata_%s", model.get("token", ""));
			}
			print(out, ",onSelect: jCocit.entity.doTreeSelect");
			print(out, ",onCheck: jCocit.entity.doTreeSelect");

			// print(out, ",lines: %s",model.is("lines"));
			// print(out, ",styleName: 'tree-lines'");

			print(out, "\">");

			print(out, "</ul>");
			print(out, "</div>");
		}
	}

	static class DataRender extends BaseCuiRender<CuiTreeModelData> {

		@Override
		public void render(Writer out, CuiTreeModelData model) throws Throwable {

			Tree tree = model.getData();
			List<Node> nodes = tree.getChildren();
			if (!Lang.isNil(nodes))
				outNodes(out, nodes);
			else {
				print(out, "[]");
			}

		}

		private void outNodes(Writer out, List<Node> nodes) throws IOException {

			print(out, "[");

			boolean noFirst = false;
			for (Node node : nodes) {
				if (noFirst) {
					print(out, ",");
				} else {
					noFirst = true;
				}

				print(out, "{\"id\" : %s", Json.toJson(node.getId()));
				print(out, ",\"text\" : %s", Json.toJson(node.getName()));
				print(out, ",\"checked\" : %s", node.get("checked", false) ? 1 : 0);
				print(out, ",\"open\" : %s", node.get("open", false) ? 1 : 0);
				List<Node> children = node.getChildren();
				if (!Lang.isNil(children)) {
					print(out, ",\"children\" :");

					outNodes(out, children);
				}

				print(out, "}");
			}

			print(out, "]");
		}
	}
}