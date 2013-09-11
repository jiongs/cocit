package com.jiongsoft.cocit.service;

import java.util.List;

import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.cocit.utils.Tree;

/**
 * 组件化自定义数据实体表，也称“组件化自定义数据表”、“自定义数据表”、“数据表”等。
 * <UL>
 * <LI>代表一个运行时的自定义数据表，通常由定义在数据库中的数据实体解析而来；
 * <LI>与数据模块的关系：每个数据表可以被绑定到多个数据模块，但每个数据模块只能绑定一个数据表；
 * <LI>与数据分组的关系：每个数据表可以包含多个数据分组；
 * <LI>与数据字段的关系：每个数据表可以包含多个数据字段；
 * <LI>与数据子表的关系：每个数据表可以包含多个数据子表，用于描述一主多从结构的数据关系；
 * <LI>与数据父表的关系：每个数据表可以隶属于多个数据父表（如：自定义数据字段表既是自定义数据组的子表，也是自定义数据表的子表）；
 * <LI>与数据操作的关系：每个数据表可以包含多个数据操作，但每个数据操作只能隶属于一个数据表；
 * </UL>
 * 
 * @author jiongs753
 * 
 */
public interface CocEntityTableService extends CocService {

	// /**
	// * 获取该数据表的“子数据表”。
	// *
	// * @return
	// */
	// List<CocEntityTableService> getChildrenDataTables();

	/**
	 * 获取该数据表中的数据分组
	 * 
	 * @return
	 */
	List<CocEntityGroupService> getEntityGroups();

	/**
	 * 获取该数据表中的所有数据字段。
	 * 
	 * @return
	 */
	List<CocEntityFieldService> getEntityFields();

	/**
	 * 获取该数据表中的数据操作，用于生存操作菜单。
	 * 
	 * @return
	 */
	List<CocEntityOperationService> getEntityOperations();

	/**
	 * 获取该数据表中用作导航树的字段。
	 * 
	 * @return
	 */
	List<CocEntityFieldService> getEntityFieldsForNaviTree();

	/**
	 * 获取该数据表中用作Grid列的字段。
	 * 
	 * @return
	 */
	List<CocEntityFieldService> getEntityFieldsForGrid();

	Tree getEntityNaviData();

	/**
	 * 验证指定的数据是否合法
	 * 
	 * @param opMode
	 * @param entityData
	 * @throws CocException
	 */
	void validate(String opMode, Object entityData) throws CocException;
}