package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.cocui.model.CuiMenuModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.StringUtil;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;

@SuppressWarnings("unused")
class JCocitMenuRender extends BaseCuiRender<CuiMenuModel> {

	@Override
	public void render(Writer out, CuiMenuModel model) throws Throwable {
		// this.printJSMenu(out, model);
		printHtmlMenu(out, model);
	}

	private void printHtmlMenu(Writer out, CuiMenuModel model) throws IOException {
		Tree tree = model.getData();

		// 菜单ID与GridRender中输出的工具栏ID相同，都是BizTable ID。
		print(out, "<div id=\"toolbar_%s\" tableID=\"%s\" style=\"padding:2px; height: auto; background-color: #dfeffc;\">", //
				model.get("token", ""), model.getId());

		List<Node> nodes = tree.getChildren();
		if (!Lang.isNil(nodes)) {

			// toolbar
			print(out, "<div style=\"margin: 1px 0 1px 0\">");

			for (Node node : nodes) {
				print(out, "<a href=\"javascript:void(0)\" class=\"jCocit-ui jCocit-menubar\" data-options=\"");
				print(out, "opID: '%s'", node.getId());

				String value = node.get("moduleID", "");
				if (!StringUtil.isNil(value))
					print(out, ", moduleID: %s", value);

				value = node.get("tableID", "");
				if (!StringUtil.isNil(value))
					print(out, ", tableID: %s", value);

				value = node.get("operationMode", "");
				if (!StringUtil.isNil(value))
					print(out, ", opMode: '%s'", value);

				value = node.get("operationCode", "");
				if (!StringUtil.isNil(value)) {
					print(out, ", opCode: %s", value);
					print(out, ", iconCls: 'icon-%s'", value);// iconCls 由菜单操作码决定
				}

				// 子菜单
				if (node.size() > 0) {
					print(out, ", menu: '#submenu_%s_%s'", model.get("token", ""), node.getId());
				} else {
					print(out, ", onClick: jCocit.bizmodule.doAction");
				}

				print(out, "\">%s</a>", node.getName());
			}
			print(out, "</div>");

			// sub menu
			for (Node node : nodes) {
				if (node.size() > 0)
					printHtmlSubMenu(out, model, node);
			}
		}

		print(out, "</div>");
	}

	private void printHtmlSubMenu(Writer out, CuiMenuModel model, Node node) throws IOException {
		print(out, "<div id=\"submenu_%s_%s\" data-options=\"onClick: jCocit.bizmodule.doAction\" style=\"width:120px;\">", //
				model.get("token", ""), node.getId());

		for (Node child : node.getChildren()) {
			print(out, "<div data-options=\"opID: '%s'", child.getId());

			String value = node.get("moduleID", "");
			if (!StringUtil.isNil(value))
				print(out, ", moduleID: %s", value);

			value = node.get("tableID", "");
			if (!StringUtil.isNil(value))
				print(out, ", tableID: %s", value);

			value = child.get("operationMode", "");
			if (!StringUtil.isNil(value))
				print(out, ", opMode: '%s'", value);

			value = child.get("operationCode", "");
			if (!StringUtil.isNil(value)) {
				print(out, ", iconCls: 'icon-%s'", value);
				print(out, ", opCode: %s", value);
			}

			print(out, "\"><span>%s</span>", child.getName());

			// 输出子菜单
			if (child.size() > 0) {
				printHtmlSubMenu(out, model, child);
			}

			print(out, "</div>");
		}

		print(out, "</div>");
	}

	private void printJSMenu(Writer out, CuiMenuModel model) throws IOException {
		print(out, "<script >var toolbar%s = [", model.getId());

		Tree tree = model.getData();
		List<Node> nodes = tree.getChildren();
		;
		if (!Lang.isNil(nodes)) {
			boolean notFirst = false;
			for (Node node : nodes) {
				if (notFirst) {
					print(out, ",");
				} else {
					notFirst = true;
				}

				print(out, "{text: '%s'", node.getName());
				String opCode = node.get("operationCode", "");
				print(out, ",iconCls: 'icon-%s'", opCode);
				print(out, ", handler:function(){alert('%s')}", opCode);
				print(out, "}");

			}
		}

		print(out, "]</script >");
	}
}
