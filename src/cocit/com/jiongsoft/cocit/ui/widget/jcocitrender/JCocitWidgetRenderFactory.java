package com.jiongsoft.cocit.ui.widget.jcocitrender;

import java.util.Hashtable;
import java.util.Map;

import com.jiongsoft.cocit.ui.CuiModel;
import com.jiongsoft.cocit.ui.CuiRender;
import com.jiongsoft.cocit.ui.widget.EntityFormWidgetModel;
import com.jiongsoft.cocit.ui.widget.EntityFormWidgetData;
import com.jiongsoft.cocit.ui.widget.EntityModuleWidgetModel;
import com.jiongsoft.cocit.ui.widget.EntityTableWidgetModel;
import com.jiongsoft.cocit.ui.widget.GridWidgetModel;
import com.jiongsoft.cocit.ui.widget.GridWidgetData;
import com.jiongsoft.cocit.ui.widget.MenuWidgetModel;
import com.jiongsoft.cocit.ui.widget.SearchBoxWidgetModel;
import com.jiongsoft.cocit.ui.widget.TreeWidgetModel;
import com.jiongsoft.cocit.ui.widget.TreeWidgetData;
import com.jiongsoft.cocit.ui.widget.WidgetRenderFactory;
import com.jiongsoft.cocit.utils.Log;

/**
 * 输出支持jCocit.js jQuery框架的HTML/JSON/XML。
 * 
 * @author yongshan.ji
 * 
 */
public class JCocitWidgetRenderFactory implements WidgetRenderFactory {
	private Map<Class, CuiRender> renders;

	public JCocitWidgetRenderFactory() {
		renders = new Hashtable();
	}

	@Override
	public CuiRender getRender(Class<? extends CuiModel> modelType) {

		CuiRender ret = renders.get(modelType);
		if (ret == null) {
			ret = makeRender(modelType);

			// TODO: Develop mode dont cache it.
			// if (ret != null)
			// renders.put(modelType, ret);
		}

		return ret;
	}

	private CuiRender makeRender(Class modelType) {

		/*
		 * 创建模型Render
		 */
		if (modelType.equals(EntityModuleWidgetModel.class))
			return new JCocitEntityModuleRender();

		if (modelType.equals(EntityTableWidgetModel.class))
			return new JCocitEntityTableRender();

		if (modelType.equals(EntityFormWidgetModel.class))
			return new JCocitFormRender();
		if (modelType.equals(EntityFormWidgetData.class))
			return new JCocitFormDataRender();

		if (modelType.equals(GridWidgetModel.class))
			return new JCocitGridRenders.ModelRender();
		if (modelType.equals(GridWidgetData.class))
			return new JCocitGridRenders.DataRender();

		if (modelType.equals(MenuWidgetModel.class))
			return new JCocitMenuRender();

		if (modelType.equals(SearchBoxWidgetModel.class))
			return new JCocitSearchBoxRender();

		if (modelType.equals(TreeWidgetModel.class))
			return new JCocitTreeRenders.ModelRender();
		if (modelType.equals(TreeWidgetData.class))
			return new JCocitTreeRenders.DataRender();

		/*
		 * 创建CoC UIRender 失败！
		 */
		Log.error("JCocitWidgetRenderFactory.makeRender: 创建失败！{modelType:%s}", modelType.getName());

		return null;
	}

}
