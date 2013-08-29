package com.jiongsoft.cocit.utils.json;

import java.io.Writer;

import org.nutz.json.Json;

public class NutJson extends JsonImpl {

	@Override
	public String toJson(Object obj) {
		return Json.toJson(obj);
	}

	@Override
	public void toJson(Writer writer, Object obj) {
		Json.toJson(writer, obj);
	}

}
