package com.jiongsoft.cocit.cocui.model;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.TreeNode;

public class CuiModelFactoryImpl implements CuiModelFactory {

	@Override
	public CuiBizModuleModel getBizModuleModel(CocBizModule module) {
		CocBizTable mainTable = module.getMainBizTable();
		CuiBizTableModel mainModel = getBizTableModel(module, mainTable);

		CuiBizModuleModel ret = new CuiBizModuleModel(mainModel);

		List<CocBizTable> childrenTables = module.getChildrenBizTables();
		if (childrenTables != null) {
			List<CuiBizTableModel> childrenModels = new ArrayList();
			for (CocBizTable table : childrenTables) {
				childrenModels.add(getBizTableModel(module, table));
			}
		}

		return ret;
	}

	@Override
	public CuiBizTableModel getBizTableModel(CocBizModule module, CocBizTable dataTable) {
		CuiBizTableModel model = new CuiBizTableModel();

		model.setId(dataTable.getID());
		model.setName(dataTable.getName());

		model.setTreeModel(this.getNaviTreeModel(module, dataTable));
		model.setMenuModel(this.getOperationMenuModel(module, dataTable));
		model.setGridModel(this.getGridModel(module, dataTable));
		model.setSearchBoxModel(this.getSearchBoxModel(module, dataTable));

		return model;
	}

	@Override
	public CuiSearchBoxModel getSearchBoxModel(CocBizModule module, CocBizTable dataTable) {
		return new CuiSearchBoxModel();
	}

	@Override
	public CuiGridModel getGridModel(CocBizModule module, CocBizTable dataTable) {
		CuiGridModel model = new CuiGridModel();

		model.setId("" + dataTable.getID());
		model.setName(dataTable.getName());
		model.setDataLoadUrl(ActionUtil.GET_BIZ_TABLE_GRID_DATA.replace("*", ActionUtil.encodeArgs(module.getID(), dataTable.getID())));

		// 创建Grid字段列
		List<CocBizField> fields = dataTable.getBizFieldsForGrid();
		int count = 0;
		for (CocBizField fld : fields) {
			GridColumn col = new GridColumn(fld.getPropName(), fld.getName());

			// 设置Grid列属性
			if (fld.getType() == CocBizField.TYPE_NUMBER)
				col.setAlign("right");
			else
				col.setAlign("left");
			col.setWidth(fld.getGridWidth());
			col.setPattern(fld.getPattern());

			model.addColumn(col);

			count++;
			if (count == 8)
				break;
		}

		return model;
	}

	@Override
	public CuiMenuModel getOperationMenuModel(CocBizModule module, CocBizTable dataTable) {
		List<CocBizOperation> dataOperations = dataTable.getBizOperations();

		CuiMenuModel model = new CuiMenuModel();

		TreeNode data = new TreeNode();
		addChildrenTo(data, dataOperations);

		model.setData(data);

		return model;
	}

	private void addChildrenTo(TreeNode node, List<CocBizOperation> operations) {
		if (operations == null)
			return;

		for (CocBizOperation op : operations) {
			TreeNode child = new TreeNode("" + op.getID(), op.getName());

			addChildrenTo(child, op.getChildrenBizOperations());

			node.addChild(child);
		}
	}

	@Override
	public CuiTreeModel getNaviTreeModel(CocBizModule module, CocBizTable dataTable) {
		List<CocBizField> dataFields = dataTable.getBizFieldsForNaviTree();
		if (dataFields == null || dataFields.size() == 0) {
			return null;
		}

		CuiTreeModel model = new CuiTreeModel();

		model.setDataLoadUrl(ActionUtil.GET_BIZ_TABLE_NAVI_TREE_DATA.replace("*", ActionUtil.encodeArgs(module.getID(), dataTable.getID())));

		return model;
	}

}
