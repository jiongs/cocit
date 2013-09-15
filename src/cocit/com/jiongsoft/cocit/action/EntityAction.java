package com.jiongsoft.cocit.action;

import java.util.List;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.jiongsoft.cocit.mvc.adaptor.EntityParamAdaptor;
import com.jiongsoft.cocit.mvc.adaptor.EntityParamNode;
import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.jiongsoft.cocit.ui.UIModelView;
import com.jiongsoft.cocit.ui.model.AlertsModel;
import com.jiongsoft.cocit.ui.model.widget.EntityFormWidgetData;
import com.jiongsoft.cocit.ui.model.widget.EntityFormWidgetModel;
import com.jiongsoft.cocit.ui.model.widget.EntityModuleWidgetModel;
import com.jiongsoft.cocit.ui.model.widget.EntityTableWidgetModel;
import com.jiongsoft.cocit.ui.model.widget.GridWidgetData;
import com.jiongsoft.cocit.ui.model.widget.GridWidgetModel;
import com.jiongsoft.cocit.ui.model.widget.TreeWidgetData;
import com.jiongsoft.cocit.util.ActionUtil;
import com.jiongsoft.cocit.util.CocException;
import com.jiongsoft.cocit.util.Log;
import com.jiongsoft.cocit.util.StringUtil;

/**
 * 实体Action：即用来管理实体数据的Action，负责接收管理实体数据的请求并处理这些请求，包括“增加、删除、查询、修改、导入、导出”等操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(UIModelView.VIEW_TYPE)
@Fail(UIModelView.VIEW_TYPE)
@AdaptBy(type = EntityParamAdaptor.class)
public class EntityAction {

	/**
	 * 获取“数据模块”界面模型，用于输出数据模块的界面。
	 * 
	 * @param args
	 *            Hex加密后的调用参数，参数组成“moduleID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_MODULE_UI)
	public EntityModuleWidgetModel getEntityModuleUI(String args) {
		ActionHelper helper = ActionHelper.make(args, null, null);

		EntityModuleWidgetModel moduleModel = helper.widgetFactory.getEntytyModuleUI(helper.module);

		Log.debug("EntityAction.getEntityModuleUI: moduleModel = %s", moduleModel);

		// 返回
		return moduleModel;
	}

	/**
	 * 获取“数据表”界面模型，用于输出数据表界面。
	 * 
	 * @param opArgs
	 *            Hex加密后的调用参数，参数组成“moduleID:tableID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_TABLE_UI)
	public EntityTableWidgetModel getEntityTableUI(String args) {
		ActionHelper helper = ActionHelper.make(args, null, null);

		EntityTableWidgetModel tableModel = helper.widgetFactory.getEntityTableUI(helper.module, helper.table);

		Log.debug("EntityAction.getEntityTableUI: tableModel = %s", tableModel);

		// 返回
		return tableModel;
	}

	/**
	 * 获取“数据表GRID”数据模型，用于输出数据表GRID所需要的JSON数据。
	 * 
	 * @param opArgs
	 *            加密后的调用参数，参数组成“moduleID:tableID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_GRID_DATA)
	public GridWidgetData getEntityGridData(String args) {
		ActionHelper helper = ActionHelper.make(args, null, null);

		GridWidgetModel tableModel = helper.widgetFactory.getGridUI(helper.module, helper.table);

		/*
		 * 构造Grid数据模型
		 */
		GridWidgetData ret = new GridWidgetData();
		ret.setModel(tableModel);

		// 构造查询条件
		CndExpr expr = helper.makeExpr();
		try {
			List data = helper.entityManager.query(expr, null);
			int total = helper.entityManager.count(expr, null);

			ret.setData(data);
			ret.setTotal(total);

			Log.debug("EntityAction.getEntityGridData: total = %s", total);
		} catch (CocException e) {
			ret.setException(e);
		}

		return ret;
	}

	/**
	 * 获取“获取数据表导航树”数据模型，用于输出树所需要的JSON数据。
	 * 
	 * @param opArgs
	 *            加密后的调用参数，参数组成“moduleID:tableID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_NAVI_DATA)
	public TreeWidgetData getEntityNaviData(String args) {
		ActionHelper helper = ActionHelper.make(args, null, null);

		TreeWidgetData treeModel = helper.widgetFactory.getEntityNaviData(helper.module, helper.table);

		return treeModel;
	}

	/**
	 * 
	 * 获取业务数据表单模型
	 * 
	 * @param opArgs
	 *            调用参数，参数组成“moduleID:tableID:operationID”
	 * @param argDataID
	 *            dataID
	 * @param dataNode
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_FORM_UI)
	public EntityFormWidgetModel getEntityFormUI(String args, String argDataID, @Param("::entity.") EntityParamNode dataNode) {
		ActionHelper helper = ActionHelper.make(args, argDataID, dataNode);

		EntityFormWidgetModel formModel = helper.widgetFactory.getEntityFormUI(helper.module, helper.table, helper.opMode, helper.entity);

		formModel.setData(helper.entity);

		/**
		 * 返回
		 */
		return formModel;
	}

	/**
	 * 
	 * 保存业务数据
	 * 
	 * @param opArgs
	 *            调用参数，参数组成“moduleID:tableID:operationID”
	 * @param argDataID
	 *            dataID
	 * @param dataNode
	 * @return
	 */
	@At(ActionUtil.SAVE_ENTITY_FORM_DATA)
	public EntityFormWidgetData saveEntityFormData(String args, String argDataID, @Param("::entity.") EntityParamNode dataNode) {
		ActionHelper helper = ActionHelper.make(args, argDataID, dataNode);

		EntityFormWidgetModel formModel = helper.widgetFactory.getEntityFormUI(helper.module, helper.table, helper.opMode, helper.entity);

		EntityFormWidgetData ret = new EntityFormWidgetData();
		ret.setModel(formModel);
		ret.setData(helper.entity);

		try {
			helper.entityManager.save(helper.entity, helper.opMode);
		} catch (Throwable e) {
			ret.setException(e);
		}

		return ret;
	}

	@At(ActionUtil.DELETE_ENTITY_DATA)
	public EntityFormWidgetData deleteEntityData(String args, String dataID) {
		ActionHelper helper = ActionHelper.make(args, dataID, null);

		EntityFormWidgetModel formModel = helper.widgetFactory.getEntityFormUI(helper.module, helper.table, helper.opMode, helper.entity);

		EntityFormWidgetData ret = new EntityFormWidgetData();
		ret.setModel(formModel);
		ret.setData(helper.entity);

		try {
			String[] array = StringUtil.toArray(dataID);
			Long[] idArray = new Long[array.length];
			for (int i = 0; i < array.length; i++) {
				idArray[i] = Long.parseLong(array[i]);
			}
			helper.entityManager.delete(idArray, helper.opMode);
		} catch (Throwable e) {
			ret.setException(e);
		}

		return ret;
	}

	@At(ActionUtil.EXEC_ENTITY_TASK)
	public AlertsModel execEntityTask(String args, String entityID, @Param("::entity.") EntityParamNode dataNode) {
		ActionHelper helper = ActionHelper.make(args, entityID, dataNode);

		try {
			String result = helper.entityManager.execTask(helper, helper.opMode);

			return AlertsModel.makeSuccess(result);
		} catch (Throwable e) {
			return AlertsModel.makeError(e == null ? "" : e.getMessage());
		}
	}

	@At(ActionUtil.EXEC_ENTITY_ASYN_TASK)
	public AlertsModel execEntityAsynTask(String args, String entityID, @Param("::entity.") EntityParamNode dataNode) {
		ActionHelper helper = ActionHelper.make(args, entityID, dataNode);

		try {
			String result = helper.entityManager.execAsynTask(helper, helper.opMode);

			return AlertsModel.makeSuccess(result);
		} catch (Throwable e) {
			return AlertsModel.makeError(e == null ? "" : e.toString());
		}
	}
}
