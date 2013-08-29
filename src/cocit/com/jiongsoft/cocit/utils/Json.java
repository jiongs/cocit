package com.jiongsoft.cocit.utils;

import java.io.Writer;

import com.jiongsoft.cocit.utils.json.JsonImpl;

/**
 * JSON 工具类
 * 
 * @author yongshan.ji
 * 
 */
public abstract class Json {
	private static JsonImpl json = JsonImpl.DEFAULT;

	public static String toJson(Object obj) {
		return json.toJson(obj);
	}

	public static void toJson(Writer writer, Object obj) {
		json.toJson(writer, obj);
	}
}
