package com.jiongsoft.cocit.service;

import java.io.Serializable;

import com.jiongsoft.cocit.orm.expr.CndExpr;

/**
 * 安全管理器：每个软件实体有一个对应的安全管理器。
 * 
 * @author yongshan.ji
 * 
 */
public interface SecurityManager {
	/**
	 * 检查“用户”是否有权访问“模块”？
	 * <p>
	 * 通常可以调用该方法检查用户是否可以看到模块功能菜单。
	 * 
	 * @param user
	 *            用户：用户登录帐号或用户ID；如果user类型是String则为登录帐号，如果是Long则为用户ID。
	 * @param module
	 *            模块：模块编号或模块ID；如果module类型是String则为模块编号，如果是Long则为模块ID。
	 * @return 有权操作返回true，无权操作则返回false。
	 */
	public boolean check(Serializable user, Serializable module);

	/**
	 * 检查“用户”是否有权访问“模块”“实体表”中的“数据”？
	 * 
	 * @param user
	 *            用户：用户登录帐号或用户ID；如果user类型是String则为登录帐号，如果是Long则为用户ID。
	 * @param module
	 *            模块：模块编号或模块ID；如果module类型是String则为模块编号，如果是Long则为模块ID。
	 * @param table
	 *            实体表：实体表编号或实体表ID；如果table类型是String则为实体表编号，如果是Long则为实体表ID。
	 * @param data
	 *            数据：即实体表中的数据ID数组
	 * 
	 * @return 有权操作返回true，无权操作则返回false。
	 */
	public boolean check(Serializable user, Serializable module, Serializable table, Long... data);

	/**
	 * 检查“用户”是否有权“操作”“模块”中的“实体表”数据？
	 * 
	 * @param user
	 *            用户：用户登录帐号或用户ID；如果user类型是String则为登录帐号，如果是Long则为用户ID。
	 * @param module
	 *            模块：模块编号或模块ID；如果module类型是String则为模块编号，如果是Long则为模块ID。
	 * @param table
	 *            实体表：实体表编号或实体表ID；如果table类型是String则为实体表编号，如果是Long则为实体表ID。
	 * @param op
	 *            操作：操作模式或操作ID；如果op类型是String则为操作模式，如果是Long则为操作ID；实体表中操作可通过操作模式唯一标识。
	 *            <p>
	 *            操作如：添加、删除、修改、查询、导入、导出等。
	 * @param data
	 *            数据：即实体表中的数据ID数组
	 * @return 有权操作返回true，无权操作则返回false。
	 */
	public boolean check(Serializable user, Serializable module, Serializable table, Serializable op, Long... data);

	/**
	 * 获取“模块”中“实体表”数据过滤器：用来过滤有权访问的“模块”中“实体表”的GRID数据。
	 * <p>
	 * 当“用户”管理某个模块“实体表”时，在主界面GRID中所能看到的数据记录将无条件受该查询条件的限制。
	 * 
	 * @param user
	 *            用户：用户登录帐号或用户ID；如果user类型是String则为登录帐号，如果是Long则为用户ID。
	 * @param module
	 *            模块：模块编号或模块ID；如果module类型是String则为模块编号，如果是Long则为模块ID。
	 * @param table
	 *            实体表：实体表编号或实体表ID；如果table类型是String则为实体表编号，如果是Long则为实体表ID。
	 * @return 查询表达式：准寻Expr表达式规范。
	 */
	public CndExpr getDataFilter(Serializable user, Serializable module, Serializable table);
}
