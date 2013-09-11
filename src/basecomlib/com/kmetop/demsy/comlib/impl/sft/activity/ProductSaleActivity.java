package com.kmetop.demsy.comlib.impl.sft.activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

@Entity
@CocTable(name = "产品促销", code = "ProductSaleActivity", orderby = 4//
, actions = { @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(property = "catalog", gridOrder = 1) //
		, @CocField(property = "buyerInfo", gridOrder = 2) //
		, @CocField(property = "orderInfo", gridOrder = 3) //
		, @CocField(property = "tradeInfo", gridOrder = 4) //
		, @CocField(property = "createdIP", gridOrder = 5) //
}) //
}// end groups
)
public class ProductSaleActivity extends SFTBizComponent {
	@CocField(name = "活动分类", mode = "c:HM e:M", options = "['type eq 5']")
	@ManyToOne
	protected ActivityCatalog catalog;

	@CocField(name = "买家信息", mode = "c:M *:S", privacy = true)
	protected String buyerInfo;

	@CocField(name = "订单信息", mode = "c:M *:S", privacy = true)
	protected String orderInfo;

	@CocField(name = "交易信息", mode = "c:M *:S", privacy = true)
	protected String tradeInfo;

	@Column(length = 32)
	@CocField(name = "IP地址", mode = "*:N v:S", privacy = true)
	protected String createdIP;

	public ActivityCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(ActivityCatalog catalog) {
		this.catalog = catalog;
	}

	public String getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(String buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getTradeInfo() {
		return tradeInfo;
	}

	public void setTradeInfo(String tradeInfo) {
		this.tradeInfo = tradeInfo;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}
}
