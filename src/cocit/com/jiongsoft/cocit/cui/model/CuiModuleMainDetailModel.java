package com.jiongsoft.cocit.cui.model;

/**
 * 功能模块主从结构UI模型：由主模块和多个从模块组成。
 * 
 * @author yongshan.ji
 * 
 */
public class CuiModuleMainDetailModel extends BaseCuiModel {

	private CuiModulesTabsModel main;

	private CuiModulesTabsModel detail;

	public CuiModuleMainDetailModel(CuiModulesTabsModel main, CuiModulesTabsModel detail) {
		this.main = main;
		this.detail = detail;
	}

	public CuiModulesTabsModel getMain() {
		return main;
	}

	public void setMain(CuiModulesTabsModel main) {
		this.main = main;
	}

	public CuiModulesTabsModel getDetail() {
		return detail;
	}

	public void setDetail(CuiModulesTabsModel subModulesModel) {
		this.detail = subModulesModel;
	}
}
