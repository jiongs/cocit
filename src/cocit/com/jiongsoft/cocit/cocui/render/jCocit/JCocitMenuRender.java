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

class JCocitMenuRender extends BaseCuiRender<CuiMenuModel> {

	@Override
	public void render(Writer out, CuiMenuModel model) throws Throwable {
		Tree tree = model.getData();

		// 菜单ID与GridRender中输出的工具栏ID相同，都是BizTable ID。
		print(out, "<div id=\"toolbar%s\" style=\"padding:2px; height: auto; background-color: #dfeffc;\">", model.getId());

		List<Node> nodes = tree.getChildren();
		if (!Lang.isNil(nodes)) {

			// toolbar
			print(out, "<div style=\"margin: 1px 0 1px 0\">");
			for (Node node : nodes) {
				print(out, "<a href=\"javascript:void(-1)\" class=\"jCocit-ui jCocit-menubar\" data-options=\"");
				print(out, "split:true");

				if (node.size() > 0)
					print(out, ",menu:'#submenu%s'", node.getId());

				// menu iconCls
				String iconCls = node.get("iconCls");
				if (!StringUtil.isNil(iconCls))
					print(out, ",iconCls:'%s'", iconCls);

				print(out, "\"><img src=\"%s\" />%s</a>", node.get("logo"), node.getName());
			}
			print(out, "</div>");

			// sub menu
			for (Node node : nodes) {
				if (node.size() > 0)
					outSubmenu(out, node);
			}
		}

		print(out, "</div>");
	}

	private void outSubmenu(Writer out, Node node) throws IOException {
		print(out, "<div id=\"submenu1\" style=\"width:150px;\">", node.getId());

		for (Node child : node.getChildren()) {
			print(out, "<div data-options=\"iconCls:'icon-undo'\">");
			print(out, "<span>%s</span>", child.getName());

			// 输出子菜单
			if (child.size() > 0)
				outSubmenu(out, child);

			print(out, "</div>");
		}

		print(out, "</div>");
	}
}
