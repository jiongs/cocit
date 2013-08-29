package com.jiongsoft.cocit.coft.impl;

import java.util.Date;

import com.jiongsoft.cocit.coft.CoftConfig;
import com.jiongsoft.cocit.utils.ClassUtil;
import com.jiongsoft.cocit.utils.DateUtil;
import com.jiongsoft.cocit.utils.Log;

public abstract class BaseCoftConfig implements CoftConfig {

	protected abstract String getStr(String key);

	public <T> T get(String configKey, T defaultReturn) {
		String str = this.getStr(configKey);

		if (str == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) str;

		Class valueType = defaultReturn.getClass();

		try {
			if (valueType.equals(String.class))
				return (T) str;
			if (Long.class.isAssignableFrom(valueType))
				return (T) Long.valueOf(str);
			if (Integer.class.isAssignableFrom(valueType))
				return (T) Integer.valueOf(str);
			if (Short.class.isAssignableFrom(valueType))
				return (T) Short.valueOf(str);
			if (Byte.class.isAssignableFrom(valueType))
				return (T) Byte.valueOf(str);
			if (Double.class.isAssignableFrom(valueType))
				return (T) Double.valueOf(str);
			if (Float.class.isAssignableFrom(valueType))
				return (T) Float.valueOf(str);
			if (Boolean.class.isAssignableFrom(valueType))
				return (T) Boolean.valueOf(str);
			if (Date.class.isAssignableFrom(valueType))
				return (T) DateUtil.parse(str);
			if (Class.class.isAssignableFrom(valueType))
				return (T) ClassUtil.forName(str);

			// return (T) ClassUtil.newInstance(valueType, str);

		} catch (Throwable e) {
			Log.error("CoudSoftConfigImpl.get: 出错！ {key:%s, defaultReturn:%s, valueType:%s}", configKey, defaultReturn, valueType.getName());
		}

		return defaultReturn;
	}

}
