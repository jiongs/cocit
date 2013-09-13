package com.kmetop.demsy.security.impl;

import java.util.Date;

import com.jiongsoft.cocit.orm.expr.CndExpr;

class PermissionEntry {
	/**
	 * 权限条目有效起始日期
	 */
	Date expiredFrom;

	/**
	 * 权限条目有效截止日期
	 */
	Date expiredTo;

	/**
	 * 权限类型：拒绝还是允许？true 表示拒绝，false 表示允许。
	 */
	boolean denied;

	/**
	 * 用户角色ID：与“用户模块”和“用户过滤器”互斥，用来表示当前授权项对哪些角色的用户有效。
	 */
	byte roleID = -1;

	/**
	 * 用户模块：与“用户角色ID”互斥，用来标识用户类型，如：后台管理员、网站注册会员等。
	 */
	long userModule;

	/**
	 * 用户过滤器：用来表示当前授权项对哪些用户群有效？
	 */
	CndExpr userFilter;

	/**
	 * 数据模块：用来表示当前授权项中的主体对象（用户角色、用户群）可以访问哪个模块？
	 */
	long dataModule;

	/**
	 * 数据过滤器：用来表示当前授权项中的主题对象（用户角色、用户群）可以访问模块中的哪些数据集？
	 */
	CndExpr dataExpr;

	// String[] actions;

}
