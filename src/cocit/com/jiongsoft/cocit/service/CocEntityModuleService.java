package com.jiongsoft.cocit.service;

import java.util.List;

/**
 * CoC业务模块，也称“组件化自定义数据模块”、“自定义数据模块”、“数据模块”等。
 * 
 * <UL>
 * <LI>代表一个运行时的自定义数据模块，通常由定义在数据库中的数据实体解析而来；
 * <LI>与模块的关系：数据模块是模块的一种；
 * <LI>与数据表的关系：每个数据模块只能绑定一个数据表，而每个数据表则可以被绑定到多个数据模块，绑定方式通常为绑定表达式（JSON表达式）；
 * </UL>
 * 
 * <B>绑定表达式JSON对象解析：</b>
 * <p>
 * <CODE>
 * {
 * entityTable: 1,
 * operations:[1, 2, 3],
 * groups:[1, 2, 3],
 * fields:[1, 2, 3],
 * naviTreeFields: [1, 2, 3],
 * gridFields: [],
 * children:[{},{},...{}],
 * }
 * </CODE>
 * <UL>
 * <LI>entityTable: 表示绑定的数据表ID；
 * <LI>operations: 表示绑定后的数据操作，是一个数组，数组中的每个元素为数据操作ID；
 * <LI>groups: 表示绑定后的数据组，是一个数组，数组中的每个元素为数据组ID；
 * <LI>fields: 表示绑定后的数据字段，是一个数组，数组中的每个元素为数据字段ID；
 * <LI>naviTreeFields: 表示绑定后的导航树字段，是一个数组，数组中的每个元素为支持导航树的字段ID；
 * <LI>gridFields: 表示绑定后的Grid字段，是一个数组，数组中的每个元素为支持导航树的字段ID；
 * <LI>children: 表示绑定后的数据子表，是一个数组，数组中的每个元素为一个绑定表达式；
 * </UL>
 * 
 * @author jiongs753
 * 
 * @param <T>
 */
public interface CocEntityModuleService extends CocModuleService {

	/**
	 * 获取模块主数据表，通常可以通过解析数据模块对数据表的“引用表达式”计算出主数据表对象。
	 * 
	 * @return
	 */
	CocEntityTableService getEntityTable();

	/**
	 * 获取模块子数据表，通常可以通过解析数据模块对数据表的“引用表达式”计算出从数据表对象。一个主数据表可以包含多个从数据表，用来描述一主多从结构。
	 * 
	 * @return
	 */
	List<CocEntityTableService> getChildrenEntityTables();
}
