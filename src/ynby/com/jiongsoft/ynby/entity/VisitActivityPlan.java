package com.jiongsoft.ynby.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.utils.ActionUtil;
import com.kmetop.demsy.comlib.biz.ann.BzAct;
import com.kmetop.demsy.comlib.biz.ann.BzFld;
import com.kmetop.demsy.comlib.biz.ann.BzGrp;
import com.kmetop.demsy.comlib.biz.ann.BzSys;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

@Entity
@BzSys(name = "参观计划设置", code = "VisitActivityPlan", catalog = "_ynby_visit", actionPathPrefix = ActionUtil.ACTION_PATH_PREFIX, orderby = 2//
// 操作按钮
, actions = { @BzAct(name = "添加", typeCode = 101, mode = "c")//
		, @BzAct(name = "修改", typeCode = 102, mode = "e") //
		, @BzAct(name = "删除", typeCode = 299, mode = "d") //
		, @BzAct(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@BzGrp(name = "基本信息", code = "basic"//
// 业务字段
, fields = { // @BzFld(name = "活动名称", mode = "*:N v:S c:M e:M", property = "name") //
@BzFld(name = "计划参观日期", mode = "*:N v:S c:M e:M", property = "planDate", pattern = "yyyy-MM-dd")//
		, @BzFld(name = "计划参观人数", mode = "*:N v:S c:M e:M", property = "planPersonNumber")//
		, @BzFld(name = "计划参观地点", mode = "*:N v:S c:M e:M", property = "address")//
		// , @BzFld(name = "报名起始日期", mode = "*:N v:S c:E e:E", property = "expiredFrom", pattern = "yyyy-MM-dd HH:mm:ss")//
		// , @BzFld(name = "报名截止日期", mode = "*:N v:S c:E e:E", property = "expiredTo", pattern = "yyyy-MM-dd HH:mm:ss")//
		, @BzFld(name = "联系人姓名", mode = "*:N v:S c:E e:E", property = "contactPerson") //
		, @BzFld(name = "联系人电话", mode = "*:N v:S c:E e:E", property = "contactTel") //
		, @BzFld(name = "报名人数", mode = "*:N v:S", property = "registerPersonNumber")//
		, @BzFld(name = "参观人数", mode = "*:N v:S c:N e:E", property = "realPersonNumber")//
		, @BzFld(name = "活动描述", mode = "*:N v:S c:E e:E", property = "desc") //
}// end: fields
) // end: BzGrp
}// end: groups
)
public class VisitActivityPlan extends SFTBizComponent {

	@ManyToOne
	VisitActivityAddress address;

	// Date expiredFrom;
	//
	// Date expiredTo;

	Date planDate;

	Integer planPersonNumber;

	@Column(length = 32)
	String contactPerson;

	@Column(length = 32)
	String contactTel;

	Integer registerPersonNumber;

	Integer realPersonNumber;

	// public Date getExpiredFrom() {
	// return expiredFrom;
	// }
	//
	// public void setExpiredFrom(Date expiredFrom) {
	// this.expiredFrom = expiredFrom;
	// }
	//
	// public Date getExpiredTo() {
	// return expiredTo;
	// }
	//
	// public void setExpiredTo(Date expiredTo) {
	// this.expiredTo = expiredTo;
	// }

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Integer getPlanPersonNumber() {
		return planPersonNumber;
	}

	public void setPlanPersonNumber(Integer planPersonNumber) {
		this.planPersonNumber = planPersonNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public Integer getRegisterPersonNumber() {
		return registerPersonNumber;
	}

	public void setRegisterPersonNumber(Integer registerPersonNumber) {
		this.registerPersonNumber = registerPersonNumber;
	}

	public Integer getRealPersonNumber() {
		return realPersonNumber;
	}

	public void setRealPersonNumber(Integer realPersonNumber) {
		this.realPersonNumber = realPersonNumber;
	}

	public VisitActivityAddress getAddress() {
		return address;
	}

	public void setAddress(VisitActivityAddress address) {
		this.address = address;
	}
}
