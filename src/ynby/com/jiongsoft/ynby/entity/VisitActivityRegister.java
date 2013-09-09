package com.jiongsoft.ynby.entity;

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
@BzSys(name = "参观报名管理", code = "VisitActivityRegister", catalog = "_ynby_visit", actionPathPrefix = ActionUtil.ACTION_PATH_PREFIX, orderby = 3//
// 操作按钮
, actions = { @BzAct(name = "添加", typeCode = 101, mode = "c")//
		, @BzAct(name = "删除", typeCode = 299, mode = "d") //
		, @BzAct(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@BzGrp(name = "基本信息", code = "basic"//
// 业务字段
, fields = { @BzFld(name = "真实姓名", mode = "*:N v:S c:M e:M", property = "name") //
		, @BzFld(name = "手机号码", mode = "*:N v:S c:M e:M", property = "tel") //
		, @BzFld(name = "身份证号码", mode = "*:N v:S c:M e:M", property = "code") //
		, @BzFld(name = "性别", mode = "*:N v:S c:E e:E", property = "sex", options = "0:男,1:女") //
		, @BzFld(name = "工作单位", mode = "*:N v:S c:E e:E", property = "unit") //
		, @BzFld(name = "自驾车牌号", mode = "*:N v:S c:E e:E", property = "carCode") //
		, @BzFld(name = "参观时间", mode = "*:N v:S c:M e:M", property = "plan")//
		, @BzFld(name = "参观人数", mode = "*:N v:S c:E e:E", property = "personNumber") //
		, @BzFld(name = "报名时间", mode = "*:N v:S", property = "created", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @BzFld(name = "QQ号码", mode = "*:N v:S c:E e:E", property = "qq") //
		, @BzFld(name = "邮箱地址", mode = "*:N v:S c:E e:E", property = "email") //
		, @BzFld(name = "登录帐号", mode = "*:N v:S", property = "createdBy") //
		, @BzFld(name = "IP地址", mode = "*:N v:S", property = "personNumber") //
		, @BzFld(name = "手机验证码", mode = "*:N v:S c:M e:R", property = "telValidCode") //
}// end: fields
) // end: BzGrp
}// end: groups
)
public class VisitActivityRegister extends SFTBizComponent {

	@ManyToOne
	VisitActivityPlan plan;

	byte sex = 0;

	@Column(length = 32)
	String tel;

	@Column(length = 32)
	String telValidCode;

	@Column(length = 32)
	String qq;

	@Column(length = 32)
	String email;

	@Column(length = 255)
	String unit;

	@Column(length = 255)
	String carCode;

	int personNumber = 1;

	@Column(length = 32)
	String createdIP;

	public VisitActivityPlan getPlan() {
		return plan;
	}

	public void setPlan(VisitActivityPlan plan) {
		this.plan = plan;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTelValidCode() {
		return telValidCode;
	}

	public void setTelValidCode(String telValidCode) {
		this.telValidCode = telValidCode;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

}
