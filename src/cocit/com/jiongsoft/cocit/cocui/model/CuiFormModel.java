package com.jiongsoft.cocit.cocui.model;

import java.util.List;
import java.util.Properties;

import com.jiongsoft.cocit.cocsoft.CocBizField;

/**
 * 业务表单模型
 * 
 * @author yongshan.ji
 * 
 */
public class CuiFormModel extends BaseCuiModel {
	private Object data;

	private List<FormField> fields;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<FormField> getFields() {
		return fields;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	public static class FormField {
		// 字段类型
		private byte type;
		private String field;
		private String title;
		private String pattern;
		private String mode;

		// 字段关联的对象
		private CocBizField bizField;

		private Properties props;

		/**
		 * 创建一个GridColumn对象
		 * 
		 * @param field
		 *            字段：用于标识该列数据来自哪个字段？
		 * @param title
		 *            标题：用于显示表头标题
		 * @param align
		 *            数据对齐方式：可选值“right/center/left”
		 * @param width
		 *            列宽度：单位px，-1 表示自动宽度。
		 */
		public FormField(String field, String title) {
			this.props = new Properties();

			this.field = field;
			this.title = title;
		}

		public void addProp(String propName, String propValue) {
			props.put(propName, propValue);
		}

		public String getProp(String propName) {
			return props.getProperty(propName);
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Properties getProps() {
			return props;
		}

		public void setProps(Properties props) {
			this.props = props;
		}

		public String getPattern() {
			return pattern;
		}

		public void setPattern(String pattern) {
			this.pattern = pattern;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public byte getType() {
			return type;
		}

		public void setType(byte type) {
			this.type = type;
		}

		public CocBizField getBizField() {
			return bizField;
		}

		public void setBizField(CocBizField bizField) {
			this.bizField = bizField;
		}
	}
}
