package com.jiongsoft.cocit.service.impl.demsy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityOperationService;
import com.jiongsoft.cocit.service.CocEntityTableService;
import com.jiongsoft.cocit.service.CocServiceFactory;
import com.jiongsoft.cocit.service.CocSoftService;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.biz.IBizField;
import com.kmetop.demsy.comlib.impl.base.biz.BizAction;
import com.kmetop.demsy.comlib.impl.base.lib.DemsySoft;
import com.kmetop.demsy.comlib.impl.base.security.Module;
import com.kmetop.demsy.comlib.impl.sft.system.SFTSystem;
import com.kmetop.demsy.engine.BizEngine;
import com.kmetop.demsy.engine.ModuleEngine;

/**
 * 
 * @author jiongs753
 * 
 */
public class DemsyServiceFactory implements CocServiceFactory {

	private BizEngine bizEngine;
	private ModuleEngine moduleEngine;
	private Map<String, CocSoftService> cacheSoft;

	public DemsyServiceFactory() {
		moduleEngine = (ModuleEngine) Demsy.moduleEngine;
		bizEngine = (BizEngine) Demsy.bizEngine;
		cacheSoft = new Hashtable();
	}

	@Override
	public CocSoftService getSoftService(String domain) {
		synchronized (cacheSoft) {
			CocSoftService ret = cacheSoft.get(domain);
			if (ret == null) {
				DemsySoft ds = (DemsySoft) moduleEngine.getSoft(domain);
				if (ds != null) {
					ret = new DemsySoftService(ds);
					cacheSoft.put(domain, ret);
				}
			}

			return ret;
		}

	}

	private DemsyEntityTableService makeBizTable(Module module, SFTSystem system) {
		DemsyEntityTableService ret = new DemsyEntityTableService(system);

		return ret;
	}

	@Override
	public CocEntityModuleService getEntityModuleService(Long moduleID) {
		Module module = (Module) moduleEngine.getModule(moduleID);
		if (module == null)
			return null;

		SFTSystem mainSystem = (SFTSystem) moduleEngine.getSystem(module);

		//
		CocEntityTableService mainDataTable = this.makeBizTable(module, mainSystem);
		DemsyEntityModuleService ret = new DemsyEntityModuleService(module, mainDataTable);

		//
		List<CocEntityTableService> childrenDataTables = new ArrayList();
		List<IBizField> fkFields = bizEngine.getFieldsOfSlave(mainSystem);
		for (IBizField fkField : fkFields) {
			SFTSystem fkSystem = (SFTSystem) fkField.getSystem();

			DemsyEntityTableService bizTable = this.makeBizTable(module, fkSystem);
			childrenDataTables.add(bizTable);

			// 设置该子表通过哪个字段引用了主表？
			bizTable.set("fkfield", fkField.getPropName());

			// TODO:应通过模块表达式来解析数据表对象，目前暂时不支持模块对数据表的引用表达式。

		}

		ret.setChildrenDataTables(childrenDataTables);

		return ret;
	}

	@Override
	public CocEntityTableService getEntityTableService(Long moduleID, Long tableID) {
		Module module = (Module) moduleEngine.getModule(moduleID);
		SFTSystem system = (SFTSystem) bizEngine.getSystem(tableID);

		// TODO:应通过模块表达式来解析数据表对象，目前暂时不支持模块对数据表的引用表达式。

		return this.makeBizTable(module, system);
	}

	@Override
	public CocEntityOperationService getEntityOperationService(Long moduleID, Long tableID, Long operationID) {
		if (operationID == null)
			return null;

		BizAction action = (BizAction) bizEngine.getAction(tableID, operationID);
		if (action == null)
			return null;

		return new DemsyEntityOperationService(action);
	}

}