package com.jiongsoft.cocit.cui;

import com.jiongsoft.cocit.coft.Codule;

/**
 * UI模型工厂：负责创建或管理{@link CuiModel}
 * 
 * @author jiongs753
 * 
 */
public interface CuiModelFactory {

	/**
	 * 获取模块主界面窗体模型
	 * 
	 * @param module
	 * @return
	 */
	CuiModel getMainModel(Codule module);
}
