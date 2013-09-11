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
import com.kmetop.demsy.comlib.eshop.IProductAttribute;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "产品特征", code = IProductAttribute.SYS_CODE, orderby = 4,//
actions = { @CocOperation(name = "添加特征", typeCode = TYPE_BZFORM_NEW, mode = "c"),//
		@CocOperation(jsonData = "CommonBizAction.data.js") //
},//
groups = { //
@CocGroup(name = "基本信息", code = "basic",//
fields = { @CocField(property = "product"),//
		@CocField(name = "特征名称", property = "name", mode = "c:M e:M v:S *:N"),//
		@CocField(name = "特征编码", property = "code", mode = "c:E e:E v:S *:N"),//
		@CocField(property = "image"),//
		@CocField(property = "images"),//
		@CocField(name = "特征描述", property = "desc", type = "RichText", mode = "c:E e:E *:N v:S", gridField = false) //
}) }// end groups
)
public class ProductAttribute extends BizComponent implements IProductAttribute {
	@ManyToOne
	@CocField(name = "产品名称", mode = "c:M e:M bu:E v:S *:N", disabledNavi = true, isFkChild = true)
	protected Product product;

	@CocField(name = "特征图片", uploadType = "*.jpg;*.gif;*.png;*.bmp", mode = "c:E e:E v:S *:N")
	protected Upload image;

	@CocField(name = "特征图库", uploadType = "*.jpg;*.gif;*.png;*.bmp;*.swf;*.flv;", mode = "c:E e:E v:S *:N", gridField = false)
	protected MultiUpload images;

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

	public MultiUpload getImages() {
		return images;
	}

	public void setImages(MultiUpload images) {
		this.images = images;
	}
}
