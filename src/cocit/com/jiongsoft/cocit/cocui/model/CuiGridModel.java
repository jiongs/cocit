package com.jiongsoft.cocit.cocui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * 数据表Grid界面模型：由多个Grid列和数据组成，如果数据不存在则表示将异步获取Grid数据。
 * 
 * <b>属性说明：</b>
 * <UL>
 * <LI>rownumbers: bool值，是否在Grid中显示行号？
 * <LI>checkbox: bool值，是否在Grid的第一列显示复选框？
 * <LI>singleSelect: bool值，表示Grid是否只能单选？
 * </UL>
 * 
 * @author yongshan.ji
 * 
 */
public class CuiGridModel extends BaseCuiModel {

	// Grid数据，如果该值为Null，则将通过AJAX方式加载Grid数据。
	private List data;

	// Grid数据“增、删、查、改”操作的URL地址
	private String dataLoadUrl;
	private String dataDeleteUrl;
	private String dataEditUrl;
	private String dataAddUrl;

	// Grid列
	private List<GridColumn> columns;

	public CuiGridModel() {
		super();
		columns = new ArrayList();
	}

	public void addColumn(GridColumn col) {
		columns.add(col);
	}

	public String getDataLoadUrl() {
		return dataLoadUrl;
	}

	public void setDataLoadUrl(String dataLoadUrl) {
		this.dataLoadUrl = dataLoadUrl;
	}

	public String getDataDeleteUrl() {
		return dataDeleteUrl;
	}

	public void setDataDeleteUrl(String dataDeleteUrl) {
		this.dataDeleteUrl = dataDeleteUrl;
	}

	public String getDataEditUrl() {
		return dataEditUrl;
	}

	public void setDataEditUrl(String dataEditUrl) {
		this.dataEditUrl = dataEditUrl;
	}

	public String getDataAddUrl() {
		return dataAddUrl;
	}

	public void setDataAddUrl(String dataUpdateUrl) {
		this.dataAddUrl = dataUpdateUrl;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public List<GridColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<GridColumn> columns) {
		this.columns = columns;
	}

	public static class GridColumn {
		private String field;
		private String title;
		private String assign;
		private int width;
		private Properties props;

		/**
		 * 创建一个GridColumn对象
		 * 
		 * @param field
		 *            字段：用于标识该列数据来自哪个字段？
		 * @param title
		 *            标题：用于显示表头标题
		 * @param assign
		 *            数据对齐方式：可选值“right/center/left”
		 * @param width
		 *            列宽度：单位px，-1 表示自动宽度。
		 */
		public GridColumn(String field, String title) {
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

		public String getAssign() {
			return assign;
		}

		public void setAssign(String assign) {
			this.assign = assign;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public Properties getProps() {
			return props;
		}

		public void setProps(Properties props) {
			this.props = props;
		}
	}

}
