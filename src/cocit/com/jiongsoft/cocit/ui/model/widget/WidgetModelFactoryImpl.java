// $codepro.audit.disable unnecessaryImport
package com.jiongsoft.cocit.ui.model.widget;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.service.EntityFieldService;
import com.jiongsoft.cocit.service.EntityGroupService;
import com.jiongsoft.cocit.service.EntityOperationService;
import com.jiongsoft.cocit.service.EntityTableService;
import com.jiongsoft.cocit.service.ModuleService;
import com.jiongsoft.cocit.ui.model.widget.EntityFormWidgetModel.FormField;
import com.jiongsoft.cocit.ui.model.widget.GridWidgetModel.GridColumn;
import com.jiongsoft.cocit.util.ActionUtil;
import com.jiongsoft.cocit.util.KeyValue;
import com.jiongsoft.cocit.util.ObjectUtil;
import com.jiongsoft.cocit.util.StringUtil;
import com.jiongsoft.cocit.util.Tree;
import com.jiongsoft.cocit.util.Tree.Node;

public class WidgetModelFactoryImpl implements WidgetModelFactory {

	@Override
	public EntityModuleWidgetModel getEntytyModuleUI(ModuleService entityModule) {
		EntityTableService mainTable = entityModule.getEntityTable();
		EntityTableWidgetModel mainModel = getEntityTableUI(entityModule, mainTable);

		EntityModuleWidgetModel ret = new EntityModuleWidgetModel(mainModel);
		ret.setId("" + entityModule.getID());
		ret.setName(entityModule.getName());

		List<EntityTableService> childrenTables = entityModule.getChildrenEntityTables();
		if (childrenTables != null) {
			List<EntityTableWidgetModel> childrenModels = new ArrayList();
			for (EntityTableService table : childrenTables) {
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
	public EntityTableWidgetModel getEntityTableUI(ModuleService entityModule, EntityTableService entityTable) {
		EntityTableWidgetModel model = new EntityTableWidgetModel();

		model.setId("" + entityTable.getID());
		model.setName(entityTable.getName());

		model.setNaviTreeModel(this.getEntityNaviUI(entityModule, entityTable));
		model.setOperationMenuModel(this.getOperationMenuUI(entityModule, entityTable));
		model.setGridModel(this.getGridUI(entityModule, entityTable));

		// 将搜索框放在左边导航树顶部
		// model.setSearchBoxModel(this.getSearchBoxModel(module, table));

		return model;
	}

	@Override
	public SearchBoxWidgetModel getSearchBoxUI(ModuleService entityModule, EntityTableService entityTable) {

		SearchBoxWidgetModel ret = new SearchBoxWidgetModel();
		ret.setId("" + entityTable.getID());

		List<KeyValue> list = new ArrayList();
		for (EntityFieldService f : entityTable.getEntityFieldsForGrid()) {
			int type = f.getType();

			if (f.getDicOptions().length > 0//
					|| type == EntityFieldService.TYPE_FK//
					|| type == EntityFieldService.TYPE_BOOL//
					|| type == EntityFieldService.TYPE_UPLOAD//
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
	public GridWidgetModel getGridUI(ModuleService entityModule, EntityTableService entityTable) {
		GridWidgetModel model = new GridWidgetModel();

		model.setId("" + entityTable.getID());
		model.setName(entityTable.getName());
		Long moduleID = 0L;
		if (entityModule != null)
			moduleID = entityModule.getID();
		model.setDataLoadUrl(ActionUtil.GET_ENTITY_GRID_DATA.replace("*", ActionUtil.encodeArgs(moduleID, entityTable.getID())));

		// 创建Grid字段列
		List<EntityFieldService> fields = entityTable.getEntityFieldsForGrid();
		int count = 0;
		int columnsTotalWidth = 0;
		for (EntityFieldService fld : fields) {
			GridColumn col = new GridColumn(fld.getPropName(), fld.getName());
			col.setEntityField(fld);

			// 设置Grid列属性
			col.setAlign("left");
			byte type = fld.getType();
			switch (type) {
			case EntityFieldService.TYPE_NUMBER:
				col.setAlign("right");
				col.setWidth(60);
				break;
			case EntityFieldService.TYPE_BOOL:
				col.setWidth(60);
				break;
			case EntityFieldService.TYPE_DATE:
				col.setWidth(120);
				break;
			case EntityFieldService.TYPE_UPLOAD:
				col.setWidth(120);
				break;
			case EntityFieldService.TYPE_TEXT:
			case EntityFieldService.TYPE_RICH_TEXT:
				col.setWidth(200);
				break;
			case EntityFieldService.TYPE_FK:
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
	public MenuWidgetModel getOperationMenuUI(ModuleService entityModule, EntityTableService entityTable) {
		List<EntityOperationService> dataOperations = entityTable.getEntityOperations();

		MenuWidgetModel model = new MenuWidgetModel();
		model.setId("" + entityTable.getID());

		Tree tree = Tree.make();
		if (!ObjectUtil.isNil(dataOperations)) {
			for (EntityOperationService op : dataOperations) {
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
				child.set("opMode", op.getOperationMode());
				child.setSequence(op.getSequence());

			}
		}

		model.setData(tree);

		// 将搜索框放在菜单栏右边
		model.setSearchBoxModel(this.getSearchBoxUI(entityModule, entityTable));

		return model;
	}

	@Override
	public TreeWidgetModel getEntityNaviUI(ModuleService entityModule, EntityTableService entityTable) {
		if (ObjectUtil.isNil(entityTable.getEntityFieldsForNaviTree()))
			return null;

		// 创建树模型
		TreeWidgetModel model = new TreeWidgetModel();
		model.setId("" + entityTable.getID());
		model.set("onlyLeafCheck", "true");
		// model.set("onlyLeafValue", "true");

		// 设置异步加载数据的 URL 地址
		model.setDataLoadUrl(ActionUtil.GET_ENTITY_NAVI_DATA.replace("*", ActionUtil.encodeArgs(entityModule.getID(), entityTable.getID())));

		// 获取树数据
		// Tree entity = table.getNaviTree();
		// model.setData(entity);

		// 返回
		return model;
	}

	@Override
	public TreeWidgetData getEntityNaviData(ModuleService entityModule, EntityTableService entityTable) {
		if (ObjectUtil.isNil(entityTable.getEntityFieldsForNaviTree()))
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
	public EntityFormWidgetModel getEntityFormUI(ModuleService entityModule, EntityTableService entityTable, EntityOperationService entityOp, Object entityEntity) {
		EntityFormWidgetModel ret = new EntityFormWidgetModel();

		List<EntityGroupService> groups = entityTable.getEntityGroups();
		for (EntityGroupService group : groups) {
			FormField groupField = new FormField(group.getName());

			List<EntityFieldService> entityfields = group.getEntityFields();
			if (entityfields != null) {
				for (EntityFieldService entityfield : entityfields) {
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
