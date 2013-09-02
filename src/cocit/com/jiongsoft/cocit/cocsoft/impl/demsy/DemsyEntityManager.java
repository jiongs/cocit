package com.jiongsoft.cocit.cocsoft.impl.demsy;

import java.util.List;

import com.jiongsoft.cocit.cocsoft.CocBizModule;
import com.jiongsoft.cocit.cocsoft.EntityManager;
import com.jiongsoft.cocit.corm.expr.CndExpr;
import com.jiongsoft.cocit.corm.expr.Expr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.biz.IBizManager;

public class DemsyEntityManager implements EntityManager {
	private IBizManager bizManager;

	DemsyEntityManager(CocBizModule m) {
		DemsyCocBizModule module = (DemsyCocBizModule) m;
		bizManager = Demsy.bizManagerFactory.getManager(module.getEntity());
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
	public List query(Expr expr, String opCode) {
		return bizManager.query(opCode, (CndExpr) expr);
	}

}
