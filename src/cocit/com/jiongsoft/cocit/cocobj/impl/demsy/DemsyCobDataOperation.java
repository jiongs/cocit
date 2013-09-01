package com.jiongsoft.cocit.cocobj.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocobj.CobDataOperation;
import com.kmetop.demsy.comlib.impl.base.biz.BizAction;

class DemsyCobDataOperation implements CobDataOperation {

	private BizAction entity;

	// lazy load
	private List<BizAction> childrenBizActions;

	DemsyCobDataOperation(BizAction e) {
		this.entity = e;
	}

	@Override
	public Long getID() {
		return entity.getId();
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
	public String getOperationCode() {
		return entity.getCode();
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
	public <T> T getExtProp(String propName) {
		return (T) entity.get(propName);
	}

	@Override
	public List<CobDataOperation> getChildrenDataOperations() {
		if (childrenBizActions == null)
			return null;

		List<CobDataOperation> ret = new ArrayList();

		for (BizAction action : childrenBizActions) {
			ret.add(new DemsyCobDataOperation(action));
		}

		return ret;
	}

	public void setChildrenBizActions(List<BizAction> children) {
		this.childrenBizActions = children;
	}

}
