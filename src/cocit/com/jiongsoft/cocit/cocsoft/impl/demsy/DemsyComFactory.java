package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizOperation;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocsoft.CocSoft;
import com.jiongsoft.cocit.cocsoft.ComFactory;
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
public class DemsyComFactory implements ComFactory {

	private BizEngine bizEngine;
	private ModuleEngine moduleEngine;
	private Map<String, CocSoft> cacheSoft;

	public DemsyComFactory() {
		moduleEngine = (ModuleEngine) Demsy.moduleEngine;
		bizEngine = (BizEngine) Demsy.bizEngine;
		cacheSoft = new Hashtable();
	}

	@Override
	public CocSoft getSoft(String domain) {
		synchronized (cacheSoft) {
			CocSoft ret = cacheSoft.get(domain);
			if (ret == null) {
				DemsySoft ds = (DemsySoft) moduleEngine.getSoft(domain);
				if (ds != null) {
					ret = new DemsyCocSoft(ds);
					cacheSoft.put(domain, ret);
				}
			}

			return ret;
		}

	}

	private DemsyCocBizTable makeBizTable(Module module, SFTSystem system) {
		DemsyCocBizTable ret = new DemsyCocBizTable(system);

		return ret;
	}

	@Override
	public CocBizModule getBizModule(Long moduleID) {
		Module module = (Module) moduleEngine.getModule(moduleID);
		if (module == null)
			return null;

		SFTSystem mainSystem = (SFTSystem) moduleEngine.getSystem(module);

		//
		CocBizTable mainDataTable = this.makeBizTable(module, mainSystem);
		DemsyCocBizModule ret = new DemsyCocBizModule(module, mainDataTable);

		//
		List<CocBizTable> childrenDataTables = new ArrayList();
		List<IBizField> fkFields = bizEngine.getFieldsOfSlave(mainSystem);
		for (IBizField fkField : fkFields) {
			SFTSystem fkSystem = (SFTSystem) fkField.getSystem();

			DemsyCocBizTable bizTable = this.makeBizTable(module, fkSystem);
			childrenDataTables.add(bizTable);

			// 设置该子表通过哪个字段引用了主表？
			bizTable.set("fkfield", fkField.getPropName());

			// TODO:应通过模块表达式来解析数据表对象，目前暂时不支持模块对数据表的引用表达式。

		}

		ret.setChildrenDataTables(childrenDataTables);

		return ret;
	}

	@Override
	public CocBizTable getBizTable(Long moduleID, Long tableID) {
		Module module = (Module) moduleEngine.getModule(moduleID);
		SFTSystem system = (SFTSystem) bizEngine.getSystem(tableID);

		// TODO:应通过模块表达式来解析数据表对象，目前暂时不支持模块对数据表的引用表达式。

		return this.makeBizTable(module, system);
	}

	@Override
	public CocBizOperation getBizOperation(Long moduleID, Long tableID, Long operationID) {
		if (operationID == null)
			return null;

		BizAction action = (BizAction) bizEngine.getAction(tableID, operationID);
		if (action == null)
			return null;

		return new DemsyCocBizOperation(action);
	}

}
