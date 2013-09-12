package com.jiongsoft.cocit.service;

public interface SecurityManager {
	/**
	 * 检查指定的用户是否有权访问指定的实体表
	 * 
	 * @param user
	 *            用户登录帐号
	 * @param moduleID
	 *            模块ID
	 * @param tableID
	 *            模块中的实体表ID
	 * @return 有权操作返回true，无权操作则返回false。
	 */
	public boolean check(String user, Long moduleID, Long tableID);

	/**
	 * 检查指定的用户是否有权访问指定的实体表中的数据
	 * 
	 * @param user
	 *            用户登录帐号
	 * @param moduleID
	 *            模块ID
	 * @param tableID
	 *            模块中的实体表ID
	 * @param entityID
	 *            实体数据ID
	 * @return 有权操作返回true，无权操作则返回false。
	 */
	public boolean check(String user, Long moduleID, Long tableID, Long[] entityID);

	/**
	 * 检查指定用户是否有权执行模块中的实体表操作
	 * 
	 * @param user
	 *            用户登录帐号
	 * @param moduleID
	 *            模块ID
	 * @param tableID
	 *            模块中的实体表ID
	 * @param opMode
	 *            操作模式：实体表中操作的唯一标识符
	 * @return 有权操作返回true，无权操作则返回false。
	 */
	public boolean check(String user, Long moduleID, Long tableID, String opMode);

	/**
	 * 检查指定用户是否有权操作模块中实体数据
	 * 
	 * @param user
	 *            用户登录帐号
	 * @param moduleID
	 *            模块ID
	 * @param tableID
	 *            模块中的实体表ID
	 * @param opMode
	 *            操作模式：实体表中操作的唯一标识符
	 * @param entityID
	 *            实体数据ID
	 * @return 有权操作返回true，无权操作则返回false。
	 */
	public boolean check(String user, Long moduleID, Long tableID, String opMode, Long[] entityID);
}
