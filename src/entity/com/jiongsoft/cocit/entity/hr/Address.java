package com.jiongsoft.cocit.entity.hr;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.entity.impl.NameEntity;
import com.jiongsoft.cocit.util.UrlAPI;

@Entity
@CocTable(name = "地点信息维护", code = "hr_address", catalog = "_soft_base", pathPrefix = UrlAPI.URL_NS//
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
		@CocField(name = "上级地点", mode = "*:N v:S c:E e:E", property = "parent", gridOrder = 5)//
		, @CocField(name = "名称", mode = "*:N v:S c:M e:M", property = "name", gridOrder = 1)//
		, @CocField(name = "编号", mode = "*:N v:S c:E e:E", property = "code", gridOrder = 2)//
		, @CocField(name = "所属部门", mode = "*:N v:S c:E e:E", property = "department", gridOrder = 4)//
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
public class Address extends NameEntity {

	// 上级地点
	@ManyToOne
	Address parent;

	// 所属部门
	@ManyToOne
	Department department;

	public Address getParent() {
		return parent;
	}

	public void setParent(Address parent) {
		this.parent = parent;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
