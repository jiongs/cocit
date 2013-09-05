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

		List<KeyValue> list = model.getData();

		print(out, "<input class=\"jCocit-ui jCocit-searchbox\" data-options=\"prompt:'', height: 26, width:%s, onSearch: jCocit.bizmodule.doSearch", model.get("width", 240));
		if (!Lang.isNil(list)) {
			print(out, ", menu:'#searchbox_menu_%s'", model.get("token", model.getId()));
		}
		print(out, "\"/>");
		if (!Lang.isNil(list)) {
			print(out, "<div id=\"searchbox_menu_%s\" style=\"width:120px; overflow:hidden;\">", model.get("token", model.getId()));

			print(out, "<div data-options=\"name: ''\">=全部字段=</div>");
			for (KeyValue obj : list) {
				print(out, "<div data-options=\"name: '%s'\">%s</div>", obj.getValue(), obj.getKey());
			}
			print(out, "</div>");
		}

	}

}
