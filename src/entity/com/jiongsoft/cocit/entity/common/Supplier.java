package com.jiongsoft.cocit.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.entity.impl.NameEntity;
import com.jiongsoft.cocit.util.UrlAPI;

@Entity
@CocTable(name = "供货商信息维护", code = "common_supplier", catalog = "_soft_base", pathPrefix = UrlAPI.URL_NS//
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
		@CocField(name = "名称", mode = "*:N v:S c:M e:M", property = "name", gridOrder = 2)//
		, @CocField(name = "编号", mode = "*:N v:S c:E e:E", property = "code", gridOrder = 3)//
		, @CocField(name = "单位地址", mode = "*:N v:S c:M e:M", property = "address", gridOrder = 4)//
		, @CocField(name = "联系人姓名", mode = "*:N v:S c:M e:M", property = "contactPerson", gridOrder = 5)//
		, @CocField(name = "联系人电话", mode = "*:N v:S c:M e:M", property = "tel", gridOrder = 6)//
		, @CocField(name = "联系人邮箱", mode = "*:N v:S c:E e:E", property = "email", gridOrder = 7)//
		, @CocField(name = "联系人QQ", mode = "*:N v:S c:E e:E", property = "qq", gridOrder = 8)//
		, @CocField(name = "描述", mode = "*:N v:S c:E e:E", property = "desc") //
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
public class Supplier extends NameEntity {

	@Column(length = 256)
	String address;

	@Column(length = 64)
	String contactPerson;

	@Column(length = 128)
	String email;

	@Column(length = 32)
	String qq;

	@Column(length = 32)
	String tel;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
