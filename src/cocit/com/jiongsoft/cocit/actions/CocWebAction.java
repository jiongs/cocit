package com.jiongsoft.cocit.actions;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.mvc.adaptor.EntityParamAdaptor;
import com.jiongsoft.cocit.mvc.adaptor.EntityParamNode;
import com.jiongsoft.cocit.service.CocEntityActionService;
import com.jiongsoft.cocit.ui.CuiModelView;
import com.jiongsoft.cocit.ui.model.JSPModel;
import com.jiongsoft.cocit.utils.ActionUtil;

@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
@AdaptBy(type = EntityParamAdaptor.class)
public class CocWebAction {

	/**
	 * 
	 * @param jspArgs
	 *            JSP路径参数：子目录用冒号分隔，如：visit:index 表示要访问/visit/index.jsp页面。
	 * @param opArgs
	 *            操作参数：表示需要通过JSP页面动态访问指定的模块操作，参数格式为：“moduleID:tableID:operationID”。
	 * @param entityID
	 *            实体ID：表示JSP页面支持动态数据。
	 * @param entityParamNode
	 *            实体参数节点：用来接收HTTP中以entity.开头的参数，这些参数将被注入到实体对象中，继续传递到指定的页面。
	 * @return JSPModel 对象
	 */
	@At(ActionUtil.GET_JSP_MODEL)
	public JSPModel getJspModel(String jspArgs, String opArgs, String entityID, @Param("::entity.") EntityParamNode entityParamNode) {

		CocEntityActionService actionService = CocEntityActionService.make(opArgs, entityID, entityParamNode);

		String softContextPath = Cocit.getContextPath() + "/" + actionService.softService.getCode().replace('.', '_');
		String jspPath = ActionUtil.makeJspPath(jspArgs);

		JSPModel model = JSPModel.make(softContextPath, jspPath);

		model.set("actionService", actionService);

		return model;
	}
}
