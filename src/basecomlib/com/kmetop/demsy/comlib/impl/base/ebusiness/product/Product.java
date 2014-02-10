package com.kmetop.demsy.comlib.impl.base.ebusiness.product;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT_N;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.biz.field.MultiUpload;
import com.kmetop.demsy.comlib.biz.field.RichText;
import com.kmetop.demsy.comlib.biz.field.SubSystem;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.eshop.IProduct;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "产品信息管理", code = IProduct.SYS_CODE, orderby = 4,//
actions = {
//
		@CocOperation(name = "添加产品", typeCode = TYPE_BZFORM_NEW, mode = "c"),//
		@CocOperation(name = "调整分类", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu"),//
		@CocOperation(name = "批量修改处方标志", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu5"),//
		// @CocOperation(name = "调整销售数据", typeCode = TYPE_BZFORM_EDIT, mode = "e1"),//
		// @CocOperation(name = "批量调整运营商", typeCode = TYPE_BZFORM_EDIT_N, mode =
		// "bu1"),//
		// @CocOperation(name = "批量调整发货点", typeCode = TYPE_BZFORM_EDIT_N, mode =
		// "bu3"),//
		// @CocOperation(name = "批量设置网购", typeCode = TYPE_BZFORM_EDIT_N, mode =
		// "bu4"),//
		@CocOperation(jsonData = "CommonBizAction.data.js") //
},//
groups = {
//
		@CocGroup(name = "基本信息", code = "basic",//
		fields = {
				//
				@CocField(name = "产品名称", property = "name", mode = "c:M e:M v:S *:N", gridOrder = 1)//
				, @CocField(name = "产品编码", property = "code", mode = "c:E e:E v:S *:N")//
				, @CocField(property = "spec", gridOrder = 2) //
				, @CocField(property = "catalog")//
				, @CocField(property = "operator")//
				, @CocField(property = "storage")//
				, @CocField(property = "recommend", gridOrder = 3) //
				, @CocField(property = "otcDrug", gridOrder = 4)//
				, @CocField(property = "allowBuy", gridOrder = 5) //
				, @CocField(property = "image") //
		})//
		, @CocGroup(name = "销售信息", code = "saleinfo",//
		fields = {
				//
				@CocField(property = "onlineDate") //
				, @CocField(property = "oldPrice", gridOrder = 6)//
				, @CocField(property = "price", gridOrder = 7)//
				, @CocField(property = "saleNum", gridOrder = 8)//
				, @CocField(property = "stockNum", gridOrder = 9) //
				, @CocField(property = "keywords") //
		// , @CocField(name = "产品说明", property = "desc", mode = "c:E e:E v:S *:N") //
		})//
		, @CocGroup(name = "产品详情", code = "content",//
		fields = {
		//
		@CocField(property = "content") //
		// })//
		// , @CocGroup(name = "自定义特征", code = "attributes",//
		// fields = { @CocField(property = "attributes") //
		// })//
		// , @CocGroup(name = "产品型号", code = "models",//
		// fields = { @CocField(property = "models") //
		// })//
		// , @CocGroup(name = "产品图库", code = "images",//
		// fields = { @CocField(property = "images") //
		})//
		, @CocGroup(name = "其他属性", code = "other",//
		fields = {
				//
				@CocField(property = "clickNum", gridOrder = 10)//
				, @CocField(property = "commentNum")//
				, @CocField(name = "人工顺序", property = "orderby", uiTemplate = "ui.widget.field.Spinner", mode = "v:S *:N") //
				, @CocField(name = "创建时间", property = "created", mode = "*:N v:S") //
				, @CocField(name = "更新时间", property = "updated", mode = "*:N v:S") //
				, @CocField(name = "创建帐号", property = "createdBy", mode = "*:N v:S") //
				, @CocField(name = "更新帐号", property = "updatedBy", mode = "*:N v:S") //
		}) // @CocGroup
}// end groups
)
public class Product extends BizComponent implements IProduct {
	@ManyToOne
	@CocField(name = "产品类别", mode = "c:M e:M bu:E v:S *:N")
	protected ProductCatalog catalog;

	@OneToMany(mappedBy = "product")
	@CocField(name = "产品特征", uploadType = "*.jpg;*.gif;*.png;*.bmp", isTransient = true, gridField = false, mode = "c:E e:E v:S *:N", refrenceFields = "name,image,desc")
	protected SubSystem<ProductAttribute> attributes;

	@OneToMany(mappedBy = "product")
	@CocField(name = "产品型号", uploadType = "*.jpg;*.gif;*.png;*.bmp", isTransient = true, gridField = false, mode = "c:E e:E v:S *:N", refrenceFields = "name,image,desc")
	protected SubSystem<ProductModel> models;

	@ManyToOne
	@CocField(name = "发货地址", mode = "c:E e:E bu3:E v:S *:N", disabledNavi = true)
	protected ProductDeliver storage;

	@ManyToOne
	@CocField(name = "运营商", mode = "c:E e:E bu1:E v:S *:N", disabledNavi = true)
	protected ProductOperator operator;

	@CocField(name = "推荐标志", options = "0:未推荐,1:推荐", mode = "c:E e:E bu2:E v:S *:N")
	protected byte recommend;

	@CocField(name = "产品图片", uploadType = "*.jpg;*.gif;*.png;*.bmp", mode = "c:E e:E v:S *:N")
	protected Upload image;

	@CocField(name = "产品图库", uploadType = "*.jpg;*.gif;*.png;*.bmp;*.swf;*.flv;*.zip", mode = "c:E e:E v:S *:N", gridField = false)
	protected MultiUpload images;

	@CocField(name = "产品规格", mode = "c:E e:E v:S *:N")
	protected String spec;

	@CocField(name = "上架时间", pattern = "yyyy-MM-dd", mode = "c:E e:E v:S *:N")
	protected Date onlineDate;

	@CocField(name = "产品详情", gridField = false, mode = "c:E e:E v:S *:N")
	protected RichText content;

	@Column(length = 256)
	@CocField(name = "关键字", mode = "c:E e:E v:S *:N")
	protected String keywords;

	/*
	 * 销售数据
	 */
	@CocField(name = "产品原价", mode = "c:E e:E e1:E v:S *:N", pattern = "#,##0.00")
	protected Double oldPrice;

	@CocField(name = "产品现价", mode = "c:E e:E e1:E v:S *:N", pattern = "#,##0.00")
	protected Double price;

	@CocField(name = "产品状态", mode = "c:E e:E e1:E bu4:E v:S *:N", options = "0:上架展示,1:上架销售,2:已下架")
	protected byte allowBuy;

	@CocField(name = "产品属性", mode = "c:E e:E e1:E bu5:E v:S *:N", options = "0:无,1:处方药,2:非处方药,3:保健养生,4:日化用品,99:其他")
	protected byte otcDrug;

	@CocField(name = "库存数量", mode = "c:E e:E e1:E v:S *:N")
	protected Integer stockNum;

	@CocField(name = "销售数量", mode = "c:E e:E e1:E v:S *:N")
	protected Integer saleNum;

	// 统计信息收集=====================
	@CocField(name = "点击次数", mode = "*:N v:S")
	protected Integer clickNum;

	@CocField(name = "评论次数", mode = "*:N v:S")
	protected Integer commentNum;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public ProductCatalog getCatalog() {
		return catalog;
	}

	public ProductOperator getOperator() {
		return operator;
	}

	public Upload getImage() {
		return image;
	}

	@Transient
	private Double nowPrice;

	/**
	 * 获取产品现价：如果有促销活动则返回促销价，否则返回正常网购价
	 */
	public Double getNowPrice() {
		if (nowPrice == null) {
			// 允许网购
			if (this.allowBuy == 1) {
				// 促销价
				nowPrice = getBarginPrice();

				// 正常价格
				if (nowPrice == null) {
					nowPrice = getPrice();
				}

				// 库存不足
				if (this.stockNum != null && this.stockNum <= 0)
					nowPrice = 0.0;

				if (nowPrice == null)
					nowPrice = 0.0;
			} else {
				nowPrice = 0.0;
			}
		}

		return nowPrice == 0.0 ? null : nowPrice;
	}

	/*
	 * 获取 产品促销价
	 */
	public Double getBarginPrice() {
		// TODO:
		return null;
	}

	public String getBarginNote() {
		// TODO:
		return "";
	}

	public Double getPrice() {
		return price;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCatalog(ProductCatalog catalog) {
		this.catalog = catalog;
	}

	public void setOperator(ProductOperator vender) {
		this.operator = vender;
	}

	public void setImage(Upload image) {
		this.image = image;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public byte getRecommend() {
		return recommend;
	}

	public void setRecommend(byte recommend) {
		this.recommend = recommend;
	}

	public RichText getContent() {
		return content;
	}

	public void setContent(RichText content) {
		this.content = content;
	}

	public Double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Double getBalance() {
		if (oldPrice != null && getNowPrice() != null)
			return oldPrice - getNowPrice();

		return null;
	}

	public ProductDeliver getStorage() {
		return storage;
	}

	public void setStorage(ProductDeliver logisticsAddress) {
		this.storage = logisticsAddress;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Date getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}

	public byte getAllowBuy() {
		return allowBuy;
	}

	public void setAllowBuy(byte allowBuy) {
		this.allowBuy = allowBuy;
	}

	public MultiUpload getImages() {
		return images;
	}

	public void setImages(MultiUpload images) {
		this.images = images;
	}

	// @Override
	// public String getDesc() {
	// if (Str.isEmpty(this.desc)) {
	// String str = this.content == null ? "" : content.toString();
	// return Str.substr(Str.escapeHTML(str).trim(), 50);
	// }
	//
	// return Str.substr(Str.escapeHTML(desc).trim(), 50);
	// }

	public SubSystem<ProductAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(SubSystem<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

	public SubSystem<ProductModel> getModels() {
		return models;
	}

	public void setModels(SubSystem<ProductModel> models) {
		this.models = models;
	}

	public byte getOtcDrug() {
		return otcDrug;
	}

	public void setOtcDrug(byte otcDrug) {
		this.otcDrug = otcDrug;
	}

	public String getOtcDrugName() {
		switch (otcDrug) {
		case 0:
			return "无";
		case 1:
			return "处方药";
		case 2:
			return "非处方药";
		case 3:
			return "保健养生";
		case 4:
			return "日化用品";
		default:
			return "其他";
		}

	}
}
