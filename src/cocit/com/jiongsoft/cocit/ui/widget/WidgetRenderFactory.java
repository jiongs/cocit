package com.jiongsoft.cocit.ui.widget;

import com.jiongsoft.cocit.ui.CuiModel;
import com.jiongsoft.cocit.ui.CuiRender;

/**
 * CuiRender工厂：用于创建并管理CuiRender对象。
 * 
 * @author yongshan.ji
 * 
 */
public interface WidgetRenderFactory {

	CuiRender getRender(Class<? extends CuiModel> model);
}
