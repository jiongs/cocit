package com.jiongsoft.cocit.service.impl.demsy;

import java.util.List;

import com.jiongsoft.cocit.orm.expr.CndExpr;
import com.jiongsoft.cocit.service.CocEntityModuleService;
import com.jiongsoft.cocit.service.CocEntityTableService;
import com.jiongsoft.cocit.service.CocEntityManager;
import com.jiongsoft.cocit.utils.CocException;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.biz.IBizManager;
import com.kmetop.demsy.comlib.impl.base.security.Module;
import com.kmetop.demsy.comlib.impl.sft.system.SFTSystem;

public class DemsyEntityManager implements CocEntityManager {
	private IBizManager bizManager;
	private DemsyEntityModuleService cocmodule;
	private DemsyEntityTableService coctable;

	DemsyEntityManager(CocEntityModuleService m, CocEntityTableService t) {
		cocmodule = (DemsyEntityModuleService) m;
		coctable = (DemsyEntityTableService) t;

		Module module = null;
		if (cocmodule != null)
			module = cocmodule.getEntity();

		SFTSystem system = coctable.getEntity();

		bizManager = Demsy.bizManagerFactory.getManager(module, system);
	}

	@Override
	public int save(Object entity, String opMode) throws CocException {
		coctable.validate(opMode, entity);

		return bizManager.save(entity, opMode);
	}

	@Override
	public int delete(Object entity, String opMode) {
		return bizManager.delete(entity, opMode);
	}

	@Override
	public Object load(Long entityID, String opMode) {
		return bizManager.load(entityID, opMode);
	}

	@Override
	public List query(CndExpr expr, String opMode) {
		return bizManager.query(opMode, expr);
	}

	@Override
	public int count(CndExpr expr, String opMode) {
		return bizManager.count(opMode, expr);
	}

	@Override
	public int delete(Long id, String opMode) throws CocException {
		return bizManager.delete(id, opMode);
	}

	@Override
	public int delete(Long[] idArray, String opMode) throws CocException {
		for (Long id : idArray) {
			bizManager.delete(id, opMode);
		}
		return idArray.length;
	}

	@Override
	public Class getType() {
		return bizManager.getType();
	}

}