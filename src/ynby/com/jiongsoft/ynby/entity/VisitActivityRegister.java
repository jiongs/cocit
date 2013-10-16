package com.jiongsoft.ynby.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.util.ActionUtil;
import com.jiongsoft.ynby.plugins.VisitActivityPlugins;
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
, actions = {
//
		@CocOperation(name = "导出EXCEL", typeCode = 107, mode = "xls")//
		, @CocOperation(name = "报名", typeCode = 101, mode = "c", plugin = VisitActivityPlugins.SaveRegister.class)//
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
// 业务字段
, fields = {
//
		@CocField(name = "参观时间", mode = "*:N v:S c:M e:M xls:S", property = "activity", gridOrder = 5)//
		, @CocField(name = "真实姓名", mode = "*:N v:S c:M e:M xls:S", property = "name", gridOrder = 1) //
		, @CocField(name = "性别", mode = "*:N v:S c:E e:E xls:S", property = "sex", options = "0:男,1:女", disabledNavi = true, gridOrder = 2) //
		, @CocField(name = "手机号码", mode = "*:N v:S c:M e:M xls:S", property = "tel", gridOrder = 3) //
		, @CocField(name = "手机验证码", mode = "*:N v:S c:M e:R", property = "telVerifyCode") //
		, @CocField(name = "身份证号码", mode = "*:N v:S c:M e:M xls:S", property = "code", gridOrder = 4) //
		, @CocField(name = "工作单位", mode = "*:N v:S c:E e:E xls:S", property = "unit", gridOrder = 9) //
		, @CocField(name = "邀请函验证码", mode = "*:N v:S xls:S", property = "verificationCode", gridOrder = 13) //
		, @CocField(name = "参观人数", mode = "*:N v:S c:E e:E xls:S", property = "personNumber", gridOrder = 6) //
		, @CocField(name = "自驾车牌号", mode = "*:N v:S c:E e:E xls:S", property = "carCode", gridOrder = 7) //
		, @CocField(name = "QQ号码", mode = "*:N v:S c:E e:E", property = "qq", gridOrder = 10) //
		, @CocField(name = "邮箱地址", mode = "*:N v:S c:E e:E", property = "email", gridOrder = 11) //
		, @CocField(name = "团队ID", mode = "*:N v:S c:E e:E", property = "teamID", gridOrder = 12) //
		, @CocField(name = "成员关系", mode = "*:N v:S c:E e:E", property = "teamMemberRole", gridOrder = 13) //
		, @CocField(name = "年龄", mode = "*:N v:S c:E e:E", property = "age", gridOrder = 14) //
		, @CocField(name = "状态", mode = "*:N v:S c:E e:E", property = "status", gridOrder = 19, options = "0:已报名 1:已参与 2:未参与 9:已取消") //
		, @CocField(name = "登录帐号", mode = "*:N v:S", property = "createdBy", gridOrder = 20) //
		, @CocField(name = "报名时间", mode = "*:N v:S", property = "created", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 8) //
		, @CocField(name = "IP地址", mode = "*:N v:S", property = "createdIP", gridOrder = 21) //
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
	String telVerifyCode;

	/**
	 * 当前时间LONG值转换成INT值，然后在to HEX成大写字母，
	 */
	@Column(length = 127)
	String verificationCode;

	@Column(length = 32)
	String qq;

	@Column(length = 32)
	String email;

	@Column(length = 255)
	String unit;

	@Column(length = 255)
	String carCode;

	Integer age;

	int personNumber = 1;

	/**
	 * 状态：0:已报名 1:已参与 2:未参与 9:已取消
	 */
	byte status = 0;

	/*
	 * 自动赋值的字段
	 */
	@Column(length = 32)
	String createdIP;

	/*
	 * 团队报名信息
	 */
	/**
	 * 团队ID：报名者手机号
	 */
	String teamID;

	/**
	 * 团队成员角色（即与报名者的关系）：描述该成员是报名者的...(配偶|父母|子女|亲戚|朋友|同事|其他)
	 * <p>
	 * 该字段为空或“本人”说明该成员为报名者本人
	 */
	@Column(length = 32)
	String teamMemberRole;

	// 团队成员 JSON 数组
	@Column(columnDefinition = "text")
	String teamMembers;

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

	public String getTelVerifyCode() {
		return telVerifyCode;
	}

	public void setTelVerifyCode(String telValidCode) {
		this.telVerifyCode = telValidCode;
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

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(String teamMembers) {
		this.teamMembers = teamMembers;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getTeamMemberRole() {
		return teamMemberRole;
	}

	public void setTeamMemberRole(String teamMemberRole) {
		this.teamMemberRole = teamMemberRole;
	}

}
