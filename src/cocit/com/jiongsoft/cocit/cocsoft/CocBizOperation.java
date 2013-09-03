package com.jiongsoft.cocit.cocsoft;

import java.util.List;

/**
 * 组件化自定义软件数据操作，也称“组件化自定义数据操作”、“自定义数据操作”、“数据操作”等。
 * 
 * <UL>
 * <LI>代表一个运行时的自定义数据操作，通常由定义在数据库中的数据实体解析而来；
 * <LI>与数据表的关系：每个数据操作只能隶属于一个数据表，而一个数据表可以包含多个数据操作；
 * <LI>同一个数据表内数据操作之间的关系：数据操作可以是树形结构，用于构成树形结构的操作菜单；
 * </UL>
 * 
 * @author jiongs753
 * 
 */
public interface CocBizOperation extends CocObject {
	
	String getLogo();

	/**
	 * 获取该操作的操作码，操作码用于计算字段编辑模式。
	 * 
	 * @return
	 */
	String getOperationCode();

	/**
	 * 获取该操作的子操作。
	 * 
	 * @return
	 */
	List<CocBizOperation> getChildrenBizOperations();
}
