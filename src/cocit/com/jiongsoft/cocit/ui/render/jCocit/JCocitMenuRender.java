package com.jiongsoft.cocit.ui.render.jCocit;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.ui.model.CuiMenuModel;
import com.jiongsoft.cocit.ui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.ui.render.BaseCuiRender;
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

	private void printHtmlMenu(Writer out, CuiMenuModel model) throws Throwable {
		Tree tree = model.getData();

		// 工具栏容器：DIV
		print(out, "<div id=\"toolbar_%s\" style=\"padding:2px; height: auto;\">", model.get("token", ""));

		/*
		 * 用一个Table将工具栏容器分成两部分：1.左边为工具栏菜单，2.右边为搜索框。
		 */
		print(out, "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%%\"><tr><td width=\"5\" nowrap>");

		/*
		 * 1.左边为工具栏菜单
		 */
		List<Node> nodes = tree.getChildren();
		if (!Lang.isNil(nodes)) {

			// toolbar
			print(out, "<div style=\"margin: 1px 0 1px 0\">");

			for (Node node : nodes) {
				print(out, "<a href=\"javascript:void(0)\" class=\"jCocit-ui jCocit-toolbar\" data-options=\"");
				print(out, "name:'%s'", node.getName());

				// token: 用来关联到导航树（tree_????）和DataGrid（datagrid_???）。???表示token。
				String str = model.get("token", "");
				if (!StringUtil.isNil(str))
					print(out, ", token: '%s'", str);// 菜单通过该令牌获取DataGrid对象

				// pathArgs = entityModuleID:entityTableID:entityOperationID
				str = node.get("pathArgs", "");
				if (!StringUtil.isNil(str))
					print(out, ", pathArgs: '%s'", str);

				str = node.get("operationMode", "");
				if (!StringUtil.isNil(str))
					print(out, ", opMode: '%s'", str);

				str = node.get("operationCode", "");
				if (!StringUtil.isNil(str)) {
					print(out, ", opCode: %s", str);
					print(out, ", iconCls: 'icon-%s'", str);// iconCls 由菜单操作码决定
				}

				// 子菜单
				if (node.size() > 0) {
					print(out, ", menu: '#submenu_%s_%s'", model.get("token", ""), node.getId());
				} else {
					print(out, ", onClick: jCocit.entity.doAction");
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

		/*
		 * 2.右边为搜索框
		 */
		CuiSearchBoxModel searchMode = model.getSearchBoxModel();
		if (searchMode != null) {
			print(out, "</td><td align=\"left\" style=\"padding-left: 5px;\">");

			searchMode.set("token", model.get("token", ""));
			searchMode.render(out);

		}

		print(out, "</td></tr></table>");

		print(out, "</div>");
	}

	private void printHtmlSubMenu(Writer out, CuiMenuModel model, Node node) throws IOException {
		print(out, "<div id=\"submenu_%s_%s\" data-options=\"onClick: jCocit.entity.doAction\" style=\"width:120px;\">", //
				model.get("token", ""), node.getId());

		for (Node child : node.getChildren()) {
			print(out, "<div data-options=\"", child.getId());
			print(out, "name:'%s'", node.getName());

			// pathArgs = entityModuleID:entityTableID:entityOperationID
			String str = node.get("pathArgs", "");
			if (!StringUtil.isNil(str))
				print(out, ", pathArgs: '%s'", str);

			str = model.get("token", "");
			if (!StringUtil.isNil(str))
				print(out, ", token: '%s'", str);

			str = child.get("operationCode", "");
			if (!StringUtil.isNil(str)) {
				print(out, ", iconCls: 'icon-%s'", str);
				print(out, ", opCode: %s", str);
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
		print(out, "<script type=\"text/javascript\">var toolbar_%s = [", model.get("token", ""));

		Tree tree = model.getData();
		List<Node> nodes = tree.getAll();

		if (!Lang.isNil(nodes)) {
			boolean notFirst = false;
			for (Node node : nodes) {

				if (notFirst) {
					print(out, ",");
				} else {
					notFirst = true;
				}

				if (node.size() > 0) {

					print(out, "'-'");

					continue;
				}

				print(out, "{text: '%s', opID: '%s'", node.getName(), node.getId());
				String value = node.get("entityModuleID", "");
				if (!StringUtil.isNil(value))
					print(out, ", entityModuleID: %s", value);

				value = node.get("entityTableID", "");
				if (!StringUtil.isNil(value))
					print(out, ", entityTableID: %s", value);

				value = node.get("entityOperationMode", "");
				if (!StringUtil.isNil(value))
					print(out, ", opMode: '%s'", value);

				value = node.get("operationCode", "");
				if (!StringUtil.isNil(value)) {
					print(out, ", opCode: %s", value);
					print(out, ", iconCls: 'icon-%s'", value);// iconCls 由菜单操作码决定
				}

				print(out, ", onClick:jCocit.entity.doAction");

				print(out, "}");

			}
		}

		print(out, "]</script>");
	}
}
