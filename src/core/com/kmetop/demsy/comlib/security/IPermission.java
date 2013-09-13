package com.kmetop.demsy.comlib.security;

import java.util.Date;

import com.kmetop.demsy.comlib.biz.field.Dataset;

/**
 * <b>权限许可：</b>即“允许”或“拒绝”主体对象在有效期内访问系统资源做什么事情？
 * <p>
 * 
 * @author yongshan.ji
 */
public interface IPermission {
	boolean isDenied();

	boolean isDisabled();

	Date getExpiredFrom();

	Date getExpiredTo();

	/**
	 * 使用表达式指定待授权的用户，即：对满足条件的所有用户授权访问特定的模块{@link #getDatas()}
	 * 
	 * @deprecated
	 * @return
	 */
	Dataset getUsers();

	/**
	 * 使用表达式指定被授权的模块，即：将满足条件的所有模块授权给特定的用户{@link #getUsers()}
	 * 
	 * @deprecated
	 * @return
	 */
	Dataset getDatas();

}
