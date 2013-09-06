package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.cocui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;

class JCocitSearchBoxRender extends BaseCuiRender<CuiSearchBoxModel> {

	@Override
	public void render(Writer out, CuiSearchBoxModel model) throws Throwable {

		// 下拉菜单：分类查询
		List<KeyValue> list = model.getData();

		print(out, "<input class=\"jCocit-ui jCocit-searchbox\" data-options=\"prompt:'', height: 25, width:%s, onSearch: jCocit.bizmodule.doSearch", 200);

		// 下拉菜单：分类查询
		if (!Lang.isNil(list)) {
			print(out, ", menu:'#searchbox_menu_%s'", model.get("token", model.getId()));
		}

		print(out, "\"/>");

		// 下拉菜单：分类查询
		if (!Lang.isNil(list)) {
			print(out, "<div id=\"searchbox_menu_%s\" style=\"width:120px; overflow:hidden;\">", model.get("token", model.getId()));

			print(out, "<div data-options=\"name: ''\">=全部字段=</div>");
			int count = 0;
			for (KeyValue obj : list) {
				print(out, "<div data-options=\"name: '%s'\">%s</div>", obj.getValue(), obj.getKey());
				count++;
				if (count == 12)
					break;
			}
			print(out, "</div>");
		}

	}

}
