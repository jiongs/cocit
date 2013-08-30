package com.jiongsoft.cocit.coft.impl.demsy;

import java.util.ArrayList;
import java.util.List;

import com.jiongsoft.cocit.coft.CoftModule;
import com.jiongsoft.cocit.coft.CoftModuleFactory;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.IModuleEngine;
import com.kmetop.demsy.comlib.security.IModule;

public class DemsyCoftModuleFactory implements CoftModuleFactory {

	private IModuleEngine moduleEngine;

	public DemsyCoftModuleFactory() {
		moduleEngine = Demsy.moduleEngine;
	}

	@Override
	public CoftModule getModule(Long moduleID) {
		return new DemsyCoftModule(moduleEngine.getModule(moduleID));
	}

	public static class DemsyCoftModule implements CoftModule {
		private IModule module;
		private List<CoftModule> children;

		private DemsyCoftModule(IModule module) {
			this.module = module;
			this.children = new ArrayList();
		}

		public void addChild(DemsyCoftModule module) {
			this.children.add(module);
		}

		@Override
		public byte getType() {
			return Byte.valueOf("" + module.getType());
		}

		@Override
		public Long getID() {
			return module.getId();
		}

		@Override
		public String getName() {
			return module.getName();
		}

		public List<CoftModule> getChildren() {
			return children;
		}

	}
}
