package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModelData;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Json;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;

abstract class JCocitTreeRenders {

	static class ModelRender extends BaseCuiRender<CuiTreeModel> {

		@Override
		public void render(Writer out, CuiTreeModel model) throws Throwable {

			print(out, "<div style=\"width:%spx; height:%spx; position: relative; border:1px solid #95B8E7; padding: 2px; overflow: hidden;background-color: #F4F4F4\">", model.get("width", 240),
					model.get("height", 300));
			print(out, "<ul id=\"tree_%s\" tableID=\"%s\" class=\"jCocit-ui jCocit-tree\" data-options=\"", //
					model.get("token", ""), model.getId());

			print(out, "url: '%s'", model.getDataLoadUrl());
			// print(out, ",lines: %s",model.is("lines"));
			// print(out, ",styleName: 'tree-lines'");
			print(out, ",checkbox: %s", (boolean) model.get("checkbox", true));

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
