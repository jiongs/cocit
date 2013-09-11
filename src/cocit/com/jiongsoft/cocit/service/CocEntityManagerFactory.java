package com.jiongsoft.cocit.service;

public interface CocEntityManagerFactory {

	public CocEntityManager getEntityManager(CocEntityModuleService module, CocEntityTableService table);
}
