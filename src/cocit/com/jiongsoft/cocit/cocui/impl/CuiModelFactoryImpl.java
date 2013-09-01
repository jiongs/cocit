package com.jiongsoft.cocit.cocui.impl;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.cocobj.CobDataField;
import com.jiongsoft.cocit.cocobj.CobDataModule;
import com.jiongsoft.cocit.cocobj.CobDataOperation;
import com.jiongsoft.cocit.cocobj.CobDataTable;
import com.jiongsoft.cocit.cocui.CuiModelFactory;
import com.jiongsoft.cocit.cocui.CuiPaths;
import com.jiongsoft.cocit.cocui.model.CuiDataModuleModel;
import com.jiongsoft.cocit.cocui.model.CuiDataTableModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel;
import com.jiongsoft.cocit.cocui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.cocui.model.CuiMenuModel;
import com.jiongsoft.cocit.cocui.model.CuiSearchBoxModel;
import com.jiongsoft.cocit.cocui.model.CuiTreeModel;
import com.jiongsoft.cocit.utils.TreeNode;

public class CuiModelFactoryImpl implements CuiModelFactory {

	@Override
	public CuiDataModuleModel getDataModuleModel(CobDataModule module) {
		CobDataTable mainTable = module.getMainDataTable();
		CuiDataTableModel mainModel = getDataTableModel(module, mainTable);

		CuiDataModuleModel ret = new CuiDataModuleModel(mainModel);

		List<CobDataTable> childrenTables = module.getChildrenDataTables();
		if (childrenTables != null) {
			List<CuiDataTableModel> childrenModels = new ArrayList();
			for (CobDataTable table : childrenTables) {
				childrenModels.add(getDataTableModel(module, table));
			}
		}

		return ret;
	}

	@Override
	public CuiDataTableModel getDataTableModel(CobDataModule module, CobDataTable dataTable) {
		CuiDataTableModel model = new CuiDataTableModel();

		model.setId(dataTable.getID());
		model.setName(dataTable.getName());

		model.setTreeModel(this.getNaviTreeModel(module, dataTable));
		model.setMenuModel(this.getOperationMenuModel(module, dataTable));
		model.setGridModel(this.getGridModel(module, dataTable));
		model.setSearchBoxModel(this.getSearchBoxModel(module, dataTable));

		return model;
	}

	@Override
	public CuiSearchBoxModel getSearchBoxModel(CobDataModule module, CobDataTable dataTable) {
		return new CuiSearchBoxModel();
	}

	@Override
	public CuiGridModel getGridModel(CobDataModule module, CobDataTable dataTable) {
		CuiGridModel model = new CuiGridModel();

		model.setDataLoadUrl(CuiPaths.DATA_GRIDDATA.replace("*", CuiPaths.encodeArgs(module.getID() + ":" + dataTable.getID())));

		// 创建Grid字段列
		List<CobDataField> fields = dataTable.getDataFieldsForGrid();
		for (CobDataField fld : fields) {
			GridColumn col = new GridColumn(fld.getPropName(), fld.getName());

			// 设置Grid列属性
			if (fld.getType() == CobDataField.TYPE_NUMBER)
				col.setAssign("right");
			else
				col.setAssign("left");
			col.setWidth(fld.getGridWidth());

			model.addColumn(col);
		}

		return model;
	}

	@Override
	public CuiMenuModel getOperationMenuModel(CobDataModule module, CobDataTable dataTable) {
		List<CobDataOperation> dataOperations = dataTable.getDataOperations();

		CuiMenuModel model = new CuiMenuModel();

		TreeNode data = new TreeNode();
		addChildrenTo(data, dataOperations);

		model.setData(data);

		return model;
	}

	private void addChildrenTo(TreeNode node, List<CobDataOperation> operations) {
		if (operations == null)
			return;

		for (CobDataOperation op : operations) {
			TreeNode child = new TreeNode("" + op.getID(), op.getName());

			addChildrenTo(child, op.getChildrenDataOperations());

			node.addChild(child);
		}
	}

	@Override
	public CuiTreeModel getNaviTreeModel(CobDataModule module, CobDataTable dataTable) {
		List<CobDataField> dataFields = dataTable.getDataFieldsForNaviTree();
		if (dataFields == null || dataFields.size() == 0) {
			return null;
		}

		CuiTreeModel model = new CuiTreeModel();

		model.setDataLoadUrl(CuiPaths.DATA_NAVITREEDATA.replace("*", CuiPaths.encodeArgs(module.getID() + ":" + dataTable.getID())));

		return model;
	}

}
