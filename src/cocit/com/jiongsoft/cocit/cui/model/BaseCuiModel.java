package com.jiongsoft.cocit.cui.model;

import static com.jiongsoft.cocit.Cocit.getCuiRenderFactory;

import java.io.Writer;
import java.util.Hashtable;
import java.util.Map;

import com.jiongsoft.cocit.cui.CuiModel;

/**
 * 模型基类：继承该类的所有UI模型都将以HTML结构数据。
 * 
 * @author yongshan.ji
 * 
 */
public abstract class BaseCuiModel implements CuiModel {

	// Grid属性设置
	private Map<String, Object> props;

	private String themeName;

	public BaseCuiModel() {
		props = new Hashtable();
	}

	@Override
	public void render(Writer out) throws Throwable {
		getCuiRenderFactory().getRender(getClass()).render(out, this);
	}

	@Override
	public String getContentType() {
		return CONTENT_TYPE_HTML;
	}

	public void addProp(String propName, Object propValue) {
		props.put(propName, propValue);
	}

	public <T> T getProp(String propName) {
		return (T) props.get(propName);
	}

	public Map<String, Object> getProps() {
		return props;
	}

	public void setProps(Map<String, Object> props) {
		this.props = props;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

}
