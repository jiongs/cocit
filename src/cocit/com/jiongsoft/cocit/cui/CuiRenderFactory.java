package com.jiongsoft.cocit.cui;

/**
 * CuiRender工厂：用于创建并管理CuiRender对象。
 * 
 * @author yongshan.ji
 * 
 */
public interface CuiRenderFactory {

	CuiRender getRender(Class<? extends CuiModel> model);
}
