package com.jiongsoft.cocit.actions;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.jiongsoft.cocit.ui.CuiModel;
import com.jiongsoft.cocit.ui.CuiModelView;

/**
 * 报表模块Action：负责处理业务报表的“查询、导出”等业务操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
public class CocReportAction {

	/**
	 * 获取模块主界面UI模型
	 * 
	 * @param moduleArgs
	 *            BASE64加密后的模块参数
	 * @return 模块主界面UI模型
	 */
	@At("/coc/report/moduleService/*")
	public CuiModel getModuleModel(String moduleArgs) {

		return null;
	}
}
