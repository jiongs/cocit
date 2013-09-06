package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.CocBizTable;
import com.jiongsoft.cocit.cocsoft.EntityManager;
import com.jiongsoft.cocit.corm.expr.CndExpr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.biz.IBizManager;

public class DemsyEntityManager implements EntityManager {
	private IBizManager bizManager;

	DemsyEntityManager(CocBizModule m, CocBizTable t) {
		DemsyCocBizModule module = (DemsyCocBizModule) m;
		DemsyCocBizTable table = (DemsyCocBizTable) t;

		bizManager = Demsy.bizManagerFactory.getManager(module.getEntity(), table.getEntity());
	}

	@Override
	public int save(Object entity, String opCode) {
		return bizManager.save(entity, opCode);
	}

	@Override
	public int delete(Object entity, String opCode) {
		return bizManager.delete(entity, opCode);
	}

	@Override
	public Object load(Long entityID, String opCode) {
		return bizManager.load(entityID, opCode);
	}

	@Override
	public List query(CndExpr expr, String opCode) {
		return bizManager.query(opCode, expr);
	}

	@Override
	public int count(CndExpr expr, String opCode) {
		return bizManager.count(opCode, expr);
	}

	@Override
	public Class getType() {
		return bizManager.getType();
	}

}
