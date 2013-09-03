package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizField;
import com.jiongsoft.cocit.cocsoft.CocBizGroup;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.utils.ClassUtil;
import com.jiongsoft.cocit.utils.KeyValue;
import com.jiongsoft.cocit.utils.Lang;
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
import com.kmetop.demsy.orm.IOrm;

class DemsyCocBizTable implements CocBizTable {
	private SFTSystem entity;
	private List<SystemDataGroup> dataGroups;
	private List<AbstractSystemData> dataFields;
	private List<AbstractSystemData> dataFieldsForNaviTree;
	private List<AbstractSystemData> dataFieldsForGrid;
	private List<BizAction> dataOperations;

	DemsyCocBizTable(SFTSystem e) {
		this.entity = e;
	}

	@Override
	public boolean is(String propName) {
		Object obj = entity.get(propName);
		if (obj == null)
			return false;

		try {
			return Boolean.valueOf(obj.toString());
		} catch (Throwable e) {
			return false;
		}
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
	public <T> T get(String propName) {
		return (T) entity.get(propName);
	}

	@Override
	public List<CocBizGroup> getBizGroups() {
		if (this.dataGroups == null)
			return null;

		List<CocBizGroup> ret = new ArrayList();
		for (SystemDataGroup s : this.dataGroups) {
			ret.add(new DemsyCocBizGroup(s));
		}

		return ret;
	}

	@Override
	public List<CocBizField> getBizFields() {
		if (this.dataFields == null)
			return null;

		List<CocBizField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFields) {
			ret.add(new DemsyCocBizField(s));
		}

		return ret;
	}

	@Override
	public List<CocBizOperation> getBizOperations() {
		if (this.dataOperations == null)
			return null;

		List<CocBizOperation> ret = new ArrayList();
		for (BizAction s : this.dataOperations) {
			ret.add(new DemsyCocBizOperation(s));
		}

		return ret;
	}

	@Override
	public List<CocBizField> getBizFieldsForNaviTree() {
		if (this.dataFieldsForNaviTree == null)
			return null;

		List<CocBizField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFieldsForNaviTree) {
			ret.add(new DemsyCocBizField(s));
		}

		return ret;
	}

	@Override
	public List<CocBizField> getBizFieldsForGrid() {
		if (this.dataFieldsForGrid == null)
			return null;

		List<CocBizField> ret = new ArrayList();
		for (AbstractSystemData s : this.dataFieldsForGrid) {
			ret.add(new DemsyCocBizField(s));
		}

		return ret;
	}

	public void setDataGroups(List<SystemDataGroup> dataGroups) {
		this.dataGroups = dataGroups;
	}

	public void setDataFields(List<AbstractSystemData> dataFields) {
		this.dataFields = dataFields;
	}

	public void setDataFieldsForNaviTree(List<AbstractSystemData> dataFieldsForNaviTree) {
		this.dataFieldsForNaviTree = dataFieldsForNaviTree;
	}

	public void setDataOperations(List<BizAction> dataOperations) {
		this.dataOperations = dataOperations;
	}

	public void setDataFieldsForGrid(List<AbstractSystemData> dataFieldsForGrid) {
		this.dataFieldsForGrid = dataFieldsForGrid;
	}

	@Override
	public Tree getNaviTree() {

		Tree root = Tree.make();

		for (AbstractSystemData fld : this.dataFieldsForNaviTree) {

			Node node = root.addNode(null, fld.getPropName()).setName("按 " + fld.getName());

			this.makeNodes(root, node, fld);
		}

		// 如果导航树节点总数没有超过边框则全部展开
		root.optimizeStatus();

		root.sort();

		return root;
	}

	private void makeNodes(Tree tree, Node node, AbstractSystemData field) {
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
			if (orm.count(fkSystemType) > 200) {
				return;
			}
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
				Node childNode = tree.addNode(parentNodeID, propName + ":" + nodeID).setName(nodeName);

				// 计算节点顺序
				if (ClassUtil.hasField(fkSystemType, "orderby")) {
					Integer seq = Lang.getValue(record, "orderby");
					if (seq != null)
						childNode.setSequence(seq);
				}
			}
		} else {
			KeyValue[] options = cocField.getOptions();
			if (options == null || options.length == 0 || options.length > 200) {
				return;
			}

			for (KeyValue option : options) {
				tree.addNode(parentNodeID, propName + ":" + option.getValue()).setName(option.getKey());
			}
		}
	}
}
