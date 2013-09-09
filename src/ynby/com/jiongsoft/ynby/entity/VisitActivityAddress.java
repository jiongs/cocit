package com.jiongsoft.ynby.entity;

import javax.persistence.Entity;

import com.jiongsoft.cocit.utils.ActionUtil;
import com.kmetop.demsy.comlib.biz.ann.BzAct;
import com.kmetop.demsy.comlib.biz.ann.BzFld;
import com.kmetop.demsy.comlib.biz.ann.BzGrp;
import com.kmetop.demsy.comlib.biz.ann.BzSys;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

@Entity
@BzSys(name = "参观地点设置", code = "VisitActivityAddress", catalog = "_ynby_visit", actionPathPrefix = ActionUtil.ACTION_PATH_PREFIX, orderby = 1//
// 操作按钮
, actions = { @BzAct(name = "添加", typeCode = 101, mode = "c")//
		, @BzAct(name = "修改", typeCode = 102, mode = "e") //
		, @BzAct(name = "删除", typeCode = 299, mode = "d") //
		, @BzAct(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@BzGrp(name = "基本信息", code = "basic"//
// 业务字段
, fields = { @BzFld(name = "地点名称", property = "name", mode = "c:M e:E v:S *:N", gridOrder = 1) //
		, @BzFld(name = "地点编号", property = "code", mode = "c:M e:E v:S *:N", gridOrder = 2) //
		, @BzFld(name = "联系人", property = "contactPerson", mode = "c:M e:E v:S *:N", gridOrder = 3) //
		, @BzFld(name = "联系电话", property = "contactTel", mode = "c:M e:E v:S *:N", gridOrder = 4) //
		, @BzFld(name = "地点描述", property = "desc", mode = "c:E e:E v:S *:N", gridOrder = 10) //
}// end: fields
) // end: BzGrp
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
