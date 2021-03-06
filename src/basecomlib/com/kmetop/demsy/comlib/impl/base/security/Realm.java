package com.kmetop.demsy.comlib.impl.base.security;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_ADMIN;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_ADMIN_REALM;
import static com.kmetop.demsy.comlib.LibConst.ORDER_SYSADMIN_REALM;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.impl.BizComponent;
import com.kmetop.demsy.comlib.security.IRealm;

@Entity
@CocTable(name = "登录类型设置", code = BIZSYS_ADMIN_REALM, catalog = BIZCATA_ADMIN, orderby = ORDER_SYSADMIN_REALM, buildin = true//
, actions = { @CocOperation(name = "新增", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(name = "类型名称", property = "name", mode = "c:M e:M")//
		, @CocField(name = "类型编号", property = "code", mode = "c:M e:M") //
		, @CocField(property = "userModule") //
		, @CocField(name = "类型描述", property = "desc") //
		, @CocField(name = "创建时间", property = "created", mode = "*:P", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(name = "更新时间", property = "updated", mode = "*:P", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(name = "创建帐号", property = "createdBy", mode = "*:P") //
		, @CocField(name = "更新帐号", property = "updatedBy", mode = "*:P") //
}) }// end groups
, jsonData = "Realm.data.js"//
)
public class Realm extends BizComponent implements IRealm {

	@ManyToOne
	@CocField(name = "用户模块", disabledNavi = true, mode = "c:M e:M", options = "['refSystemExtends eq BaseUser']")
	private Module userModule;

	public Module getUserModule() {
		return userModule;
	}

	public void setUserModule(Module userModule) {
		this.userModule = userModule;
	}

}
