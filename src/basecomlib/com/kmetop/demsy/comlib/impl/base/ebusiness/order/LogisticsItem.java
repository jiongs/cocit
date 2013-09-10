package com.kmetop.demsy.comlib.impl.base.ebusiness.order;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.eshop.ILogistics;
import com.kmetop.demsy.comlib.eshop.ILogisticsItem;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "发货清单", code = ILogisticsItem.SYS_CODE, orderby = 4,//
actions = { @CocOperation(name = "详情", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
// , @BzAct(name = "永久删除", typeCode = TYPE_BZ_DEL, mode = "d") //
},//
groups = { //
@CocGroup(name = "基本信息", code = "basic",//
fields = { @CocField(property = "logistics", gridOrder = 1) //
		, @CocField(name = "商品名称", property = "name", mode = "*:N v:S", gridOrder = 2)//
		, @CocField(name = "商品编码", property = "code", mode = "*:N v:S", gridOrder = 3)//
		, @CocField(property = "price") //
		, @CocField(property = "amount") //
		, @CocField(property = "subtotal") //
		, @CocField(property = "orderID") //
		, @CocField(property = "orderItemID") //
		, @CocField(property = "productID") //
}) })
public class LogisticsItem extends BizComponent implements ILogisticsItem {
	@ManyToOne
	@CocField(name = "物流单", mode = "*:N v:S", disabledNavi = true, isChildTable = true)
	protected Logistics logistics;

	@CocField(name = "单价(元)", mode = "*:N v:S", pattern = "#,##0.00")
	protected Double price;

	@CocField(name = "数量", mode = "*:N v:S")
	protected int amount;

	@CocField(name = "小计(元)", mode = "*:N v:S", pattern = "#,##0.00")
	protected Double subtotal;

	@Column(length = 32)
	@CocField(name = "订单号", mode = "*:N v:S")
	protected String orderID;

	@CocField(name = "订单条目ID", mode = "*:N v:S")
	protected Long orderItemID;

	@CocField(name = "产品ID", mode = "*:N v:S")
	protected Long productID;

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(ILogistics logistics) {
		this.logistics = (Logistics) logistics;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Long getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(Long orderItemID) {
		this.orderItemID = orderItemID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}
}
