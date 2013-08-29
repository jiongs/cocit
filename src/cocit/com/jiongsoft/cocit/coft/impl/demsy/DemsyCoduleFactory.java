package com.jiongsoft.cocit.coft.impl.demsy;

import com.jiongsoft.cocit.coft.Codule;
import com.jiongsoft.cocit.coft.CoduleFactory;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.IModuleEngine;
import com.kmetop.demsy.comlib.security.IModule;

public class DemsyCoduleFactory implements CoduleFactory {

	private IModuleEngine moduleEngine;

	public DemsyCoduleFactory() {
		moduleEngine = Demsy.moduleEngine;
	}

	@Override
	public Codule getModule(Long moduleID) {
		return new DemsyCoftModule(moduleEngine.getModule(moduleID));
	}

	public static class DemsyCoftModule implements Codule {
		private IModule module;

		private DemsyCoftModule(IModule module) {
			this.module = module;
		}

		@Override
		public byte getModuleType() {
			return Byte.valueOf("" + module.getType());
		}

		@Override
		public Long getModuleID() {
			return module.getId();
		}

	}
}
