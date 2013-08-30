package com.jiongsoft.cocit.cui.model;

import java.util.ArrayList;
import java.util.List;

public class CuiModulesTabsModel extends BaseCuiModel {
	private List<CuiModuleModel> models;

	public CuiModulesTabsModel() {
		this.models = new ArrayList();
	}

	public CuiModulesTabsModel(List<CuiModuleModel> models) {
		this.models = models;
		if (this.models == null)
			this.models = new ArrayList();
	}

	public void addModuleModel(CuiModuleModel model) {
		models.add(model);
	}

	public List<CuiModuleModel> getModels() {
		return models;
	}

	public void setModels(List<CuiModuleModel> moduleModels) {
		this.models = moduleModels;
	}
}
