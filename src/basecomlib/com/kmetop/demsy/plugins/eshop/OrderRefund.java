package com.kmetop.demsy.plugins.eshop;

import java.util.List;

import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.entity.impl.BaseEntityPlugin;
import com.kmetop.demsy.actions.OrderActions;
import com.kmetop.demsy.comlib.eshop.IOrder;
import com.kmetop.demsy.lang.DemsyException;
import com.kmetop.demsy.log.Log;
import com.kmetop.demsy.log.Logs;
import com.kmetop.demsy.orm.IOrm;

/**
 * 卖家退款
 * 
 * @author yongshan.ji
 * 
 */
public class OrderRefund extends BaseEntityPlugin {
	protected static Log log = Logs.get();

	protected void process(IOrm orm, IOrder order) {
		if (order.getStatus() != IOrder.STATUS_WAIT_SELLER_SEND_GOODS) {
			throw new DemsyException("退款失败：只能为[%s]的订单退款！订单(%s),订单状态(%s)", IOrder.STATUS_TITLES[IOrder.STATUS_WAIT_SELLER_SEND_GOODS], order.getOrderID(), IOrder.STATUS_TITLES[order.getStatus()]);
		}
		order.setStatus(IOrder.STATUS_WAIT_BUYER_CONFIRM_REFUND);

		OrderActions.processOrder(orm, order, order.getTradeID(), IOrder.STATUS_WAIT_BUYER_CONFIRM_REFUND);
	}

	@Override
	public void before(CocEntityEvent event) {
		IOrm orm = (IOrm) event.getOrm();
		Object data = event.getEntityData();
		if (data instanceof List) {
			List list = (List) data;
			for (Object obj : list) {
				process(orm, (IOrder) obj);
			}
		} else {
			process(orm, (IOrder) data);
		}
	}

	@Override
	public void after(CocEntityEvent event) {

	}

	@Override
	public void loaded(CocEntityEvent event) {

	}

}
