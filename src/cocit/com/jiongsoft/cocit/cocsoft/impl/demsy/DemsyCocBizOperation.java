package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.utils.StringUtil;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.impl.base.biz.BizAction;
import com.kmetop.demsy.lang.Str;

class DemsyCocBizOperation implements CocBizOperation {

	private BizAction entity;

	// lazy load
	private List<BizAction> childrenBizActions;

	DemsyCocBizOperation(BizAction e) {
		this.entity = e;
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
	public <T> T get(String propName, T defaultReturn) {
		String value = entity.get(propName);

		if (value == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) value;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) StringUtil.cast(value, valueType);
		} catch (Throwable e) {
		}

		return defaultReturn;
	}

	@Override
	public List<CocBizOperation> getChildrenBizOperations() {
		if (childrenBizActions == null)
			return null;

		List<CocBizOperation> ret = new ArrayList();

		for (BizAction action : childrenBizActions) {
			ret.add(new DemsyCocBizOperation(action));
		}

		return ret;
	}

	public void setChildrenBizActions(List<BizAction> children) {
		this.childrenBizActions = children;
	}

	@Override
	public String getLogo() {
		String logoPath = null;
		Upload logo = entity.getLogo();
		if (logo == null || Str.isEmpty(logo.toString()))
			logoPath = Demsy.appconfig.get("imagepath.actionlib") + "/" + entity.getMode() + ".gif";
		else
			logoPath = logo.toString();

		return logoPath;
	}

}
