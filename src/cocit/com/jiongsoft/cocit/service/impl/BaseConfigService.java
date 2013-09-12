// $codepro.audit.disable unnecessaryCast
package com.jiongsoft.cocit.service.impl;

import com.jiongsoft.cocit.service.ConfigService;
import com.jiongsoft.cocit.util.Log;
import com.jiongsoft.cocit.util.StringUtil;

public abstract class BaseConfigService implements ConfigService {

	protected abstract String getStr(String key);

	public <T> T get(String configKey, T defaultReturn) {
		String value = this.getStr(configKey);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return null;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.castTo(value, valueType);
		} catch (Throwable e) {
			Log.error("CoudSoftConfigImpl.get: 出错！ {key:%s, defaultReturn:%s, valueType:%s}", configKey, defaultReturn, valueType.getName(), e);
		}

		return defaultReturn;
	}

}
