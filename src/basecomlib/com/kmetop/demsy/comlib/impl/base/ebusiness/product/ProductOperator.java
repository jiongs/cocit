package com.kmetop.demsy.comlib.impl.base.ebusiness.product;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;

import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.eshop.IProductOperator;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "运营机构设置", code = IProductOperator.SYS_CODE, orderby = 2,//
actions = {
//
		@CocOperation(name = "添加", typeCode = TYPE_BZFORM_NEW, mode = "c"),//
		@CocOperation(jsonData = "CommonBizAction.data.js") //
},//
groups = {//
@CocGroup(name = "基本信息", code = "basic",//
fields = {
// 基本信息
		@CocField(name = "名称", property = "name", mode = "c:M e:M"),//
		@CocField(name = "编码", property = "code"),//
		// 其他信息
		@CocField(name = "排序", property = "orderby", uiTemplate = "ui.widget.field.Spinner"), //
		@CocField(name = "描述", property = "desc"), //
		@CocField(name = "创建时间", property = "created", mode = "*:N v:P"), //
		@CocField(name = "更新时间", property = "updated", mode = "*:N v:P"), //
		@CocField(name = "创建帐号", property = "createdBy", mode = "*:N v:P"), //
		@CocField(name = "更新帐号", property = "updatedBy", mode = "*:N v:P") //
}) // @CocGroup
}// end groups
)
public class ProductOperator extends BizComponent implements IProductOperator {
}
