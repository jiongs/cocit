package com.jiongsoft.cocit.actions;

import java.util.List;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.jiongsoft.cocit.mvc.adaptor.EntityParamAdaptor;
import com.jiongsoft.cocit.mvc.adaptor.EntityParamNode;
import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.jiongsoft.cocit.service.CocEntityActionService;
import com.jiongsoft.cocit.ui.CuiModelView;
import com.jiongsoft.cocit.ui.model.AlertsModel;
import com.jiongsoft.cocit.ui.widget.EntityFormWidgetData;
import com.jiongsoft.cocit.ui.widget.EntityFormWidgetModel;
import com.jiongsoft.cocit.ui.widget.EntityModuleWidgetModel;
import com.jiongsoft.cocit.ui.widget.EntityTableWidgetModel;
import com.jiongsoft.cocit.ui.widget.GridWidgetData;
import com.jiongsoft.cocit.ui.widget.GridWidgetModel;
import com.jiongsoft.cocit.ui.widget.TreeWidgetData;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

/**
 * 实体数据管理Action：负责接收管理实体数据的请求并处理这些请求，包括“增加、删除、查询、修改、导入、导出”等操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
@AdaptBy(type = EntityParamAdaptor.class)
public class CocEntityAction {

	/**
	 * 获取“数据模块”界面模型，用于输出数据模块的界面。
	 * 
	 * @param args
	 *            Hex加密后的调用参数，参数组成“entityModuleID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_MODULE_UI)
	public EntityModuleWidgetModel getEntityModuleUI(String args) {
		CocEntityActionService service = CocEntityActionService.make(args, null, null);

		EntityModuleWidgetModel moduleModel = service.modelFactory.getEntytyModuleUI(service.entityModule);

		Log.debug("CocEntityAction.getEntityModuleUI: moduleModel = %s", moduleModel);

		// 返回
		return moduleModel;
	}

	/**
	 * 获取“数据表”界面模型，用于输出数据表界面。
	 * 
	 * @param pathArgs
	 *            Hex加密后的调用参数，参数组成“entityModuleID:entityTableID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_TABLE_UI)
	public EntityTableWidgetModel getEntityTableUI(String args) {
		CocEntityActionService service = CocEntityActionService.make(args, null, null);

		EntityTableWidgetModel tableModel = service.modelFactory.getEntityTableUI(service.entityModule, service.entityTable);

		Log.debug("CocEntityAction.getEntityTableUI: tableModel = %s", tableModel);

		// 返回
		return tableModel;
	}

	/**
	 * 获取“数据表GRID”数据模型，用于输出数据表GRID所需要的JSON数据。
	 * 
	 * @param pathArgs
	 *            加密后的调用参数，参数组成“entityModuleID:entityTableID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_GRID_DATA)
	public GridWidgetData getEntityGridData(String args) {
		CocEntityActionService service = CocEntityActionService.make(args, null, null);

		GridWidgetModel tableModel = service.modelFactory.getGridUI(service.entityModule, service.entityTable);

		/*
		 * 构造Grid数据模型
		 */
		GridWidgetData ret = new GridWidgetData();
		ret.setModel(tableModel);

		// 构造查询条件
		CndExpr expr = service.makeExpr();
		try {
			List data = service.entityManager.query(expr, null);
			int total = service.entityManager.count(expr, null);

			ret.setData(data);
			ret.setTotal(total);

			Log.debug("CocEntityAction.getEntityGridData: total = %s", total);
		} catch (CocException e) {
			ret.setException(e);
		}

		return ret;
	}

	/**
	 * 获取“获取数据表导航树”数据模型，用于输出树所需要的JSON数据。
	 * 
	 * @param pathArgs
	 *            加密后的调用参数，参数组成“entityModuleID:entityTableID”
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_NAVI_DATA)
	public TreeWidgetData getEntityNaviData(String args) {
		CocEntityActionService service = CocEntityActionService.make(args, null, null);

		TreeWidgetData treeModel = service.modelFactory.getEntityNaviData(service.entityModule, service.entityTable);

		return treeModel;
	}

	/**
	 * 
	 * 获取业务数据表单模型
	 * 
	 * @param pathArgs
	 *            调用参数，参数组成“entityModuleID:entityTableID:entityOperationID”
	 * @param argDataID
	 *            dataID
	 * @param dataNode
	 * @return
	 */
	@At(ActionUtil.GET_ENTITY_FORM_UI)
	public EntityFormWidgetModel getEntityFormUI(String args, String argDataID, @Param("::entity.") EntityParamNode dataNode) {
		CocEntityActionService service = CocEntityActionService.make(args, argDataID, dataNode);

		EntityFormWidgetModel formModel = service.modelFactory.getEntityFormUI(service.entityModule, service.entityTable, service.entityOperation, service.entity);

		formModel.setData(service.entity);

		/**
		 * 返回
		 */
		return formModel;
	}

	/**
	 * 
	 * 保存业务数据
	 * 
	 * @param pathArgs
	 *            调用参数，参数组成“entityModuleID:entityTableID:entityOperationID”
	 * @param argDataID
	 *            dataID
	 * @param dataNode
	 * @return
	 */
	@At(ActionUtil.SAVE_ENTITY_FORM_DATA)
	public EntityFormWidgetData saveEntityFormData(String args, String argDataID, @Param("::entity.") EntityParamNode dataNode) {
		CocEntityActionService service = CocEntityActionService.make(args, argDataID, dataNode);

		EntityFormWidgetModel formModel = service.modelFactory.getEntityFormUI(service.entityModule, service.entityTable, service.entityOperation, service.entity);

		EntityFormWidgetData ret = new EntityFormWidgetData();
		ret.setModel(formModel);
		ret.setData(service.entity);

		try {
			service.entityManager.save(service.entity, service.entityOperationMode);
		} catch (Throwable e) {
			ret.setException(e);
		}

		return ret;
	}

	@At(ActionUtil.DELETE_ENTITY_DATA)
	public EntityFormWidgetData deleteEntityData(String args, String dataID) {
		CocEntityActionService service = CocEntityActionService.make(args, dataID, null);

		EntityFormWidgetModel formModel = service.modelFactory.getEntityFormUI(service.entityModule, service.entityTable, service.entityOperation, service.entity);

		EntityFormWidgetData ret = new EntityFormWidgetData();
		ret.setModel(formModel);
		ret.setData(service.entity);

		try {
			String[] array = StringUtil.toArray(dataID);
			Long[] idArray = new Long[array.length];
			for (int i = 0; i < array.length; i++) {
				idArray[i] = Long.parseLong(array[i]);
			}
			service.entityManager.delete(idArray, service.entityOperationMode);
		} catch (Throwable e) {
			ret.setException(e);
		}

		return ret;
	}

	@At(ActionUtil.EXEC_ENTITY_TASK)
	public AlertsModel execEntityTask(String args, String entityID, @Param("::entity.") EntityParamNode dataNode) {
		CocEntityActionService service = CocEntityActionService.make(args, entityID, dataNode);

		try {
			String result = service.entityManager.execTask(service, service.entityOperationMode);

			return AlertsModel.make(200, result);
		} catch (Throwable e) {
			return AlertsModel.make(300, e == null ? "" : e.toString());
		}
	}

	@At(ActionUtil.EXEC_ENTITY_ASYN_TASK)
	public AlertsModel execEntityAsynTask(String args, String entityID, @Param("::entity.") EntityParamNode dataNode) {
		CocEntityActionService service = CocEntityActionService.make(args, entityID, dataNode);

		try {
			String result = service.entityManager.execAsynTask(service, service.entityOperationMode);

			return AlertsModel.make(200, result);
		} catch (Throwable e) {
			return AlertsModel.make(300, e == null ? "" : e.toString());
		}
	}
}
