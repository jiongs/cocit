package com.kmetop.demsy.comlib.impl.base.security;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZ_DEL;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_ADMIN;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_ADMIN_PERMISSION;
import static com.kmetop.demsy.comlib.LibConst.ORDER_SYSADMIN_PERMISSION;

import java.util.Date;

import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocField2;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.biz.field.Dataset;
import com.kmetop.demsy.comlib.impl.BizComponent;
import com.kmetop.demsy.comlib.security.IPermission;
import com.kmetop.demsy.lang.Str;

@Entity
@CocTable(name = "功能模块授权", code = BIZSYS_ADMIN_PERMISSION, catalog = BIZCATA_ADMIN, orderby = ORDER_SYSADMIN_PERMISSION, buildin = false//
, actions = { @CocOperation(name = "新增权限", typeCode = TYPE_BZFORM_NEW, mode = "c", plugin = "com.kmetop.demsy.plugins.security.SavePermission")//
		, @CocOperation(name = "编辑", typeCode = TYPE_BZFORM_EDIT, mode = "e", plugin = "com.kmetop.demsy.plugins.security.SavePermission") //
		, @CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d", plugin = "com.kmetop.demsy.plugins.security.SavePermission") //
		, @CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(property = "users") //
		, @CocField(property = "users.module", name = "用户类型", isTransient = true, gridOrder = 1, mode = "*:N") //
		, @CocField(property = "users.rules2", name = "用户规则", isTransient = true, gridOrder = 2, mode = "*:N") //
		, @CocField(property = "datas") //
		, @CocField(property = "datas.module", name = "功能模块", isTransient = true, gridOrder = 4, mode = "*:N") //
		, @CocField(property = "datas.rules2", name = "数据权限", isTransient = true, gridOrder = 5, mode = "*:N") //
		, @CocField(property = "actions", gridOrder = 6) //
}), @CocGroup(name = "其他属性", code = "other"//
, fields = { @CocField(property = "expiredFrom", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(property = "expiredTo", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(property = "type", gridOrder = 3) //
		, @CocField(name = "停用状态", property = "disabled", options = "1:停用,0:启用", disabledNavi = true) //
		, @CocField(name = "权限描述", property = "info", isTransient = true, mode = "*:N v:S") //
		, @CocField(name = "授权时间", property = "updated", mode = "*:P", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(name = "授权帐号", property = "updatedBy", mode = "*:P") //
		, @CocField(name = "创建时间", property = "created", mode = "*:P", pattern = "yyyy-MM-dd HH:mm:ss") //
		, @CocField(name = "创建帐号", property = "createdBy", mode = "*:P") //
}) }// end groups
)
public class Permission extends BizComponent implements IPermission {
	@CocField(name = "有效期自")
	protected Date expiredFrom;

	@CocField(name = "有效期至")
	protected Date expiredTo;

	@CocField(name = "权限类型", options = "1:允许操作,0:禁止操作", disabledNavi = true)
	protected Boolean type;

	@CocField(name = "用户"//
	, children = {
			//
			@CocField2(property = "moduleGuid", name = "用户类型", options = "['refSystemExtends eq BaseUser']", order = 1)//
			, @CocField2(property = "rules", name = "用户名称", order = 2) //
	})
	protected Dataset users;

	@CocField(name = "模块"//
	, children = {
			//
			@CocField2(property = "moduleGuid", name = "功能模块", order = 1)//
			, @CocField2(property = "rules", name = "数据权限", order = 2) //
	})
	protected Dataset datas;

	@CocField(name = "模块操作")
	protected String actions;

	public String getInfo() {
		StringBuffer sb = new StringBuffer();

		if (users != null) {
			if (!Str.isEmpty(users.getModuleGuid()))
				sb.append(users.getModule());

			if (!Str.isEmpty(users.getRules2()))
				sb.append("(").append(users.getRules2()).append(")");
		}
		if (type != null && !type)
			sb.append("【禁止操作】");
		else
			sb.append("【允许操作】");
		if (datas != null) {
			if (!Str.isEmpty(datas.getModuleGuid())) {
				sb.append("功能模块(").append(datas.getModule()).append(")");
			}

			if (!Str.isEmpty(datas.getRules2()))
				sb.append("数据(").append(datas.getRules2()).append(")");
		}

		return sb.toString();
	}

	public Date getExpiredFrom() {
		return expiredFrom;
	}

	public Date getExpiredTo() {
		return expiredTo;
	}

	public boolean isDenied() {
		return type != null && type == false;
	}

	public Boolean getType() {
		return type;
	}

	public void setExpiredFrom(Date expiredFrom) {
		this.expiredFrom = expiredFrom;
	}

	public void setExpiredTo(Date expiredTo) {
		this.expiredTo = expiredTo;
	}

	public void setType(Boolean denied) {
		this.type = denied;
	}

	public Dataset getUsers() {
		return users;
	}

	public Dataset getDatas() {
		return datas;
	}

	public void setUsers(Dataset users) {
		this.users = users;
	}

	public void setDatas(Dataset datas) {
		this.datas = datas;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

}
