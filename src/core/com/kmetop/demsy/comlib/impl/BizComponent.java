package com.kmetop.demsy.comlib.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.nutz.lang.Strings;

import com.kmetop.demsy.comlib.entity.IBizComponent;
import com.kmetop.demsy.lang.Cls;

public abstract class BizComponent extends DynaEntity implements IBizComponent {

	@Column(name = "_name", length = 255)
	protected String name;

	@Column(name = "_code", length = 64)
	protected String code;

	@Column(name = "_desc", length = 2000)
	protected String desc;

	protected Boolean disabled;

	protected Boolean buildin;

	protected Integer orderby;

	@Column(name = "_created", updatable = false)
	protected Date created;

	@Column(name = "_updated")
	protected Date updated;

	@Column(name = "_created_by", length = 32, updatable = false)
	protected String createdBy;

	@Column(name = "_updated_by", length = 32)
	protected String updatedBy;

	@Transient
	protected byte statusForJsonData;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public byte getStatusForJsonData() {
		return statusForJsonData;
	}

	public void setStatusForJsonData(byte statusForAjaxAction) {
		this.statusForJsonData = statusForAjaxAction;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isDisabled() {
		return disabled != null && disabled;
	}

	public boolean isBuildin() {
		return buildin != null && buildin;
	}

	public Boolean getDisabled() {
		return isDisabled();
	}

	public Boolean getBuildin() {
		return buildin;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public void setBuildin(Boolean buildin) {
		this.buildin = buildin;
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return 37 * 17 + id.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;

		if (!Cls.getType(getClass()).equals(Cls.getType(that.getClass()))) {
			return false;
		}

		BizComponent thatEntity = (BizComponent) that;
		if (id == null || id == 0 || thatEntity.id == null || thatEntity.id == 0) {
			return this == that;
		}

		return thatEntity.id.equals(id);
	}

	@Override
	public String toString() {
		if (Strings.isEmpty(name))
			return super.toString();

		return name;
	}
}
