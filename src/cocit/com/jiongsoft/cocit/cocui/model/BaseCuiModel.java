package com.jiongsoft.cocit.cocui.model;

import static com.jiongsoft.cocit.Cocit.getCuiRenderFactory;

import java.io.Writer;
import java.util.Hashtable;
import java.util.Map;

import com.jiongsoft.cocit.cocui.CuiModel;

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
	private Map<String, Object> extProps;

	public BaseCuiModel() {
		extProps = new Hashtable();
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
	public void set(String propName, Object propValue) {
		extProps.put(propName, propValue);
	}

	/**
	 * 获取模型扩展属性
	 * 
	 * @param propName
	 * @return
	 */
	public <T> T get(String propName) {
		return (T) extProps.get(propName);
	}

	/**
	 * 获取扩展属性的BOOL值
	 * 
	 * @param propName
	 * @return
	 */
	public boolean is(String propName) {
		Object obj = extProps.get(propName);
		if (obj == null)
			return false;

		try {
			return Boolean.valueOf(obj.toString());
		} catch (Throwable e) {
			return false;
		}
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

	public Map<String, Object> getExtProps() {
		return extProps;
	}

	public void setExtProps(Map<String, Object> props) {
		this.extProps = props;
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
