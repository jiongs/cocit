package com.jiongsoft.cocit.cocsoft.impl;

import com.jiongsoft.cocit.cocsoft.CocSoftConfig;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

public abstract class BaseCocSoftConfig implements CocSoftConfig {

	protected abstract String getStr(String key);

	public <T> T get(String configKey, T defaultReturn) {
		String value = this.getStr(configKey);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) value;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.castTo(value, valueType);
		} catch (Throwable e) {
			Log.error("CoudSoftConfigImpl.get: 出错！ {key:%s, defaultReturn:%s, valueType:%s}", configKey, defaultReturn, valueType.getName());
		}

		return defaultReturn;
	}

}
