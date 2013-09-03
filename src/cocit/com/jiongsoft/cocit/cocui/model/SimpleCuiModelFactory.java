package com.jiongsoft.cocit.cocui.model;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;

public class SimpleCuiModelFactory implements CuiModelFactory {

	@Override
	public CuiBizModuleModel getBizModuleModel(CocBizModule bizModule) {
		CocBizTable mainTable = bizModule.getMainBizTable();
		CuiBizTableModel mainModel = getBizTableModel(bizModule, mainTable);

		CuiBizModuleModel ret = new CuiBizModuleModel(mainModel);

		List<CocBizTable> childrenTables = bizModule.getChildrenBizTables();
		if (childrenTables != null) {
			List<CuiBizTableModel> childrenModels = new ArrayList();
			for (CocBizTable table : childrenTables) {
				CuiBizTableModel model = new CuiBizTableModel();

				model.setId("" + table.getID());
				model.setName(table.getName());

				model.setLoadUrl(ActionUtil.GET_BIZ_TABLE_MODEL.replace("*", ActionUtil.encodeArgs(bizModule.getID(), table.getID())));

				childrenModels.add(model);
			}
			ret.setChildrenBizTableModels(childrenModels);
		}

		return ret;
	}

	@Override
	public CuiBizTableModel getBizTableModel(CocBizModule bizModule, CocBizTable bizTable) {
		CuiBizTableModel model = new CuiBizTableModel();

		model.setId("" + bizTable.getID());
		model.setName(bizTable.getName());

		model.setNaviTreeModel(this.getNaviTreeModel(bizModule, bizTable));
		model.setOperationMenuModel(this.getOperationMenuModel(bizModule, bizTable));
		model.setGridModel(this.getGridModel(bizModule, bizTable));
		model.setSearchBoxModel(this.getSearchBoxModel(bizModule, bizTable));

		return model;
	}

	@Override
	public CuiSearchBoxModel getSearchBoxModel(CocBizModule bizModule, CocBizTable bizTable) {
		return new CuiSearchBoxModel();
	}

	@Override
	public CuiGridModel getGridModel(CocBizModule bizModule, CocBizTable bizTable) {
		CuiGridModel model = new CuiGridModel();

		model.setId("" + bizTable.getID());
		model.setName(bizTable.getName());
		model.setDataLoadUrl(ActionUtil.GET_BIZ_TABLE_GRID_DATA.replace("*", ActionUtil.encodeArgs(bizModule.getID(), bizTable.getID())));

		// 创建Grid字段列
		List<CocBizField> fields = bizTable.getBizFieldsForGrid();
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
	public CuiMenuModel getOperationMenuModel(CocBizModule bizModule, CocBizTable bizTable) {
		List<CocBizOperation> dataOperations = bizTable.getBizOperations();

		CuiMenuModel model = new CuiMenuModel();
		model.setId("" + bizTable.getID());

		Tree tree = Tree.make();
		addOperationsToMenuTree(tree, null, dataOperations);

		model.setData(tree);

		return model;
	}

	private void addOperationsToMenuTree(Tree tree, String parentNodeID, List<CocBizOperation> operations) {
		if (operations == null)
			return;

		for (CocBizOperation op : operations) {
			String nodeID = "op_" + op.getID();
			Node child = tree.addNode(parentNodeID, nodeID);
			child.setName(op.getName());
			child.set("logo", op.getLogo());
			child.setSequence(op.getSequence());

			addOperationsToMenuTree(tree, nodeID, op.getChildrenBizOperations());
		}
	}

	@Override
	public CuiTreeModel getNaviTreeModel(CocBizModule bizModule, CocBizTable bizTable) {
		if (Lang.isNil(bizTable.getBizFieldsForNaviTree()))
			return null;

		// 创建树模型
		CuiTreeModel model = new CuiTreeModel();
		model.setId("" + bizTable.getID());

		// 设置树模型属性
		model.setDataLoadUrl(ActionUtil.GET_BIZ_TABLE_NAVI_TREE_DATA.replace("*", ActionUtil.encodeArgs(bizModule.getID(), bizTable.getID())));

		// 返回
		return model;
	}

	@Override
	public CuiTreeModelData getNaviTreeModelData(CocBizModule bizModule, CocBizTable bizTable) {
		if (Lang.isNil(bizTable.getBizFieldsForNaviTree()))
			return null;

		// 创建模型
		CuiTreeModelData ret = new CuiTreeModelData();
		CuiTreeModel model = new CuiTreeModel();
		model.setId("" + bizTable.getID());

		// 查询数据
		Tree data = bizTable.getNaviTree();

		// 设置模型属性
		ret.setModel(model);
		ret.setData(data);

		// 返回
		return ret;
	}

}
