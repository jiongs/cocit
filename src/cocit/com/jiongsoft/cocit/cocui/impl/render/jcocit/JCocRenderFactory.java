package com.jiongsoft.cocit.cocui.impl.render.jcocit;

import java.util.Hashtable;
import java.util.Map;

import com.jiongsoft.cocit.cocui.CuiModel;
import com.jiongsoft.cocit.cocui.CuiRender;
import com.jiongsoft.cocit.cocui.CuiRenderFactory;
import com.jiongsoft.cocit.cocui.model.CuiDataModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiDataTableModel;
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
public class JCocRenderFactory implements CuiRenderFactory {
	private Map<Class, CuiRender> renders;

	public JCocRenderFactory() {
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
		if (modelType.equals(CuiDataTableModel.class))
			return new JCocDataTableRender();

		if (modelType.equals(CuiGridModel.class))
			return new JCocGridRenders.ModelRender();

		if (modelType.equals(CuiMenuModel.class))
			return new JCocMenuRender();

		if (modelType.equals(CuiDataModuleModel.class))
			return new JCocDataModuleRender();

		if (modelType.equals(CuiDataTableModel.class))
			return new JCocDataTableRender();

		if (modelType.equals(CuiTreeModel.class))
			return new JCocTreeRenders.ModelRender();

		/*
		 * 创建数据模型 Render
		 */
		if (modelType.equals(CuiGridModelData.class))
			return new JCocGridRenders.DataRender();

		if (modelType.equals(CuiTreeModelData.class))
			return new JCocTreeRenders.DataRender();

		/*
		 * 创建CoC UIRender 失败！
		 */
		Log.error("JCocRenderFactory.makeRender: 创建失败！{modelType:%s}", modelType.getName());

		return null;
	}

}
