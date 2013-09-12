package com.jiongsoft.ynby.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.util.ActionUtil;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

/**
 * “走进云南白药”参观活动之“活动报名”
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "活动报名管理", code = "VisitActivityRegister", catalog = "_ynby_visit", pathPrefix = ActionUtil.ACTION_PATH_PREFIX, orderby = 3//
// 操作按钮
, actions = { @CocOperation(name = "报名", typeCode = 101, mode = "c", pluginName = "com.jiongsoft.ynby.plugins.VisitActivityPlugins$SaveRegister")//
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
// 业务字段
, fields = { @CocField(name = "真实姓名", mode = "*:N v:S c:M e:M", property = "name", gridOrder = 1) //
		, @CocField(name = "性别", mode = "*:N v:S c:E e:E", property = "sex", options = "0:男,1:女", gridOrder = 2) //
		, @CocField(name = "手机号码", mode = "*:N v:S c:M e:M", property = "tel", gridOrder = 3) //
		, @CocField(name = "手机验证码", mode = "*:N v:S c:M e:R", property = "verificationCode") //
		, @CocField(name = "身份证号码", mode = "*:N v:S c:M e:M", property = "code", gridOrder = 4) //
		, @CocField(name = "工作单位", mode = "*:N v:S c:E e:E", property = "unit", gridOrder = 5) //
		, @CocField(name = "参观时间", mode = "*:N v:S c:M e:M", property = "activity", gridOrder = 6)//
		, @CocField(name = "参观人数", mode = "*:N v:S c:E e:E", property = "personNumber", gridOrder = 7) //
		, @CocField(name = "自驾车牌号", mode = "*:N v:S c:E e:E", property = "carCode", gridOrder = 8) //
		, @CocField(name = "QQ号码", mode = "*:N v:S c:E e:E", property = "qq", gridOrder = 9) //
		, @CocField(name = "邮箱地址", mode = "*:N v:S c:E e:E", property = "email", gridOrder = 10) //
		, @CocField(name = "登录帐号", mode = "*:N v:S", property = "createdBy", gridOrder = 11) //
		, @CocField(name = "报名时间", mode = "*:N v:S", property = "created", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 12) //
		, @CocField(name = "IP地址", mode = "*:N v:S", property = "personNumber", gridOrder = 13) //
}// end: fields
) // end: CocGroup
}// end: groups
)
public class VisitActivityRegister extends SFTBizComponent {

	@ManyToOne
	VisitActivity activity;

	byte sex = 0;

	@Column(length = 32)
	String tel;

	@Column(length = 32)
	String verificationCode;

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

	public VisitActivity getActivity() {
		return activity;
	}

	public void setActivity(VisitActivity plan) {
		this.activity = plan;
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

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String telValidCode) {
		this.verificationCode = telValidCode;
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
