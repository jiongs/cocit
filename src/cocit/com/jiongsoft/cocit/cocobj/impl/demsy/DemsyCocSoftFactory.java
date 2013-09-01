package com.jiongsoft.cocit.cocobj.impl.demsy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jiongsoft.cocit.cocobj.CobDataModule;
import com.jiongsoft.cocit.cocobj.CobDataTable;
import com.jiongsoft.cocit.cocobj.CobSoft;
import com.jiongsoft.cocit.cocobj.CocSoftFactory;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.entity.IDemsySoft;
import com.kmetop.demsy.comlib.impl.base.biz.BizAction;
import com.kmetop.demsy.comlib.impl.base.security.Module;
import com.kmetop.demsy.comlib.impl.sft.system.AbstractSystemData;
import com.kmetop.demsy.comlib.impl.sft.system.SFTSystem;
import com.kmetop.demsy.comlib.impl.sft.system.SystemDataGroup;
import com.kmetop.demsy.engine.BizEngine;
import com.kmetop.demsy.engine.ModuleEngine;

/**
 * 
 * @author jiongs753
 * 
 */
public class DemsyCocSoftFactory implements CocSoftFactory {

	private BizEngine bizEngine;
	private ModuleEngine moduleEngine;
	private Map<String, CobSoft> cacheSoft;

	public DemsyCocSoftFactory() {
		moduleEngine = (ModuleEngine) Demsy.moduleEngine;
		bizEngine = (BizEngine) Demsy.bizEngine;
		cacheSoft = new Hashtable();
	}

	@Override
	public CobSoft getSoft(String domain) {
		synchronized (cacheSoft) {
			CobSoft ret = cacheSoft.get(domain);
			if (ret == null) {
				IDemsySoft ds = moduleEngine.getSoft(domain);
				if (ds != null) {
					ret = new DemsyCobSoft(ds);
					cacheSoft.put(domain, ret);
				}
			}

			return ret;
		}

	}

	private CobDataTable makeDataTable(SFTSystem system) {
		DemsyCobDataTable ret = new DemsyCobDataTable(system);

		List<AbstractSystemData> dataFields = (List<AbstractSystemData>) bizEngine.getFields(system);
		List<SystemDataGroup> dataGroups = (List<SystemDataGroup>) bizEngine.getFieldGroups(system);
		List<BizAction> dataOperations = (List<BizAction>) bizEngine.getActions(system);
		List<AbstractSystemData> dataFieldsForNaviTree = (List<AbstractSystemData>) bizEngine.getFieldsOfNavi(system);
		List<AbstractSystemData> dataFieldsForGrid = (List<AbstractSystemData>) bizEngine.getFieldsOfGrid(system, null);
		ret.setDataFields(dataFields);
		ret.setDataFieldsForGrid(dataFieldsForGrid);
		ret.setDataFieldsForNaviTree(dataFieldsForNaviTree);
		ret.setDataGroups(dataGroups);
		ret.setDataOperations(dataOperations);

		return ret;
	}

	@Override
	public CobDataModule getDataModule(Long moduleID) {
		Module module = (Module) moduleEngine.getModule(moduleID);
		SFTSystem mainSystem = (SFTSystem) moduleEngine.getSystem(module);

		//
		CobDataTable mainDataTable = this.makeDataTable(mainSystem);
		DemsyCobDataModule ret = new DemsyCobDataModule(module, mainDataTable);

		//
		List<SFTSystem> childrenSystems = (List<SFTSystem>) bizEngine.getSystemsOfSlave(mainSystem);
		List<CobDataTable> childrenDataTables = new ArrayList();
		for (SFTSystem sys : childrenSystems) {

			// TODO:应通过模块表达式来解析数据表对象，目前暂时不支持模块对数据表的引用表达式。
			childrenDataTables.add(this.makeDataTable(sys));

		}

		ret.setChildrenDataTables(childrenDataTables);

		return ret;
	}

	@Override
	public CobDataTable getDataTable(Long moduleID, Long tableID) {
		SFTSystem system = (SFTSystem) bizEngine.getSystem(tableID);

		// TODO:应通过模块表达式来解析数据表对象，目前暂时不支持模块对数据表的引用表达式。

		return this.makeDataTable(system);
	}

}
