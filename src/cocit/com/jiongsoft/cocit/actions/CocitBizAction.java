package com.jiongsoft.cocit.actions;

import java.util.List;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.jiongsoft.cocit.cocui.CuiModelView;
import com.jiongsoft.cocit.cocui.model.CuiBizModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiBizTableModel;
import com.jiongsoft.cocit.cocui.model.CuiFormModel;
import com.jiongsoft.cocit.cocui.model.CuiFormModelData;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModelData;
import com.jiongsoft.cocit.cocui.model.CuiTreeModelData;
import com.jiongsoft.cocit.corm.expr.CndExpr;
import com.jiongsoft.cocit.mvc.CocitBizDataAdaptor;
import com.jiongsoft.cocit.mvc.CocitBizDataNode;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

/**
 * 数据模块Action：负责处理业务数据的“增加、删除、查询、修改、导入、导出”等业务操作。
 * 
 * @author jiongs753
 * 
 */
@Ok(CuiModelView.VIEW_TYPE)
@Fail(CuiModelView.VIEW_TYPE)
@AdaptBy(type = CocitBizDataAdaptor.class)
public class CocitBizAction {

	/**
	 * 获取“数据模块”界面模型，用于输出数据模块的界面。
	 * 
	 * @param operationArgs
	 *            Hex加密后的调用参数，参数组成“dataModuleID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_MODULE_UI)
	public CuiBizModuleModel getBizModuleUI(String args) {
		CocitBizActionHelper helper = CocitBizActionHelper.make(args, null, null);

		CuiBizModuleModel moduleModel = helper.modelFactory.getBizModuleModel(helper.module);

		Log.debug("CocitBizAction.getBizModuleModel: moduleModel = %s", moduleModel);

		// 返回
		return moduleModel;
	}

	/**
	 * 获取“数据表”界面模型，用于输出数据表界面。
	 * 
	 * @param operationArgs
	 *            Hex加密后的调用参数，参数组成“dataModuleID:dataTableID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_TABLE_UI)
	public CuiBizTableModel getBizTableUI(String args) {
		CocitBizActionHelper helper = CocitBizActionHelper.make(args, null, null);

		CuiBizTableModel tableModel = helper.modelFactory.getBizTableModel(helper.module, helper.table);

		Log.debug("CocitBizAction.getBizTableModel: tableModel = %s", tableModel);

		// 返回
		return tableModel;
	}

	/**
	 * 获取“数据表GRID”数据模型，用于输出数据表GRID所需要的JSON数据。
	 * 
	 * @param operationArgs
	 *            加密后的调用参数，参数组成“dataModuleID:dataTableID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_GRID_DATA)
	public CuiGridModelData getBizGridData(String args) {
		CocitBizActionHelper helper = CocitBizActionHelper.make(args, null, null);

		CuiGridModel tableModel = helper.modelFactory.getGridModel(helper.module, helper.table);

		/*
		 * 构造Grid数据模型
		 */
		CuiGridModelData ret = new CuiGridModelData();
		ret.setModel(tableModel);

		// 构造查询条件
		CndExpr expr = helper.makeExpr();
		try {
			List data = helper.entityManager.query(expr, null);
			int total = helper.entityManager.count(expr, null);

			ret.setData(data);
			ret.setTotal(total);

			Log.debug("CocitBizAction.getBizTableGridData: total = %s", total);
		} catch (CocException e) {
			ret.setException(e);
		}

		return ret;
	}

	/**
	 * 获取“获取数据表导航树”数据模型，用于输出树所需要的JSON数据。
	 * 
	 * @param operationArgs
	 *            加密后的调用参数，参数组成“dataModuleID:dataTableID”
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_NAVI_DATA)
	public CuiTreeModelData getBizNaviData(String args) {
		CocitBizActionHelper helper = CocitBizActionHelper.make(args, null, null);

		CuiTreeModelData treeModel = helper.modelFactory.getNaviTreeModelData(helper.module, helper.table);

		return treeModel;
	}

	/**
	 * 
	 * 获取业务数据表单模型
	 * 
	 * @param operationArgs
	 *            调用参数，参数组成“bizModuleID:bizTableID:bizOperationID”
	 * @param argDataID
	 *            dataID
	 * @param dataNode
	 * @return
	 */
	@At(ActionUtil.GET_BIZ_FORM_UI)
	public CuiFormModel getBizFormUI(String args, String argDataID, @Param("::data.") CocitBizDataNode dataNode) {
		CocitBizActionHelper helper = CocitBizActionHelper.make(args, argDataID, dataNode);

		CuiFormModel formModel = helper.modelFactory.getBizFormModel(helper.module, helper.table, helper.operation, helper.data);

		formModel.setData(helper.data);

		/**
		 * 返回
		 */
		return formModel;
	}

	/**
	 * 
	 * 保存业务数据
	 * 
	 * @param operationArgs
	 *            调用参数，参数组成“bizModuleID:bizTableID:bizOperationID”
	 * @param argDataID
	 *            dataID
	 * @param dataNode
	 * @return
	 */
	@At(ActionUtil.SAVE_BIZ_FORM_DATA)
	public CuiFormModelData saveBizFormData(String args, String argDataID, @Param("::data.") CocitBizDataNode dataNode) {
		CocitBizActionHelper helper = CocitBizActionHelper.make(args, argDataID, dataNode);

		CuiFormModel formModel = helper.modelFactory.getBizFormModel(helper.module, helper.table, helper.operation, helper.data);

		CuiFormModelData ret = new CuiFormModelData();
		ret.setModel(formModel);
		ret.setData(helper.data);

		try {
			helper.entityManager.save(helper.data, helper.operationMode);
		} catch (CocException e) {
			ret.setException(e);
		}

		return ret;
	}

	@At(ActionUtil.DELETE_BIZ_DATA)
	public CuiFormModelData deleteBizData(String args, String dataID) {
		CocitBizActionHelper helper = CocitBizActionHelper.make(args, dataID, null);

		CuiFormModel formModel = helper.modelFactory.getBizFormModel(helper.module, helper.table, helper.operation, helper.data);

		CuiFormModelData ret = new CuiFormModelData();
		ret.setModel(formModel);
		ret.setData(helper.data);

		try {
			String[] array = StringUtil.toArray(dataID);
			Long[] idArray = new Long[array.length];
			for (int i = 0; i < array.length; i++) {
				idArray[i] = Long.parseLong(array[i]);
			}
			helper.entityManager.delete(idArray, helper.operationMode);
		} catch (CocException e) {
			ret.setException(e);
		}

		return ret;
	}
}
