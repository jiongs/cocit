package com.jiongsoft.cocit.cocui.model;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocui.model.CuiFormModel.FormField;
import com.jiongsoft.cocit.cocui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;

public class SimpleCuiModelFactory implements CuiModelFactory {

	@Override
	public CuiBizModuleModel getBizModuleModel(CocBizModule bizModule) {
		CocBizTable mainTable = bizModule.getMainBizTable();
		CuiBizTableModel mainModel = getBizTableModel(bizModule, mainTable);

		CuiBizModuleModel ret = new CuiBizModuleModel(mainModel);
		ret.setId("" + bizModule.getID());
		ret.setName(bizModule.getName());

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

		// 将搜索框放在左边导航树顶部
		// model.setSearchBoxModel(this.getSearchBoxModel(bizModule, bizTable));

		return model;
	}

	@Override
	public CuiSearchBoxModel getSearchBoxModel(CocBizModule bizModule, CocBizTable bizTable) {

		CuiSearchBoxModel ret = new CuiSearchBoxModel();
		ret.setId("" + bizTable.getID());

		List<KeyValue> list = new ArrayList();
		for (CocBizField f : bizTable.getBizFieldsForGrid()) {
			int type = f.getType();
			if (type == CocBizField.TYPE_FK || type == CocBizField.TYPE_BOOL || type == CocBizField.TYPE_UPLOAD) {
				continue;
			}
			list.add(KeyValue.make(f.getName(), f.getPropName()));
		}

		ret.setData(list);

		return ret;
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
			col.setAlign("left");
			byte type = fld.getType();
			switch (type) {
			case CocBizField.TYPE_NUMBER:
				col.setAlign("right");
				col.setWidth(60);
				break;
			case CocBizField.TYPE_BOOL:
				col.setWidth(60);
				break;
			case CocBizField.TYPE_DATE:
				col.setWidth(120);
				break;
			case CocBizField.TYPE_UPLOAD:
				col.setWidth(120);
				break;
			case CocBizField.TYPE_TEXT:
			case CocBizField.TYPE_RICH_TEXT:
				col.setWidth(200);
				break;
			case CocBizField.TYPE_FK:
			default:
				col.setWidth(150);
			}
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
		if (!Lang.isNil(dataOperations)) {
			for (CocBizOperation op : dataOperations) {
				String parentNodeID = null;
				if (op.getParentID() != null) {
					parentNodeID = "" + op.getParentID();
				}
				String nodeID = "" + op.getID();
				Node child = tree.addNode(parentNodeID, nodeID);

				child.setName(op.getName());
				String pathArgs = ActionUtil.encodeArgs(bizModule.getID(), bizTable.getID(), op.getID());

				// pathArgs = moduleID:tableID:operationID
				child.set("pathArgs", pathArgs);
				child.set("operationCode", op.getOperationCode());
				child.setSequence(op.getSequence());

				// addOperationsToMenuTree(tree, nodeID, op.getChildrenBizOperations());
			}
		}

		model.setData(tree);

		// 将搜索框放在菜单栏右边
		model.setSearchBoxModel(this.getSearchBoxModel(bizModule, bizTable));

		return model;
	}

	@Override
	public CuiTreeModel getNaviTreeModel(CocBizModule bizModule, CocBizTable bizTable) {
		if (Lang.isNil(bizTable.getBizFieldsForNaviTree()))
			return null;

		// 创建树模型
		CuiTreeModel model = new CuiTreeModel();
		model.setId("" + bizTable.getID());

		// 设置异步加载数据的 URL 地址
		model.setDataLoadUrl(ActionUtil.GET_BIZ_TABLE_NAVI_TREE_DATA.replace("*", ActionUtil.encodeArgs(bizModule.getID(), bizTable.getID())));

		// 获取树数据
		Tree data = bizTable.getNaviTree();
		model.setData(data);

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

	@Override
	public CuiFormModel getBizFormModel(CocBizModule bizModule, CocBizTable bizTable, CocBizOperation bizOp, Object bizEntity) {
		CuiFormModel ret = new CuiFormModel();

		List<FormField> fields = new ArrayList();
		ret.setFields(fields);

		List<CocBizField> bizfields = bizTable.getBizFields();
		for (CocBizField bizfield : bizfields) {
			FormField field = new FormField(bizfield.getPropName(), bizfield.getName());
			field.setMode(bizfield.getEditMode(bizOp.getOperationMode()));
			field.setType(bizfield.getType());
			field.setPattern(bizfield.getPattern());
			field.setProps(bizfield.getExtProps());

			field.setBizField(bizfield);

			fields.add(field);
		}

		return ret;
	}
}
