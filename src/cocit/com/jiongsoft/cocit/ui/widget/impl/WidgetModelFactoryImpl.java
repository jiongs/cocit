// $codepro.audit.disable unnecessaryImport
package com.jiongsoft.cocit.ui.widget.impl;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.service.CocEntityFieldService;
import com.jiongsoft.cocit.service.CocEntityGroupService;
import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityOperationService;
import com.jiongsoft.cocit.service.CocEntityTableService;
import com.jiongsoft.cocit.ui.widget.EntityFormWidgetModel;
import com.jiongsoft.cocit.ui.widget.EntityModuleWidgetModel;
import com.jiongsoft.cocit.ui.widget.EntityTableWidgetModel;
import com.jiongsoft.cocit.ui.widget.GridWidgetModel;
import com.jiongsoft.cocit.ui.widget.MenuWidgetModel;
import com.jiongsoft.cocit.ui.widget.SearchBoxWidgetModel;
import com.jiongsoft.cocit.ui.widget.TreeWidgetModel;
import com.jiongsoft.cocit.ui.widget.TreeWidgetData;
import com.jiongsoft.cocit.ui.widget.CuiWidgetModelFactory;
import com.jiongsoft.cocit.ui.widget.EntityFormWidgetModel.FormField;
import com.jiongsoft.cocit.ui.widget.GridWidgetModel.GridColumn;
import com.jiongsoft.cocit.utils.ActionUtil;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.StringUtil;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;

public class WidgetModelFactoryImpl implements CuiWidgetModelFactory {

	@Override
	public EntityModuleWidgetModel getEntytyModuleUI(CocEntityModuleService entityModule) {
		CocEntityTableService mainTable = entityModule.getEntityTable();
		EntityTableWidgetModel mainModel = getEntityTableUI(entityModule, mainTable);

		EntityModuleWidgetModel ret = new EntityModuleWidgetModel(mainModel);
		ret.setId("" + entityModule.getID());
		ret.setName(entityModule.getName());

		List<CocEntityTableService> childrenTables = entityModule.getChildrenEntityTables();
		if (childrenTables != null) {
			List<EntityTableWidgetModel> childrenModels = new ArrayList();
			for (CocEntityTableService table : childrenTables) {
				EntityTableWidgetModel model = new EntityTableWidgetModel();

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
	public EntityTableWidgetModel getEntityTableUI(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		EntityTableWidgetModel model = new EntityTableWidgetModel();

		model.setId("" + entityTable.getID());
		model.setName(entityTable.getName());

		model.setNaviTreeModel(this.getEntityNaviUI(entityModule, entityTable));
		model.setOperationMenuModel(this.getOperationMenuUI(entityModule, entityTable));
		model.setGridModel(this.getGridUI(entityModule, entityTable));

		// 将搜索框放在左边导航树顶部
		// model.setSearchBoxModel(this.getSearchBoxModel(moduleService, tableService));

		return model;
	}

	@Override
	public SearchBoxWidgetModel getSearchBoxUI(CocEntityModuleService entityModule, CocEntityTableService entityTable) {

		SearchBoxWidgetModel ret = new SearchBoxWidgetModel();
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
	public GridWidgetModel getGridUI(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		GridWidgetModel model = new GridWidgetModel();

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
	public MenuWidgetModel getOperationMenuUI(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		List<CocEntityOperationService> dataOperations = entityTable.getEntityOperations();

		MenuWidgetModel model = new MenuWidgetModel();
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

				// opArgs = moduleID:tableID:operationID
				child.set("opArgs", pathArgs);
				child.set("operationCode", op.getOperationCode());
				child.set("operationMode", op.getOperationMode());
				child.setSequence(op.getSequence());

			}
		}

		model.setData(tree);

		// 将搜索框放在菜单栏右边
		model.setSearchBoxModel(this.getSearchBoxUI(entityModule, entityTable));

		return model;
	}

	@Override
	public TreeWidgetModel getEntityNaviUI(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		if (Lang.isNil(entityTable.getEntityFieldsForNaviTree()))
			return null;

		// 创建树模型
		TreeWidgetModel model = new TreeWidgetModel();
		model.setId("" + entityTable.getID());
		model.set("onlyLeafCheck", "true");
		// model.set("onlyLeafValue", "true");

		// 设置异步加载数据的 URL 地址
		model.setDataLoadUrl(ActionUtil.GET_ENTITY_NAVI_DATA.replace("*", ActionUtil.encodeArgs(entityModule.getID(), entityTable.getID())));

		// 获取树数据
		// Tree entity = tableService.getNaviTree();
		// model.setData(entity);

		// 返回
		return model;
	}

	@Override
	public TreeWidgetData getEntityNaviData(CocEntityModuleService entityModule, CocEntityTableService entityTable) {
		if (Lang.isNil(entityTable.getEntityFieldsForNaviTree()))
			return null;

		// 创建模型
		TreeWidgetData ret = new TreeWidgetData();
		TreeWidgetModel model = new TreeWidgetModel();
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
	public EntityFormWidgetModel getEntityFormUI(CocEntityModuleService entityModule, CocEntityTableService entityTable, CocEntityOperationService entityOp, Object entityEntity) {
		EntityFormWidgetModel ret = new EntityFormWidgetModel();

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
