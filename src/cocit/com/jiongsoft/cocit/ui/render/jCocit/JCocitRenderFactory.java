package com.jiongsoft.cocit.ui.render.jCocit;

import java.util.Hashtable;
import java.util.Map;

import com.jiongsoft.cocit.ui.CuiModel;
import com.jiongsoft.cocit.ui.CuiRender;
import com.jiongsoft.cocit.ui.CuiRenderFactory;
import com.jiongsoft.cocit.ui.model.CuiEntityModuleModel;
import com.jiongsoft.cocit.ui.model.CuiEntityTableModel;
import com.jiongsoft.cocit.ui.model.CuiFormModel;
import com.jiongsoft.cocit.ui.model.CuiFormModelData;
import com.jiongsoft.cocit.ui.model.CuiGridModel;
import com.jiongsoft.cocit.ui.model.CuiGridModelData;
import com.jiongsoft.cocit.ui.model.CuiMenuModel;
import com.jiongsoft.cocit.ui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.ui.model.CuiTreeModel;
import com.jiongsoft.cocit.ui.model.CuiTreeModelData;
import com.jiongsoft.cocit.utils.Log;

/**
 * 输出支持jCocit.js jQuery框架的HTML/JSON/XML。
 * 
 * @author yongshan.ji
 * 
 */
public class JCocitRenderFactory implements CuiRenderFactory {
	private Map<Class, CuiRender> renders;

	public JCocitRenderFactory() {
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
		if (modelType.equals(CuiEntityModuleModel.class))
			return new JCocitEntityModuleRender();

		if (modelType.equals(CuiEntityTableModel.class))
			return new JCocitEntityTableRender();

		if (modelType.equals(CuiFormModel.class))
			return new JCocitFormRender();
		if (modelType.equals(CuiFormModelData.class))
			return new JCocitFormDataRender();

		if (modelType.equals(CuiGridModel.class))
			return new JCocitGridRenders.ModelRender();
		if (modelType.equals(CuiGridModelData.class))
			return new JCocitGridRenders.DataRender();

		if (modelType.equals(CuiMenuModel.class))
			return new JCocitMenuRender();

		if (modelType.equals(CuiSearchBoxModel.class))
			return new JCocitSearchBoxRender();

		if (modelType.equals(CuiTreeModel.class))
			return new JCocitTreeRenders.ModelRender();
		if (modelType.equals(CuiTreeModelData.class))
			return new JCocitTreeRenders.DataRender();

		/*
		 * 创建CoC UIRender 失败！
		 */
		Log.error("JCocitRenderFactory.makeRender: 创建失败！{modelType:%s}", modelType.getName());

		return null;
	}

}
