package com.kmetop.demsy.comlib.impl.base.ebusiness.product;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.biz.field.MultiUpload;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.eshop.IProductModel;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "产品型号", code = IProductModel.SYS_CODE, orderby = 4,//
actions = { @CocOperation(name = "添加型号", typeCode = TYPE_BZFORM_NEW, mode = "c"),//
		@CocOperation(jsonData = "CommonBizAction.data.js") //
},//
groups = { //
@CocGroup(name = "基本信息", code = "basic",//
fields = { @CocField(property = "product"),//
		@CocField(name = "型号名称", property = "name", mode = "c:M e:M v:S *:N"),//
		@CocField(name = "型号编码", property = "code", mode = "c:E e:E v:S *:N"),//
		@CocField(property = "image"),//
		@CocField(property = "images"),//
		@CocField(property = "spec"), //
		@CocField(name = "型号描述", property = "desc", type = "RichText", mode = "c:E e:E *:N v:S", gridField = false) //
}) }// end groups
)
public class ProductModel extends BizComponent implements IProductModel {
	@ManyToOne
	@CocField(name = "产品名称", mode = "c:M e:M bu:E v:S *:N", disabledNavi = true, isFkChild = true)
	protected Product product;

	@CocField(name = "型号图片", uploadType = "*.jpg;*.gif;*.png;*.bmp", mode = "c:E e:E v:S *:N")
	protected Upload image;

	@CocField(name = "型号图库", uploadType = "*.jpg;*.gif;*.png;*.bmp;*.swf;*.flv;", mode = "c:E e:E v:S *:N", gridField = false)
	protected MultiUpload images;

	@CocField(name = "产品规格", mode = "c:E e:E v:S *:N")
	protected String spec;

	@CocField(name = "产品原价", mode = "c:E e:E v:S *:N", pattern = "#,##0.00")
	protected Double oldPrice;

	@CocField(name = "产品现价", mode = "c:E e:E v:S *:N", pattern = "#,##0.00")
	protected Double price;

	@CocField(name = "网购许可", mode = "c:E e:E v:S *:N", disabledNavi = true, options = "1:允许网购,0:禁止网购")
	protected byte allowBuy;

	@CocField(name = "库存数量", mode = "c:E e:E v:S *:N")
	protected Integer stockNum;

	@CocField(name = "销售数量", mode = "c:E e:E v:S *:N")
	protected Integer saleNum;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Upload getImage() {
		return image;
	}

	public void setImage(Upload image) {
		this.image = image;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public MultiUpload getImages() {
		return images;
	}

	public void setImages(MultiUpload images) {
		this.images = images;
	}
}
