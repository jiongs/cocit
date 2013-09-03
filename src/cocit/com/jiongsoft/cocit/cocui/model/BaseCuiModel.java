package com.jiongsoft.cocit.cocui.model;

import static com.jiongsoft.cocit.Cocit.getCuiRenderFactory;

import java.io.Writer;
import java.util.Properties;

import com.jiongsoft.cocit.cocui.CuiModel;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

/**
 * 基本界面模型：继承该类的所有模型都将以HTML数据格式输出特定的界面模型。
 * 
 * @author yongshan.ji
 * 
 */
public abstract class BaseCuiModel implements CuiModel {

	private String id;

	private String themeName;

	// Grid属性设置
	private Properties extProps;

	public BaseCuiModel() {
		extProps = new Properties();
	}

	@Override
	public void render(Writer out) throws Throwable {
		getCuiRenderFactory().getRender(getClass()).render(out, this);
	}

	@Override
	public String getContentType() {
		return CONTENT_TYPE_HTML;
	}

	/**
	 * 设置模型扩展属性
	 * 
	 * @param propName
	 * @param propValue
	 */
	public void set(String propName, String propValue) {
		extProps.put(propName, propValue);
	}

	/**
	 * 获取模型扩展属性
	 * 
	 * @param propName
	 * @return
	 */
	public <T> T get(String propName, T defaultReturn) {
		String value = extProps.getProperty(propName);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) value;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.cast(value, valueType);
		} catch (Throwable e) {
			Log.error("BaseCuiModel.get: 出错！ {propName:%s, defaultReturn:%s, valueType:%s}", propName, defaultReturn, valueType.getName());
		}

		return defaultReturn;
	}

	/**
	 * 获取扩展属性的bool值
	 * 
	 * @param propName
	 * @return
	 */
	public boolean isExtProp(String propName) {
		Object obj = extProps.get(propName);
		if (obj == null)
			return false;

		try {
			return Boolean.valueOf(obj.toString());
		} catch (Throwable e) {
			return false;
		}
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
