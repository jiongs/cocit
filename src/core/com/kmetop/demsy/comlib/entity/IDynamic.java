package com.kmetop.demsy.comlib.entity;

import java.util.Map;

/**
 * <b>组件:</b>
 * 
 * @author yongshan.ji
 */
public interface IDynamic {

	Object get(String extPropName);

	void set(String key, Object value);

	Map getDynaProp();

	boolean is(byte index);
}
