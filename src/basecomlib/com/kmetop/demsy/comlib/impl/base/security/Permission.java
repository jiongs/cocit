package com.kmetop.demsy.comlib.impl.base.security;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZ_DEL;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_ADMIN;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_ADMIN_PERMISSION;
import static com.kmetop.demsy.comlib.LibConst.ORDER_SYSADMIN_PERMISSION;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocField2;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.kmetop.demsy.comlib.biz.field.Dataset;
import com.kmetop.demsy.comlib.impl.BizComponent;
import com.kmetop.demsy.comlib.security.IPermission;

@Entity
@CocTable(name = "功能模块授权", code = BIZSYS_ADMIN_PERMISSION, catalog = BIZCATA_ADMIN, orderby = ORDER_SYSADMIN_PERMISSION, buildin = false//
, actions = {
//
// @CocOperation(name = "授权", typeCode = TYPE_BZFORM_NEW, mode = "c", pluginName = "com.kmetop.demsy.plugins.security.SavePermission")//
		@CocOperation(name = "授权", typeCode = TYPE_BZFORM_NEW, mode = "c2", pluginName = "com.kmetop.demsy.plugins.security.SavePermission")//
		, @CocOperation(name = "编辑", typeCode = TYPE_BZFORM_EDIT, mode = "e", pluginName = "com.kmetop.demsy.plugins.security.SavePermission") //
		, @CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d", pluginName = "com.kmetop.demsy.plugins.security.SavePermission") //
		, @CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
}//
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
, fields = {
//
		@CocField(property = "name", name = "标题", mode = "*:N v:S c:M c2:M e:M", gridOrder = 1) //
		, @CocField(property = "type", name = "权限类型", mode = "*:N v:S c:M c2:M e:M", options = "1:允许,0:禁止", gridOrder = 2) //

		// COCIT V1 授权规则：
		, @CocField(property = "userTable", name = "用户类型", mode = "*:N v:S c2:M e:M", gridOrder = 3, desc = "被授权的用户类型，如“后台管理员、网站注册会员”等。语法：用户实体表ID、用户实体表编号、用户实体表GUID") //
		, @CocField(property = "userFilter", name = "用户列表", mode = "*:N v:S c2:E e:E", gridOrder = 4, desc = "描述被授权的用户，一个JSON条件表达式。如法：条件表达式“{group: [2,30,224], role: [90]}”或用户ID数组“[1,5,9,'test','jiyongshan']”") //
		, @CocField(property = "opFilter", name = "操作权限", mode = "*:N v:S c2:M e:M", gridOrder = 5, desc = "描述可以对实体表进行哪些操作？语法：由规则条目组成的JSON数组如['1:2:*', '2:*:v', '*:*:e', ..., '1', ':2', '::c']，条目规则“module:table:op”") //
		, @CocField(property = "dataFilter", name = "数据权限", mode = "*:N v:S c2:E e:E", gridOrder = 6, desc = "实体表GRID数据过滤器，用来过滤被授权的数据。语法：条件表达式“{catalog: [1,2,3], type: 4}”或数据ID数组“[1,2,14,35,123]”") //
		, @CocField(property = "desc", name = "权限描述", mode = "*:N v:S c:M c2:M e:M", gridOrder = 7, desc = "说明该权限的目的和用途。") //

		// COCIT V1/DEMSY V2 公用权限状态
		, @CocField(name = "权限状态", property = "disabled", mode = "*:N v:S c:E c2:E e:E", options = "0:启用,1:停用", gridOrder = 8) //
		, @CocField(name = "有效期自", property = "expiredFrom", mode = "*:N v:S c:E c2:E e:E", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 12) //
		, @CocField(name = "有效期至", property = "expiredTo", mode = "*:N v:S c:E c2:E e:E", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 13) //
		, @CocField(name = "创建时间", property = "created", mode = "*:N v:S", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 20) //
		, @CocField(name = "创建者帐号", property = "createdBy", mode = "*:N v:S", gridOrder = 21) //
//
}),// end: CocGroup
		@CocGroup(name = "不再使用", code = "other"//
		, fields = {
				// DEMSY V2 授权规则：不再使用
				@CocField(property = "users", mode = "*:N c:E") //
				, @CocField(property = "users.module", name = "用户类型", isTransient = true, mode = "*:N") //
				, @CocField(property = "users.rules2", name = "用户规则", isTransient = true, mode = "*:N") //
				, @CocField(property = "datas", mode = "*:N c:E") //
				, @CocField(property = "datas.module", name = "功能模块", isTransient = true, mode = "*:N") //
				, @CocField(property = "datas.rules2", name = "数据权限", isTransient = true, mode = "*:N") //
				, @CocField(property = "actions", name = "模块操作", mode = "*:N") //
		}), // end: CocGroup
}// end: groups
)
public class Permission extends BizComponent implements IPermission {
	protected Date expiredFrom;

	protected Date expiredTo;

	protected Boolean type;

	/*
	 * DEMSY版本授权表达式
	 */
	/**
	 * 以JSON文本存储并转换成对象
	 * 
	 * @deprecated COC平台不再使用
	 */
	@CocField(name = "用户", mode = "*:N c:E"//
	, children = {
			//
			@CocField2(property = "moduleGuid", name = "用户类型", options = "['refSystemExtends eq BaseUser']", order = 1)//
			, @CocField2(property = "rules", name = "用户名称", order = 2) //
	})
	protected Dataset users;

	/**
	 * 以JSON文本存储并转换成对象
	 * 
	 * @deprecated COC平台不再使用
	 */
	@CocField(name = "模块", mode = "*:N c:E"//
	, children = {
			//
			@CocField2(property = "moduleGuid", name = "功能模块", order = 1)//
			, @CocField2(property = "rules", name = "数据权限", order = 2) //
	})
	protected Dataset datas;

	/**
	 * 授权操作
	 * 
	 * @deprecated COC平台不再使用
	 */
	protected String actions;

	/*
	 * COCIT版本授权表达式
	 */
	/**
	 * 用户实体表：被授权的用户类型。如：后台管理员、网站注册会员等；可以是用户实体表ID、用户实体表编号、用户实体表GUID。
	 * 
	 * @since CoC V1
	 */
	@Column(length = 64)
	protected String userTable;

	/**
	 * 用户过滤器：用来过滤被授权的用户。
	 * <p>
	 * <UL>
	 * <LI>JSON条件表达式：可以被转换成{@link CndExpr}对象，用来查询过滤用户；{group: [2,30,224], role: [90]}
	 * <LI>JSON用户ID数组：由用户ID或登录帐号组成；如[1,5,9,"test","jiyongshan"]
	 * <LI>空：表示用户实体表中的所有用户；
	 * </UL>
	 * 
	 * @since CoC V1
	 */
	@Column(length = 512)
	protected String userFilter;

	/**
	 * 实体表操作过滤器：用来描述被授权的“模块”“实体表”及“操作”。
	 * <p>
	 * 规则：有规则条目组成的数组JSON表达式；规则条目：“module:table:op”
	 * <UL>
	 * <LI>module：可以是实体表ID、实体表编号、实体表GUID；ID和编号可以是人为指定的；GUID则是系统自动获取的。
	 * <LI>table：可以是实体表ID，实体表编号，实体表GUID；ID和编号可以是人为指定的；GUID则是系统自动获取的。
	 * <LI>op：可以是操作ID，操作模式；ID和模式都可以是人为指定的。
	 * <LI>如：["1:2:*", "2:*:v", "*:*:e", ..., "1", ":2", "::c"]
	 * <UL>
	 * <LI>"1:2:*"说明：模块1:实体表2:所有操作
	 * <LI>"2:*:v"说明：模块2:所有实体表:查看操作
	 * <LI>"*:*:E"说明：所有模块:所有实体表:编辑操作
	 * <LI>"1"说明：等于“1:*:*”，模块1
	 * <LI>":2"说明：等于“*:2”，任何模块中的 实体表2
	 * <LI>"::c"说明：等于“*:*:c”，所有添加操作
	 * </UL>
	 * </LI>
	 * </UL>
	 * 
	 * @since CoC V1
	 */
	@Column(length = 512)
	protected String opFilter;

	/**
	 * 实体表GRID数据过滤器：用来过滤被授权的数据。
	 * <UL>
	 * <LI>JSON条件表达式：可以被转换成{@link CndExpr}对象，用来查询数据；{catalog: [1,2,3], type: 4}，catalog字段在列表中，type等于4。
	 * <LI>JSON数组：由数据ID组成的数组；如：[1,2,14,35,123]
	 * </UL>
	 * 
	 * @since CoC V1
	 */
	@Column(length = 512)
	protected String dataFilter;

	//
	// public String getInfo() {
	// StringBuffer sb = new StringBuffer();
	//
	// if (users != null) {
	// if (!Str.isEmpty(users.getModuleGuid()))
	// sb.append(users.getModule());
	//
	// if (!Str.isEmpty(users.getRules2()))
	// sb.append("(").append(users.getRules2()).append(")");
	// }
	// if (type != null && !type)
	// sb.append("【禁止操作】");
	// else
	// sb.append("【允许操作】");
	// if (datas != null) {
	// if (!Str.isEmpty(datas.getModuleGuid())) {
	// sb.append("功能模块(").append(datas.getModule()).append(")");
	// }
	//
	// if (!Str.isEmpty(datas.getRules2()))
	// sb.append("数据(").append(datas.getRules2()).append(")");
	// }
	//
	// return sb.toString();
	// }

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

	public String getUserTable() {
		return userTable;
	}

	public void setUserTable(String userTable) {
		this.userTable = userTable;
	}

	public String getUserFilter() {
		return userFilter;
	}

	public void setUserFilter(String userFilter) {
		this.userFilter = userFilter;
	}

	public String getOpFilter() {
		return opFilter;
	}

	public void setOpFilter(String opFilter) {
		this.opFilter = opFilter;
	}

	public String getDataFilter() {
		return dataFilter;
	}

	public void setDataFilter(String dataFilter) {
		this.dataFilter = dataFilter;
	}

}
