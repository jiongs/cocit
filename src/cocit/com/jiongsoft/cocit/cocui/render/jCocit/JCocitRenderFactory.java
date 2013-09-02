package com.jiongsoft.cocit.cocui.render.jCocit;

import java.util.Hashtable;
import java.util.Map;

import com.jiongsoft.cocit.cocui.CuiModel;
import com.jiongsoft.cocit.cocui.CuiRender;
import com.jiongsoft.cocit.cocui.CuiRenderFactory;
import com.jiongsoft.cocit.cocui.model.CuiBizModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;
import com.jiongsoft.cocit.cocui.model.CuiMenuModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModelData;
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
			if (ret != null)
				renders.put(modelType, ret);
		}

		return ret;
	}

	private CuiRender makeRender(Class modelType) {

		/*
		 * 创建模型Render
		 */
		if (modelType.equals(CuiBizTableModel.class))
			return new JCocitBizTableRender();

		if (modelType.equals(CuiGridModel.class))
			return new JCocitGridRenders.ModelRender();

		if (modelType.equals(CuiMenuModel.class))
			return new JCocitMenuRender();

		if (modelType.equals(CuiBizModuleModel.class))
			return new JCocitBizModuleRender();

		if (modelType.equals(CuiBizTableModel.class))
			return new JCocitBizTableRender();

		if (modelType.equals(CuiTreeModel.class))
			return new JCocitTreeRenders.ModelRender();

		/*
		 * 创建数据模型 Render
		 */
		if (modelType.equals(CuiGridModelData.class))
			return new JCocitGridRenders.DataRender();

		if (modelType.equals(CuiTreeModelData.class))
			return new JCocitTreeRenders.DataRender();

		/*
		 * 创建CoC UIRender 失败！
		 */
		Log.error("JCocitRenderFactory.makeRender: 创建失败！{modelType:%s}", modelType.getName());

		return null;
	}

}
