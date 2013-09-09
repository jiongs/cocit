package com.kmetop.demsy.comlib.biz.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务表注释。使用该注释将自动在数据库中创建业务表的定义
 * 
 * @author yongshan.ji
 * 
 */
@Target(value = { ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BzSys {

	public long id() default 0;

	public String name() default "";

	public String code() default "";

	public String desc() default "";

	public String template() default "";

	BzGrp[] groups() default {};

	BzAct[] actions() default {};

	public byte layout() default 0;

	public String catalog() default "";

	public String jsonData() default "";

	public boolean buildin() default false;

	public int orderby() default 1000;

	/**
	 * 业务实体类字节码的编译版本。
	 * <p>
	 * 如果.class文件中的版本号和数据库中业务表定义的版本好不一致，将不能获得动态编译后的实体类class。
	 * 
	 * @return
	 */
	public String version() default "";

	/**
	 * 功能模块访问路径前缀：如"/coc"。路径前缀即决定该模块使用了“什么版本自定义平台”？默认使用DEMSY版本
	 * 
	 * @return
	 */
	public String actionPathPrefix() default "";

}
