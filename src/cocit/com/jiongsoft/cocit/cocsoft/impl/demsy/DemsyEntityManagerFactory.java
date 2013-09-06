package com.jiongsoft.cocit.cocsoft.impl.demsy;

import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocsoft.EntityManager;
import com.jiongsoft.cocit.cocsoft.EntityManagerFactory;

public class DemsyEntityManagerFactory implements EntityManagerFactory {

	@Override
	public EntityManager getEntityManager(CocBizModule module, CocBizTable table) {
		return new DemsyEntityManager(module, table);
	}

}
