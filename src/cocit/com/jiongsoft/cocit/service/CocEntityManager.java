package com.jiongsoft.cocit.service;

import java.util.List;

import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.jiongsoft.cocit.utils.CocException;

/**
 * CoC平台实体管理器：实体就是数据表记录在JAVA中对应的对象。实体管理器就是用来管理（增加、删除、修改、查询）数据表记录的。
 * <p>
 * <UL>
 * <LI>执行相关操作前将会进行数据校验；
 * <LI>将会调用业务插件并执行相应的逻辑；
 * </UL>
 * 
 * @author yongshan.ji
 * 
 * @param <T>实体类泛型
 */
public interface CocEntityManager<T> {

	/**
	 * 保存前需要先对数据校验。
	 * 
	 * @param entity
	 * @param opMode
	 * @return
	 * @throws CocException
	 *             保存前校验失败将抛出异常
	 */
	public int save(T entity, String opMode) throws CocException;

	public int delete(T entity, String opMode) throws CocException;

	public int delete(Long entityID, String opMode) throws CocException;

	public int delete(Long[] entityIDArray, String opMode) throws CocException;

	public T load(Long entityID, String opMode) throws CocException;

	public List<T> query(CndExpr expr, String opMode) throws CocException;

	public int count(CndExpr expr, String opMode) throws CocException;

	public Class getType();
}
