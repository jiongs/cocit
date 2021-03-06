package com.jiongsoft.ynby.entity;

import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.util.UrlAPI;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

/**
 * 
 * “走进云南白药”参观活动之“活动地点”
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "活动地点设置", code = "VisitActivityAddress", catalog = "_ynby_visit", pathPrefix = UrlAPI.URL_NS, orderby = 1//
// 操作按钮
, actions = { @CocOperation(name = "添加", typeCode = 101, mode = "c")//
		, @CocOperation(name = "修改", typeCode = 102, mode = "e") //
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
// 业务字段
, fields = { @CocField(name = "地点名称", property = "name", mode = "c:M e:E v:S *:N", gridOrder = 1) //
		, @CocField(name = "地点编号", property = "code", mode = "c:M e:E v:S *:N", gridOrder = 2) //
		, @CocField(name = "联系人", property = "contactPerson", mode = "c:M e:E v:S *:N", gridOrder = 3) //
		, @CocField(name = "联系电话", property = "contactTel", mode = "c:M e:E v:S *:N", gridOrder = 4) //
		, @CocField(name = "地点描述", property = "desc", mode = "c:E e:E v:S *:N", gridOrder = 10) //
}// end: fields
) // end: CocGroup
}// end: groups
)
public class VisitActivityAddress extends SFTBizComponent {

	String contactPerson;

	String contactTel;

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
}
