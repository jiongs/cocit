package com.jiongsoft.cocit.cocui.render.jCocit;

import java.io.Writer;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.cocui.render.BaseCuiRender;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;

class JCocitSearchBoxRender extends BaseCuiRender<CuiSearchBoxModel> {

	@Override
	public void render(Writer out, CuiSearchBoxModel model) throws Throwable {

		// 下拉菜单：分类查询
		List<KeyValue> list = model.getData();

		String token = model.get("token", "");
		print(out, "<input id=\"searchbox_%s\" class=\"jCocit-ui jCocit-searchbox\" data-options=\"bizToken:'%s', prompt:'', height: 24, width:%s, onSearch: jCocit.bizmodule.doSearch", token, token,
				250);

		// 下拉菜单：分类查询
		if (!Lang.isNil(list)) {
			print(out, ", menu:'#searchbox_menu_%s'", model.get("token", model.getId()));
		}

		print(out, "\"/>");

		// 下拉菜单：分类查询
		if (!Lang.isNil(list)) {
			print(out, "<div id=\"searchbox_menu_%s\" data-options=\"minWidth:150\">", model.get("token", model.getId()));

			print(out, "<div data-options=\"name: ''\"><span>--按字段检索--</span></div>");
			int count = 0;
			String value;
			String key;
			byte type = 0;
			for (KeyValue obj : list) {
				value = obj.getValue();
				key = obj.getKey();
				type = obj.get("type", (byte) 0);

				print(out, "<div data-options=\"name: '%s'\"><span>%s</span>", value, key);

				// 操作符子菜单
				switch (type) {
				case CocBizField.TYPE_NUMBER:
				case CocBizField.TYPE_DATE:
					print(out, "<div>");
					print(out, "<div data-options=\"name: '%s eq'\">[%s] 等于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s ne'\">[%s] 不等于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s lt'\">[%s] 小于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s le'\">[%s] 小于等于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s gt'\">[%s] 大于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s ge'\">[%s] 大于等于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s in'\">[%s] 在...中</div>", value, key);
					print(out, "<div data-options=\"name: '%s ni'\">[%s] 不在...中</div>", value, key);
					print(out, "<div data-options=\"name: '%s gl'\">[%s] 在...之间</div>", value, key);
					print(out, "</div>");
					break;
				case CocBizField.TYPE_RICH_TEXT:
				case CocBizField.TYPE_STRING:
				case CocBizField.TYPE_TEXT:
				case CocBizField.TYPE_UPLOAD:
					print(out, "<div style=\"width:60px;\">");
					print(out, "<div data-options=\"name: '%s eq'\">[%s] 等于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s ne'\">[%s] 不等于...</div>", value, key);
					print(out, "<div data-options=\"name: '%s nu'\">[%s] 为空</div>", value, key);
					print(out, "<div data-options=\"name: '%s nn'\">[%s] 不为空</div>", value, key);
					print(out, "<div data-options=\"name: '%s cn'\">[%s] 包含...</div>", value, key);
					print(out, "<div data-options=\"name: '%s nc'\">[%s] 不包含...</div>", value, key);
					print(out, "<div data-options=\"name: '%s sw'\">[%s] 以...开头</div>", value, key);
					print(out, "<div data-options=\"name: '%s bn'\">[%s] 不以...开头</div>", value, key);
					print(out, "<div data-options=\"name: '%s ew'\">[%s] 以...结尾</div>", value, key);
					print(out, "<div data-options=\"name: '%s en'\">[%s] 以...结尾</div>", value, key);
					print(out, "<div data-options=\"name: '%s in'\">[%s] 在...中</div>", value, key);
					print(out, "<div data-options=\"name: '%s ni'\">[%s] 不在...中</div>", value, key);
					print(out, "</div>");
					break;
				default:

				}

				print(out, "</div>");
				count++;
				if (count == 12)
					break;
			}
			print(out, "</div>");
		}

	}

}
