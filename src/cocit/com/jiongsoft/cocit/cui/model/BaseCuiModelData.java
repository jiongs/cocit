package com.jiongsoft.cocit.cui.model;

import static com.jiongsoft.cocit.Cocit.getCuiRenderFactory;

import java.io.Writer;

import com.jiongsoft.cocit.cui.CuiModel;

/**
 * 数据模型：用于表示通过AJAX访问的数据模型。由两部分组成：1.模型，2.数据
 * <p>
 * 继承该类的数据模型中的数据将以JSON或XML格式输出。
 * 
 * @author yongshan.ji
 * 
 * @param <T>模型泛型类
 * @param <D>数据泛型类
 */
public abstract class BaseCuiModelData<TModel extends BaseCuiModel, TData> implements CuiModel {

	/**
	 * HTML模型：数据的输出依赖于该HTML模型
	 */
	protected TModel model;

	/**
	 * 业务数据：待输出的业务数据对象
	 */
	protected TData data;

	@Override
	public void render(Writer out) throws Throwable {
		getCuiRenderFactory().getRender(getClass()).render(out, this);
	}

	@Override
	public String getContentType() {
		return CONTENT_TYPE_JSON;
	}

	public TModel getModel() {
		return model;
	}

	public void setModel(TModel model) {
		this.model = model;
	}

	public TData getData() {
		return data;
	}

	public void setData(TData data) {
		this.data = data;
	}

}
