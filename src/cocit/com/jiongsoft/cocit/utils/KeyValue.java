package com.jiongsoft.cocit.utils;

/**
 * Key Value 配对值，如下拉框选择项中，key即为Label。
 * 
 * @author jiongs753
 * 
 * @param <T>
 */
public class KeyValue<T> {
	private String key;
	private T value;

	public KeyValue(String key, T value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
