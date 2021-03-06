package com.jiongsoft.ynby.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.util.UrlAPI;
import com.jiongsoft.ynby.plugins.VisitActivityPlugins;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

/**
 * “走进云南白药”参观活动
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "活动设置", code = "VisitActivity", catalog = "_ynby_visit", pathPrefix = UrlAPI.URL_NS, orderby = 2
//
, sortExpr = "grid:name desc, tree:name desc"
// 操作按钮
, actions = {
//
		@CocOperation(name = "添加年计划", typeCode = 204, mode = "cy", plugin = VisitActivityPlugins.SaveYearActivity.class)//
		, @CocOperation(name = "添加", typeCode = 101, mode = "c", plugin = VisitActivityPlugins.SaveActivity.class)//
		, @CocOperation(name = "修改", typeCode = 102, mode = "e", plugin = VisitActivityPlugins.SaveActivity.class) //
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
// 业务字段
, fields = { @CocField(name = "活动标题", mode = "*:N v:S", property = "name", gridOrder = 1) //
		, @CocField(name = "活动时间", mode = "*:N v:S c:M e:M", property = "planDate", pattern = "yyyy-MM-dd HH:mm", gridOrder = 4)//
		, @CocField(name = "活动人数", mode = "*:N v:S c:M e:M", property = "planPersonNumber", gridOrder = 2)//
		, @CocField(name = "活动地点", mode = "*:N v:S c:M e:M", property = "address")//
		, @CocField(name = "报名起始时间", mode = "*:N v:S c:E e:E", property = "expiredFrom", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 5)//
		, @CocField(name = "报名结束时间", mode = "*:N v:S c:M e:M", property = "expiredTo", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 6)//
		, @CocField(name = "联系人姓名", mode = "*:N v:S c:E e:E", property = "contactPerson") //
		, @CocField(name = "联系人电话", mode = "*:N v:S c:E e:E", property = "contactTel") //
		, @CocField(name = "报名人数", mode = "*:N v:S", property = "registerPersonNumber", gridOrder = 3)//
		, @CocField(name = "参加人数", mode = "*:N v:S c:N e:E", property = "attendPersonNumber")//
		, @CocField(name = "活动描述", mode = "*:N v:S c:E e:E", property = "desc") //
}// end: fields
) // end: CocGroup
}// end: groups
)
public class VisitActivity extends SFTBizComponent {

	@ManyToOne
	VisitActivityAddress address;

	Date expiredFrom;

	Date expiredTo;

	Date planDate;

	Integer planPersonNumber;

	@Column(length = 32)
	String contactPerson;

	@Column(length = 32)
	String contactTel;

	int registerPersonNumber = 0;

	Integer attendPersonNumber;

	public boolean isExpired() {
		Date now = new Date();
		if (expiredTo != null && expiredFrom != null) {
			return expiredTo.getTime() < now.getTime() || expiredFrom.getTime() > now.getTime();
		}

		if (expiredTo != null)
			return expiredTo.getTime() < now.getTime();

		if (expiredFrom != null)
			return expiredFrom.getTime() > now.getTime();

		return false;
	}

	public Date getExpiredFrom() {
		return expiredFrom;
	}

	public void setExpiredFrom(Date expiredFrom) {
		this.expiredFrom = expiredFrom;
	}

	public Date getExpiredTo() {
		return expiredTo;
	}

	public void setExpiredTo(Date expiredTo) {
		this.expiredTo = expiredTo;
	}

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

	public int getRegisterPersonNumber() {
		return registerPersonNumber;
	}

	public void setRegisterPersonNumber(int registerPersonNumber) {
		this.registerPersonNumber = registerPersonNumber;
	}

	public Integer getAttendPersonNumber() {
		return attendPersonNumber;
	}

	public void setAttendPersonNumber(Integer realPersonNumber) {
		this.attendPersonNumber = realPersonNumber;
	}

	public VisitActivityAddress getAddress() {
		return address;
	}

	public void setAddress(VisitActivityAddress address) {
		this.address = address;
	}
}
