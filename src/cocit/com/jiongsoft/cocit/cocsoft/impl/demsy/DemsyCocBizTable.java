package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizGroup;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.utils.ClassUtil;
import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;
import com.jiongsoft.cocit.utils.SortUtil;
import com.jiongsoft.cocit.utils.StringUtil;
import com.jiongsoft.cocit.utils.Tree;
import com.jiongsoft.cocit.utils.Tree.Node;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.IBizField;
import com.kmetop.demsy.comlib.biz.IBizSystem;
import com.kmetop.demsy.comlib.impl.base.biz.BizAction;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.impl.sft.system.SFTSystem;
import com.kmetop.demsy.comlib.impl.sft.system.SystemDataGroup;
import com.kmetop.demsy.engine.BizEngine;
import com.kmetop.demsy.lang.Obj;
import com.kmetop.demsy.orm.IOrm;

class DemsyCocBizTable implements CocBizTable {
	private BizEngine bizEngine;
	private SFTSystem entity;
	private List<CocBizGroup> bizGroups;
	private List<CocBizField> bizFields;
	private List<CocBizOperation> bizOperations;

	private Properties extProps;

	DemsyCocBizTable(SFTSystem e) {
		bizEngine = (BizEngine) Demsy.bizEngine;

		entity = e;
		bizGroups = new ArrayList();
		bizFields = new ArrayList();
		bizOperations = new ArrayList();
		extProps = new Properties();

		List<AbstractSystemData> dataFields = (List<AbstractSystemData>) bizEngine.getFields(entity);
		List<BizAction> dataOperations = (List<BizAction>) bizEngine.getActions(entity);

		initDataFields(dataFields);
		initDataOperations(dataOperations);
	}

	@Override
	public Properties getExtProps() {
		return entity.getDynaProp();
	}

	@Override
	public Long getID() {
		return entity.getId();
	}

	@Override
	public int getSequence() {
		return entity.getOrderby();
	}

	@Override
	public String getName() {
		return entity.getName();
	}

	@Override
	public boolean isDisabled() {
		return entity.isDisabled();
	}

	@Override
	public String getInfo() {
		return entity.getDesc();
	}

	@Override
	public Date getCreatedDate() {
		return entity.getCreated();
	}

	@Override
	public String getCreatedUser() {
		return entity.getCreatedBy();
	}

	@Override
	public Date getLatestModifiedDate() {
		return entity.getUpdated();
	}

	@Override
	public String getLatestModifiedUser() {
		return entity.getUpdatedBy();
	}

	@Override
	public <T> T get(String propName, T defaultReturn) {
		String value = this.extProps.getProperty(propName);
		if (value == null)
			value = entity.get(propName);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) value;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.castTo(value, valueType);
		} catch (Throwable e) {
		}

		return defaultReturn;
	}

	@Override
	public List<CocBizGroup> getBizGroups() {
		return bizGroups;
	}

	@Override
	public List<CocBizField> getBizFields() {

		return bizFields;
	}

	@Override
	public List<CocBizOperation> getBizOperations() {
		return bizOperations;
	}

	@Override
	public List<CocBizField> getBizFieldsForNaviTree() {
		List<CocBizField> ret = new ArrayList();

		for (CocBizField f : this.bizFields) {
			DemsyCocBizField field = (DemsyCocBizField) f;
			AbstractSystemData data = field.getEntity();

			if (data.isDisabledNavi() || data.isDisabled()) {
				continue;
			}

			if ((field.isFK() && !data.isMappingToMaster()) || field.isV1Dic() || field.isBoolean() || !StringUtil.isNil(data.getOptions()))
				ret.add(field);

		}

		return ret;
	}

	@Override
	public List<CocBizField> getBizFieldsForGrid() {
		List ret = new ArrayList();

		for (CocBizField f : this.bizFields) {
			DemsyCocBizField field = (DemsyCocBizField) f;
			AbstractSystemData data = field.getEntity();

			if (data.isDisabledNavi() && data.isDisabled())
				continue;

			if (data.getRefrenceSystem() != null && data.getRefrenceField() != null)
				continue;

			if (data.isGridField() && data.getGridOrder() > 0)
				ret.add(field);

		}

		SortUtil.sort(ret, "gridOrder", true);

		return ret;
	}

	private void initDataFields(List<AbstractSystemData> dataFields) {
		if (dataFields == null)
			return;
		Map<Long, DemsyCocBizGroup> bizGroupsMap = new HashMap();

		for (AbstractSystemData systemData : dataFields) {
			SystemDataGroup dataGroup = systemData.getDataGroup();
			Long groupID = dataGroup.getId();

			DemsyCocBizGroup bizGroup = (DemsyCocBizGroup) bizGroupsMap.get(groupID);
			if (bizGroup == null) {
				bizGroup = new DemsyCocBizGroup(dataGroup);
				bizGroupsMap.put(groupID, bizGroup);
				bizGroups.add(bizGroup);
			}

			CocBizField bizField = new DemsyCocBizField(systemData);
			this.bizFields.add(bizField);
			bizGroup.addField(bizField);
		}
	}

	public Map<String, CocBizField> getBizFieldsMapByPropName() {
		Map<String, CocBizField> map = new HashMap();
		for (CocBizField f : this.bizFields) {
			map.put(f.getPropName(), f);
		}

		return map;
	}

	private void initDataOperations(List<BizAction> dataOperations) {
		if (dataOperations == null)
			return;

		for (BizAction g : dataOperations) {
			this.bizOperations.add(new DemsyCocBizOperation(g));
		}
	}

	@Override
	public Tree getNaviTree() {

		Tree tree = Tree.make();

		for (CocBizField fld : this.getBizFieldsForNaviTree()) {
			DemsyCocBizField demsyFld = (DemsyCocBizField) fld;
			Node node = tree.addNode(null, fld.getPropName()).setName(fld.getName());
			node.set("open", "true");

			boolean success = this.makeNodes(tree, node, demsyFld.getEntity());
			if (!success) {
				tree.removeNode(node);
			}
		}

		// 如果导航树节点总数没有超过边框则全部展开
		// root.optimizeStatus();

		tree.sort();

		return tree;
	}

	private boolean makeNodes(Tree tree, Node node, AbstractSystemData field) {
		BizEngine bizEngine = (BizEngine) Demsy.bizEngine;

		DemsyCocBizField cocField = new DemsyCocBizField(field);

		String parentNodeID = node == null ? "" : node.getId();
		String propName = cocField.getPropName();

		if (bizEngine.isSystemFK(field)) {

			// 获取该字段引用的外键系统
			IBizSystem fkSystem = field.getRefrenceSystem();

			// 查询外键数据
			IOrm orm = Demsy.orm();
			Class fkSystemType = bizEngine.getType(fkSystem);
			// if (orm.count(fkSystemType) > 50) {
			// return false;
			// }
			List fkSystemRecords = orm.query(fkSystemType);

			// 数据自身树
			String selfTreeProp = null;
			IBizField selfTreeFld = bizEngine.getFieldOfSelfTree(fkSystem);
			if (selfTreeFld != null) {
				selfTreeProp = bizEngine.getPropName(selfTreeFld);
			}

			for (Object record : fkSystemRecords) {
				// 计算上级节点ID
				if (!StringUtil.isNil(selfTreeProp)) {
					Object parentObj = Lang.getValue(record, selfTreeProp);
					if (parentObj != null)
						parentNodeID = parentObj.toString();
				}

				// 计算节点ID
				String nodeID = null;
				if (ClassUtil.hasField(fkSystemType, "id")) {
					nodeID = "" + Lang.getValue(record, "id");
				} else {
					nodeID = "" + record.hashCode();
				}

				// 计算节点名称
				String nodeName = record.toString();

				// 添加节点
				Node childNode = tree.addNode(parentNodeID, propName + ".id:" + nodeID).setName(nodeName);

				// 计算节点顺序
				if (ClassUtil.hasField(fkSystemType, "orderby")) {
					Integer seq = Lang.getValue(record, "orderby");
					if (seq != null)
						childNode.setSequence(seq);
				}
			}
		} else {
			KeyValue[] options = cocField.getDicOptions();
			if (options == null || options.length == 0 || options.length > 200) {
				return false;
			}

			for (KeyValue option : options) {
				tree.addNode(parentNodeID, propName + ":" + option.getValue()).setName(option.getKey());
			}
		}

		return true;
	}

	public SFTSystem getEntity() {
		return entity;
	}

	private Map<String, String> getFieldsModeMap(String opMode, Object data) {
		List<CocBizField> fields = getBizFields();
		Map<String, String> fieldMode = new HashMap();
		for (CocBizField f : fields) {
			DemsyCocBizField field = (DemsyCocBizField) f;

			String mode = field.getMode(opMode);

			// String cascadeMode = this.getCascadeMode(field, data)[1];
			// if (getModeValue(mode) < getModeValue(cascadeMode)) {
			// mode = cascadeMode;
			// }

			fieldMode.put(field.getPropName(), mode);
		}

		return fieldMode;
	}

	public void validate(String opMode, Object data) throws CocException {
		Map<String, String> fieldsMode = this.getFieldsModeMap(opMode, data);

		Iterator<String> keys = fieldsMode.keySet().iterator();
		List<String> requiredFields = new LinkedList();
		while (keys.hasNext()) {
			String field = keys.next();
			String mode = fieldsMode.get(field);
			if (mode != null && mode.indexOf("M") > -1) {
				Object v = Lang.getValue(data, field);
				if (v == null) {
					requiredFields.add(field);
				} else if (v instanceof String) {
					if (StringUtil.isNil((String) v))
						requiredFields.add(field);
				} else if (Obj.isEntity(v)) {
					if (Obj.isEmpty(null, v))
						requiredFields.add(field);
				}
			}
		}

		if (requiredFields.size() > 0) {
			Map<String, CocBizField> fields = getBizFieldsMapByPropName();
			StringBuffer sb = new StringBuffer();
			for (String prop : requiredFields) {
				sb.append(",").append(fields.get(prop).getName());
			}
			throw new CocException("必填字段：[%s]", sb.toString().substring(1));
		}
	}

	public void set(String key, String value) {
		this.extProps.put(key, value);
	}
}
