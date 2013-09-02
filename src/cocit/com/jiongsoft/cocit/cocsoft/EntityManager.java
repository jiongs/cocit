package com.jiongsoft.cocit.cocsoft;

import java.util.List;

import com.jiongsoft.cocit.corm.expr.CndExpr;

/**
 * CoC平台实体管理器：实体就是数据表记录在JAVA中对应的对象。实体管理器就是用来管理（增加、删除、修改、查询）数据表记录的。
 * 
 * @author yongshan.ji
 * 
 * @param <T>实体类泛型
 */
public interface EntityManager<T> {

	public int save(T entity, String opCode);

	public int delete(T entity, String opCode);

	public T load(Long entityID, String opCode);

	public List<T> query(CndExpr expr, String opCode);

	public int count(CndExpr expr, String opCode);
}
