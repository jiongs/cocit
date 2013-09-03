package com.jiongsoft.cocit.utils;

/**
 * Key Value 配对值，如下拉框选择项中，key即为Label。
 * 
 * @author jiongs753
 * 
 * @param <T>
 */
public class KeyValue {
	private String key;
	private Object value;

	public static KeyValue make(String key, Object value) {
		return new KeyValue(key, value);
	}

	private KeyValue(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
