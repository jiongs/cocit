package com.jiongsoft.cocit.utils.json;

import java.io.Writer;

/**
 * JSON 工具类
 * 
 * @author yongshan.ji
 * 
 */
public abstract class JsonImpl {
	public static JsonImpl DEFAULT = new NutJson();

	public static void setDefault(JsonImpl proxy) {
		if (proxy != null)
			DEFAULT = proxy;
	}

	public abstract String toJson(Object obj);

	public abstract void toJson(Writer writer, Object obj);
}
