package com.jiongsoft.cocit.utils.json;

import java.io.Writer;
import java.util.List;

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

	@Override
	public List fromJson(String json) {
		return (List) Json.fromJson(json);
	}

	@Override
	public <T> T fromJson(Class<T> type, String json) {
		return Json.fromJson(type, json);
	}

}
