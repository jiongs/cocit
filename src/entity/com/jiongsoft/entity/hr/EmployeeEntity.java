package com.jiongsoft.entity.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.entity.impl.NameEntity;
import com.jiongsoft.cocit.util.UrlAPI;

@Entity
@CocTable(name = "员工管理", code = "common_employee", catalog = "_soft_base", pathPrefix = UrlAPI.URL_NS//
/*
 * 操作按钮
 */
, actions = {
//
		@CocOperation(name = "添加", typeCode = 101, mode = "c")//
		, @CocOperation(name = "修改", typeCode = 102, mode = "e") //
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
		, @CocOperation(name = "导出到XLS", typeCode = 107, mode = "export")//
		, @CocOperation(name = "从XLS导入", typeCode = 108, mode = "import") //
}// end: actions
/*
 * 字段分组
 */
, groups = { //
@CocGroup(name = "基本信息", code = "basic_info"//
, fields = {
//
		@CocField(name = "所属部门", mode = "*:N v:S c:M e:M", property = "org", gridOrder = 1)//
		, @CocField(name = "员工姓名", mode = "*:N v:S c:M e:M", property = "name", gridOrder = 2)//
		, @CocField(name = "员工工号", mode = "*:N v:S c:E e:E", property = "code", gridOrder = 3)//
		, @CocField(name = "联系电话", mode = "*:N v:S c:E e:E", property = "tel", gridOrder = 4)//
		, @CocField(name = "QQ号码", mode = "*:N v:S c:E e:E", property = "qq", gridOrder = 5)//
		, @CocField(name = "Email地址", mode = "*:N v:S c:E e:E", property = "email", gridOrder = 6)//
		, @CocField(name = "员工简介", mode = "*:N v:S c:E e:E", property = "desc") //
}), @CocGroup(name = "基本信息", code = "operation_log"//
, fields = {
//
		@CocField(name = "录入时间", mode = "*:N v:S", property = "created", pattern = "yyyy-MM-dd HH-mm-ss") //
		, @CocField(name = "录入帐号", mode = "*:N v:S", property = "createdBy") //
		, @CocField(name = "修改时间", mode = "*:N v:S", property = "updated", pattern = "yyyy-MM-dd HH-mm-ss") //
		, @CocField(name = "修改帐号", mode = "*:N v:S", property = "updatedBy") //
}) // end: CocGroup
}// end: groups
)
public class EmployeeEntity extends NameEntity {

	// 办公地点
	@ManyToOne
	AddressEntity officeAddress;

	@ManyToOne
	DepartmentEntity department;

	@Column(length = 128)
	String email;

	@Column(length = 32)
	String qq;

	@Column(length = 32)
	String tel;

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity parent) {
		this.department = parent;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
