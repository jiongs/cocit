package com.jiongsoft.cocit.actions;

import static com.jiongsoft.cocit.utils.StringUtil.isNil;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.coft.CoftModule;
import com.jiongsoft.cocit.coft.CoftModuleFactory;
import com.jiongsoft.cocit.cui.CuiModelFactory;
import com.jiongsoft.cocit.cui.CuiModelView;
import com.jiongsoft.cocit.cui.model.CuiModuleMainDetailModel;
import com.jiongsoft.cocit.utils.StringUtil;

/**
 * 数据模块Action：负责处理业务数据的“增加、删除、查询、修改、导入、导出”等业务操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
@At(CocActionConst.PATH_ACTION_DATA)
public class CocActionData {

	/**
	 * 获取数据管理模块主界面UI模型
	 * 
	 * @param moduleArgs
	 *            Hex加密后的模块参数
	 * @return 模块主界面UI模型
	 */
	@At(CocActionConst.PATH_METHOD_MAIN)
	public CuiModuleMainDetailModel main(String moduleArgs) {
		String moduleID = StringUtil.decodeHex(moduleArgs);

		if (isNil(moduleID))
			return null;

		CoftModuleFactory moduleFactory = Cocit.getModuleFactory();
		CoftModule module = moduleFactory.getModule(Long.parseLong(moduleID));

		if (module == null)
			return null;

		CuiModelFactory uiFactory = Cocit.getCuiModelFactory();
		CuiModuleMainDetailModel ui = uiFactory.getModuleMainDetailsModel(module);

		return ui;
	}
}
