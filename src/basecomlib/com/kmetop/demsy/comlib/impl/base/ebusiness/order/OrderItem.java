package com.kmetop.demsy.comlib.impl.base.ebusiness.order;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT_N;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.eshop.IOrder;
import com.kmetop.demsy.comlib.eshop.IOrderItem;
import com.kmetop.demsy.comlib.eshop.IProduct;
import com.kmetop.demsy.comlib.eshop.IProductCatalog;
import com.kmetop.demsy.comlib.eshop.IProductOperator;
import com.kmetop.demsy.comlib.impl.BizComponent;
import com.kmetop.demsy.comlib.impl.base.ebusiness.product.Product;
import com.kmetop.demsy.comlib.impl.base.ebusiness.product.ProductCatalog;
import com.kmetop.demsy.comlib.impl.base.ebusiness.product.ProductOperator;

@Entity
@CocTable(name = "订单清单", code = IOrderItem.SYS_CODE, orderby = 2,//
actions = { @CocOperation(name = "备货", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu1", pluginName = "com.kmetop.demsy.plugins.eshop.OrderSended")//
		, @CocOperation(name = "打折", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu", pluginName = "com.kmetop.demsy.plugins.eshop.OrderAdjustCast")//
		, @CocOperation(name = "详情", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
// , @CocOperation(name = "删除条目", typeCode = TYPE_BZ_DEL, mode = "d") //
},//
groups = { //
@CocGroup(name = "基本信息", code = "basic",//
fields = { @CocField(property = "order", gridOrder = 1) //
		, @CocField(name = "商品名称", property = "name", mode = "*:N v:S", gridOrder = 2)//
		, @CocField(name = "商品编码", property = "code", mode = "*:N v:S", gridOrder = 3)//
		, @CocField(property = "productCatalog")//
		, @CocField(property = "productOperator") //

}), @CocGroup(name = "订单费用", code = "stat",//
fields = { @CocField(property = "price", gridOrder = 4)//
		, @CocField(property = "amount", gridOrder = 5)//
		, @CocField(property = "discount", gridOrder = 6)//
		, @CocField(property = "subtotal", gridOrder = 7) //
		, @CocField(property = "status", gridOrder = 8) //
}), // @CocGroup
		@CocGroup(name = "其他属性", code = "other",//
		fields = {
				// 其他信息
				@CocField(name = "下单时间", property = "created", mode = "*:N v:S"), //
				@CocField(name = "更新时间", property = "updated", mode = "*:N v:S"), //
				@CocField(name = "下单帐号", property = "createdBy", mode = "*:N v:S"), //
				@CocField(name = "更新帐号", property = "updatedBy", mode = "*:N v:S"), //
				@CocField(name = "下单IP", property = "createdIP", mode = "*:N v:S") //
		}) // @CocGroup
}// end groups
)
public class OrderItem extends BizComponent implements IOrderItem {
	@ManyToOne
	@CocField(name = "订单", mode = "*:N v:S", disabledNavi = true, isFkChild = true)
	protected Order order;

	@ManyToOne
	@CocField(name = "产品类别", mode = "*:N v:S")
	protected ProductCatalog productCatalog;

	@ManyToOne
	@CocField(name = "运营商", mode = "*:N v:S")
	protected ProductOperator productOperator;

	@ManyToOne
	@CocField(name = "产品", mode = "*:N v:S", disabledNavi = true)
	protected Product product;

	@CocField(name = "单价(元)", mode = "*:N v:S", pattern = "#,##0.00")
	protected double price;

	@CocField(name = "数量", mode = "*:N v:S")
	protected int amount;

	@CocField(name = "折扣", mode = "*:N v:S bu:E")
	protected Double discount;

	@CocField(name = "小计(元)", mode = "*:N v:S bu:E", pattern = "#,##0.00")
	protected double subtotal;

	@Column(length = 64)
	@CocField(name = "IP地址", mode = "*:N v:S")
	protected String createdIP;

	// 0:购物车(未下单),9:已取消
	@CocField(name = "状态", mode = "*:N v:S", options = "2:已下单(待备货...),3:已备货")
	protected byte status;

	public Order getOrder() {
		return order;
	}

	public void setOrder(IOrder order) {
		this.order = (Order) order;
	}

	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}

	public void setProductCatalog(IProductCatalog catalog) {
		this.productCatalog = (ProductCatalog) catalog;
	}

	public ProductOperator getProductOperator() {
		return productOperator;
	}

	public void setProductOperator(IProductOperator vender) {
		this.productOperator = (ProductOperator) vender;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(IProduct product) {
		this.product = (Product) product;
	}
}
