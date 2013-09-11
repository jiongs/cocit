package com.kmetop.demsy.comlib.impl.base.security;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT_N;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_ADMIN;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_ADMIN_WEBUSER;
import static com.kmetop.demsy.comlib.LibConst.ORDER_SYSADMIN_WEBUSER;

import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.entity.base.BaseUser;

@Entity
@CocTable(name = "网站登录帐号", code = BIZSYS_ADMIN_WEBUSER, catalog = BIZCATA_ADMIN, orderby = ORDER_SYSADMIN_WEBUSER, buildin = false//
, actions = { @CocOperation(name = "审核", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu1")//
		, @CocOperation(name = "员工注册", typeCode = TYPE_BZFORM_NEW, mode = "c1", pluginName = "com.kmetop.demsy.plugins.security.SaveStaffUser", info = "注册成功！审核通过后方可登录。", error = "注册失败！请检查错误信息。")//
		, @CocOperation(name = "编辑帐号", typeCode = TYPE_BZFORM_EDIT, mode = "e1", info = "修改个人资料成功！", disabled = true)//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "员工信息", code = "staffinfo"//
, fields = { @CocField(property = "staffName", gridOrder = 1) //
		, @CocField(property = "staffTelcode", gridOrder = 2) //
		, @CocField(property = "staffDepartment", gridOrder = 3) //
		, @CocField(property = "staffCode", gridOrder = 4) //
}), @CocGroup(name = "帐号信息", code = "logininfo"//
, fields = { @CocField(name = "登录帐号", property = "code", mode = "c:M c1:M e1:R e:R v:S *:N", gridOrder = 5, privacy = true, desc = "只能有数字、字母、下划线组成") //
		, @CocField(name = "用户昵称", property = "name", mode = "c:M c1:M e1:M e:M v:S *:N", gridOrder = 6, privacy = true)//
		, @CocField(name = "登录密码", property = "rawPassword", password = true, gridField = false, mode = "*:N c:M e:M c1:M e1:E", desc = "密码至少6个字符") //
		, @CocField(name = "验证密码", property = "rawPassword2", password = true, gridField = false, mode = "*:N c:M e:M c1:M e1:E", desc = "必须与登录密码相同") //
		, @CocField(name = "有效期自", property = "expiredFrom", mode = "c1:N e1:N", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(name = "有效期至", property = "expiredTo", mode = "c1:N e1:N", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(name = "密码问题", property = "pwdQuestion", gridField = false, mode = "c1:N e1:N") //
		, @CocField(name = "密码答案", property = "pwdAnswer", gridField = false, mode = "c1:N e1:N") //
		, @CocField(name = "用户头像", property = "image", gridField = false, uploadType = "*.jpg;*.gif;*.png", mode = "c1:E e1:E") //
		// , @CocField(name = "用户徽标", property = "logo", gridField = false,
		// uploadType = "*.jpg;*.gif;*.png", mode = "c1:N e1:N") //
		// , @CocField(name = "权限有效期自", property = "permissionExpiredFrom") //
		// , @CocField(name = "权限有效期至", property = "permissionExpiredTo") // /
		// , @CocField(name = "最近登录地址", property = "lastedRemoteAddr", mode =
		// "*:P") //
		// , @CocField(name = "最近登录时间", property = "lastedLoginDate", mode =
		// "v:S *:N")
		// //
		, @CocField(name = "个人签名", property = "desc", mode = "c1:E e1:E e:E *:N v:S") //
		// , @CocField(name = "登录次数", property = "loginedCount", mode = "v:S *:N")
		// //
		, @CocField(name = "账户停用", property = "disabled", mode = "bu1:E v:S *:N", gridOrder = 8, options = "0:启用,1:停用") //
		, @CocField(name = "注册时间", property = "created", mode = "v:S *:N", gridOrder = 7, pattern = "yyyy-MM-dd HH:mm") //
		, @CocField(name = "更新时间", property = "updated", mode = "v:S *:N", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(name = "创建帐号", property = "createdBy", mode = "v:S *:N") //
		, @CocField(name = "更新帐号", property = "updatedBy", mode = "v:S *:N") //
}) }// end groups
)
public class WebUser extends BaseUser {
	@CocField(name = "员工姓名", mode = "c1:M e1:R e:E *:N v:S", privacy = true, desc = "企业员工真实姓名，审核时需要")
	private String staffName;

	@CocField(name = "员工电话", mode = "c1:M e1:E e:E *:N v:S", privacy = true, desc = "员工手机号码或办公室电话号码")
	private String staffTelcode;

	@CocField(name = "员工部门", mode = "c1:M e1:E e:E *:N v:S", privacy = true, desc = "员工所在的部门")
	private String staffDepartment;

	@CocField(name = "员工工号", mode = "c1:E e1:E e:E *:N v:S", privacy = true, desc = "企业为员工分配的工号")
	private String staffCode;

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffTelcode() {
		return staffTelcode;
	}

	public void setStaffTelcode(String staffTelcode) {
		this.staffTelcode = staffTelcode;
	}

	public String getStaffDepartment() {
		return staffDepartment;
	}

	public void setStaffDepartment(String staffDepartment) {
		this.staffDepartment = staffDepartment;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
}
