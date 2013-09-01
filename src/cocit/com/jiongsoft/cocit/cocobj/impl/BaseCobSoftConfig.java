package com.jiongsoft.cocit.cocobj.impl;

import com.jiongsoft.cocit.cocobj.CobSoftConfig;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

public abstract class BaseCobSoftConfig implements CobSoftConfig {

	protected abstract String getStr(String key);

	public <T> T get(String configKey, T defaultReturn) {
		String value = this.getStr(configKey);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) value;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.cast(value, valueType);
		} catch (Throwable e) {
			Log.error("CoudSoftConfigImpl.get: 出错！ {key:%s, defaultReturn:%s, valueType:%s}", configKey, defaultReturn, valueType.getName());
		}

		return defaultReturn;
	}

}
