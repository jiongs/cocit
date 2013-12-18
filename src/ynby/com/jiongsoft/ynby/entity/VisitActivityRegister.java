package com.jiongsoft.ynby.entity;

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
 * “走进云南白药”参观活动之“活动报名”
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "活动报名管理", code = "VisitActivityRegister", catalog = "_ynby_visit", pathPrefix = UrlAPI.URL_NS, orderby = 3
// 操作按钮
, actions = {
//
		@CocOperation(name = "导出EXCEL", typeCode = 107, mode = "xls")//
		, @CocOperation(name = "添加", typeCode = 101, mode = "c1", plugin = VisitActivityPlugins.AdminSaveRegister.class)//
		, @CocOperation(name = "修改", typeCode = 102, mode = "e2", plugin = VisitActivityPlugins.AdminSaveRegister.class)//
		, @CocOperation(name = "取消", typeCode = 102, mode = "e1", plugin = VisitActivityPlugins.CancelRegister.class)//
		, @CocOperation(name = "删除", typeCode = 299, mode = "d", plugin = VisitActivityPlugins.DeleteRegister.class) //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
		, @CocOperation(name = "前台报名", typeCode = 101, mode = "c", plugin = VisitActivityPlugins.SaveRegister.class) //
}// end: actions
// 业务分组
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
// 业务字段
, fields = {
//
		@CocField(name = "参观时间", mode = "*:N v:S e1:S c:M e:M xls:S c1:M e2:M", property = "activity", gridOrder = 10)//
		, @CocField(name = "姓名", mode = "*:N v:S e1:S c:M e:M xls:S c1:M e2:M", property = "name", gridOrder = 1) //
		, @CocField(name = "性别", mode = "*:N v:S e1:S c:E e:E xls:S c1:M e2:M", property = "sex", options = "0:男,1:女", disabledNavi = true, gridOrder = 2) //
		// , @CocField(name = "年龄", mode = "*:N v:S e1:S c:E e:E xls:S", property = "age", gridOrder = 3) //
		, @CocField(name = "身份证号", mode = "*:N v:S e1:S c:M e:M xls:S c1:M e2:M", property = "code", gridOrder = 4) //
		, @CocField(name = "手机号码", mode = "*:N v:S e1:S c:M e:M xls:S c1:M e2:M", property = "tel", gridOrder = 5) //
		, @CocField(name = "手机验证码", mode = "*:N v:S e1:S c:M e:R", property = "telVerifyCode") //
		, @CocField(name = "团队ID", mode = "*:N v:S e1:S c:E e:E", property = "teamID", gridOrder = 6) //
		// , @CocField(name = "成员关系", mode = "*:N v:S e1:S c:E e:E xls:S", property = "teamMemberRole") //
		, @CocField(name = "参观人数", mode = "*:N v:S e1:S c:E e:E xls:S c1:E e2:E", property = "personNumber", gridOrder = 7) //
		, @CocField(name = "工作单位", mode = "*:N v:S e1:S c:E e:E xls:S c1:E e2:E", property = "unit", gridOrder = 8) //
		, @CocField(name = "邀请函验证码", mode = "*:N v:S e1:S", property = "verificationCode") //
		, @CocField(name = "自驾车牌号", mode = "*:N v:S e1:S c:E e:E xls:S c1:E e2:E", property = "carCode") //
		, @CocField(name = "状态", mode = "*:N v:S e1:S c:E e:E xls:S", property = "status", gridOrder = 9, options = "0:已报名,1:已参与,2:没有参与,9:取消报名") //
		, @CocField(name = "QQ号码", mode = "*:N v:S e1:S c:E e:E c1:E e2:E", property = "qq") //
		, @CocField(name = "邮箱地址", mode = "*:N v:S e1:S c:E e:E c1:E e2:E", property = "email") //
		, @CocField(name = "备注", mode = "*:N v:S e1:S c:E e:E c1:E e2:E", property = "desc") //
		, @CocField(name = "登录帐号", mode = "*:N v:S", property = "createdBy") //
		, @CocField(name = "团队报名方式", mode = "*:N v:S", property = "teamRegType") //
		, @CocField(name = "团队名单", mode = "*:N v:S", property = "teamXlsFile") //
		, @CocField(name = "报名时间", mode = "*:N v:S", property = "created", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 10) //
		, @CocField(name = "IP地址", mode = "*:N v:S", property = "createdIP") //
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

	Integer personNumber;

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

	/**
	 * 1: 上传EXCEL名单
	 * <p>
	 * 2: 在线填写名单
	 */
	byte teamRegType;

	/**
	 * 1: 上传EXCEL名单
	 */
	@Column(length = 128)
	String teamXlsFile;

	/**
	 * 2： 在线填写的团队名单：JSON 数组
	 */
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

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
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

	public String getTeamXlsFile() {
		return teamXlsFile;
	}

	public void setTeamXlsFile(String teamXlsFile) {
		this.teamXlsFile = teamXlsFile;
	}

	public byte getTeamRegType() {
		return teamRegType;
	}

	public void setTeamRegType(byte teamRegType) {
		this.teamRegType = teamRegType;
	}

}
