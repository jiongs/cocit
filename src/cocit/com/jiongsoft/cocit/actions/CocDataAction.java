package com.jiongsoft.cocit.actions;

import static com.jiongsoft.cocit.utils.StringUtil.decodeHex;
import static com.jiongsoft.cocit.utils.StringUtil.isNil;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.coft.Codule;
import com.jiongsoft.cocit.coft.CoduleFactory;
import com.jiongsoft.cocit.cui.CuiModel;
import com.jiongsoft.cocit.cui.CuiModelFactory;
import com.jiongsoft.cocit.cui.CuiModelView;

/**
 * 数据模块Action：负责处理业务数据的“增加、删除、查询、修改、导入、导出”等业务操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
@At("/coc/data")
public class CocDataAction {

	/**
	 * 获取数据管理模块主界面UI模型
	 * 
	 * @param moduleArgs
	 *            Hex加密后的模块参数
	 * @return 模块主界面UI模型
	 */
	@At("/main/*")
	public CuiModel main(String moduleArgs) {
		String moduleID = decodeHex(moduleArgs);

		if (isNil(moduleID))
			return null;

		CoduleFactory moduleFactory = Cocit.getCoduleFactory();
		Codule module = moduleFactory.getModule(Long.parseLong(moduleID));

		if (module == null)
			return null;

		CuiModelFactory uiFactory = Cocit.getCuiModelFactory();
		CuiModel ui = uiFactory.getMainModel(module);

		return ui;
	}
}
