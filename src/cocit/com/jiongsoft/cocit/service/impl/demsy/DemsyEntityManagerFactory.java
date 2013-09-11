package com.jiongsoft.cocit.service.impl.demsy;

import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityTableService;
import com.jiongsoft.cocit.service.CocEntityManager;
import com.jiongsoft.cocit.service.CocEntityManagerFactory;

public class DemsyEntityManagerFactory implements CocEntityManagerFactory {

	@Override
	public CocEntityManager getEntityManager(CocEntityModuleService module, CocEntityTableService table) {
		return new DemsyEntityManager(module, table);
	}

}
