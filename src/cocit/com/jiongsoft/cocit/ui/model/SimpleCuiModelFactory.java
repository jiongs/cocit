// $codepro.audit.disable unnecessaryImport
package com.jiongsoft.cocit.ui.model;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.service.CocEntityFieldService;
import com.jiongsoft.cocit.service.CocEntityGroupService;
import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityOperationService;
import com.jiongsoft.cocit.service.CocEntityTableService;
import com.jiongsoft.cocit.ui.model.CuiFormModel.FormField;
import com.jiongsoft.cocit.ui.model.CuiGridModel.GridColumn;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.StringUtil;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;

public class SimpleCuiModelFactory implements CuiModelFactory {

	@Override
	public CuiEntityModuleModel getEntytyModuleUI(CocEntityModuleService entityModule) {
		CocEntityTableService mainTable = entityModule.getEntityTable();
		CuiEntityTableModel mainModel = getEntityTableUI(entityModule, mainTable);

		CuiEntityModuleModel ret = new CuiEntityModuleModel(mainModel);
		ret.setId("" + entityModule.getID());
		ret.setName(entityModule.getName());

		List<CocEntityTableService> childrenTables = entityModule.getChildrenEntityTables();
		if (childrenTables != null) {
			List<CuiEntityTableModel> childrenModels = new ArrayList();
			for (CocEntityTableService table : childrenTables) {
				CuiEntityTableModel model = new CuiEntityTableModel();

				model.setId("" + table.getID());
				model.setName(table.getName());
				model.set("fkfield", table.get("fkfield", ""));

				model.setLoadUrl(ActionUtil.GET_ENTITY_TABLE_UI.replace("*", ActionUtil.encodeArgs(entityModule.getID(), table.getID())));

				childrenModels.add(model);
			}
			ret.setChildrenEntityTableModels(childrenModels);
		}

		return ret;
	}

	@Override
	public CuiEntityTableModel getEntityTableUI(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		CuiEntityTableModel model = new CuiEntityTableModel();

		model.setId("" + entityTable.getID());
		model.setName(entityTable.getName());

		model.setNaviTreeModel(this.getEntityNaviUI(entityModule, entityTable));
		model.setOperationMenuModel(this.getOperationMenuModel(entityModule, entityTable));
		model.setGridModel(this.getGridModel(entityModule, entityTable));

		// 将搜索框放在左边导航树顶部
		// model.setSearchBoxModel(this.getSearchBoxModel(entityModule, entityTable));

		return model;
	}

	@Override
	public CuiSearchBoxModel getSearchBoxModel(CocEntityModuleService entityModule, CocEntityTableService entityTable) {

		CuiSearchBoxModel ret = new CuiSearchBoxModel();
		ret.setId("" + entityTable.getID());

		List<KeyValue> list = new ArrayList();
		for (CocEntityFieldService f : entityTable.getEntityFieldsForGrid()) {
			int type = f.getType();

			if (f.getDicOptions().length > 0//
					|| type == CocEntityFieldService.TYPE_FK//
					|| type == CocEntityFieldService.TYPE_BOOL//
					|| type == CocEntityFieldService.TYPE_UPLOAD//
			) {
				continue;
			}

			KeyValue kv = KeyValue.make(f.getName(), f.getPropName());
			kv.set("type", "" + type);
			list.add(kv);
		}

		ret.setData(list);

		return ret;
	}

	@Override
	public CuiGridModel getGridModel(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		CuiGridModel model = new CuiGridModel();

		model.setId("" + entityTable.getID());
		model.setName(entityTable.getName());
		Long moduleID = 0L;
		if (entityModule != null)
			moduleID = entityModule.getID();
		model.setDataLoadUrl(ActionUtil.GET_ENTITY_GRID_DATA.replace("*", ActionUtil.encodeArgs(moduleID, entityTable.getID())));

		// 创建Grid字段列
		List<CocEntityFieldService> fields = entityTable.getEntityFieldsForGrid();
		int count = 0;
		int columnsTotalWidth = 0;
		for (CocEntityFieldService fld : fields) {
			GridColumn col = new GridColumn(fld.getPropName(), fld.getName());
			col.setEntityField(fld);

			// 设置Grid列属性
			col.setAlign("left");
			byte type = fld.getType();
			switch (type) {
			case CocEntityFieldService.TYPE_NUMBER:
				col.setAlign("right");
				col.setWidth(60);
				break;
			case CocEntityFieldService.TYPE_BOOL:
				col.setWidth(60);
				break;
			case CocEntityFieldService.TYPE_DATE:
				col.setWidth(120);
				break;
			case CocEntityFieldService.TYPE_UPLOAD:
				col.setWidth(120);
				break;
			case CocEntityFieldService.TYPE_TEXT:
			case CocEntityFieldService.TYPE_RICH_TEXT:
				col.setWidth(200);
				break;
			case CocEntityFieldService.TYPE_FK:
			default:
				col.setWidth(150);
			}
			col.setPattern(fld.getPattern());

			columnsTotalWidth += col.getWidth();

			model.addColumn(col);

			count++;
			if (count == 8)
				break;
		}

		model.setColumnsTotalWidth(columnsTotalWidth);

		return model;
	}

	@Override
	public CuiMenuModel getOperationMenuModel(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		List<CocEntityOperationService> dataOperations = entityTable.getEntityOperations();

		CuiMenuModel model = new CuiMenuModel();
		model.setId("" + entityTable.getID());

		Tree tree = Tree.make();
		if (!Lang.isNil(dataOperations)) {
			for (CocEntityOperationService op : dataOperations) {
				String parentNodeID = null;
				if (op.getParentID() != null) {
					parentNodeID = "" + op.getParentID();
				}
				String nodeID = "" + op.getID();
				Node child = tree.addNode(parentNodeID, nodeID);

				child.setName(op.getName());

				String pathArgs = ActionUtil.encodeArgs(entityModule.getID(), entityTable.getID(), op.getID());

				// pathArgs = entityModuleID:entityTableID:entityOperationID
				child.set("pathArgs", pathArgs);
				child.set("operationCode", op.getOperationCode());
				child.set("operationMode", op.getOperationMode());
				child.setSequence(op.getSequence());

			}
		}

		model.setData(tree);

		// 将搜索框放在菜单栏右边
		model.setSearchBoxModel(this.getSearchBoxModel(entityModule, entityTable));

		return model;
	}

	@Override
	public CuiTreeModel getEntityNaviUI(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		if (Lang.isNil(entityTable.getEntityFieldsForNaviTree()))
			return null;

		// 创建树模型
		CuiTreeModel model = new CuiTreeModel();
		model.setId("" + entityTable.getID());
		model.set("onlyLeafCheck", "true");
		// model.set("onlyLeafValue", "true");

		// 设置异步加载数据的 URL 地址
		model.setDataLoadUrl(ActionUtil.GET_ENTITY_NAVI_DATA.replace("*", ActionUtil.encodeArgs(entityModule.getID(), entityTable.getID())));

		// 获取树数据
		// Tree entityData = entityTable.getNaviTree();
		// model.setData(entityData);

		// 返回
		return model;
	}

	@Override
	public CuiTreeModelData getEntityNaviData(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		if (Lang.isNil(entityTable.getEntityFieldsForNaviTree()))
			return null;

		// 创建模型
		CuiTreeModelData ret = new CuiTreeModelData();
		CuiTreeModel model = new CuiTreeModel();
		model.setId("" + entityTable.getID());

		// 查询数据
		Tree data = entityTable.getEntityNaviData();

		// 设置模型属性
		ret.setModel(model);
		ret.setData(data);

		// 返回
		return ret;
	}

	@Override
	public CuiFormModel getEntityFormUI(CocEntityModuleService entityModule, CocEntityTableService entityTable, CocEntityOperationService entityOp, Object entityEntity) {
		CuiFormModel ret = new CuiFormModel();

		List<CocEntityGroupService> groups = entityTable.getEntityGroups();
		for (CocEntityGroupService group : groups) {
			FormField groupField = new FormField(group.getName());

			List<CocEntityFieldService> entityfields = group.getEntityFields();
			if (entityfields != null) {
				for (CocEntityFieldService entityfield : entityfields) {
					if (entityfield.isDisabled())
						continue;

					FormField field = new FormField(entityfield.getName());

					String propName = entityfield.getPropName();
					String opMode = entityOp.getOperationMode();
					String mode = entityfield.getMode(opMode);

					// 计算字段展现模式
					if (StringUtil.isNil(mode)) {
						if (opMode.equals("e") || opMode.equals("c")) {
							if (propName.equals("created") || propName.equals("createdBy") || propName.equals("updated") || propName.equals("updatedBy"))
								mode = "N";
							else
								mode = "E";
						} else if (opMode.equals("v")) {
							mode = "S";
						}
					}

					field.setField(propName);
					field.setMode(mode);
					field.setType(entityfield.getType());
					field.setPattern(entityfield.getPattern());
					field.setProps(entityfield.getExtProps());
					field.setEntityField(entityfield);

					groupField.addChild(field);
				}

				ret.addGroupField(groupField);
			}
		}

		return ret;
	}
}
