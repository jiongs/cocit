package com.jiongsoft.cocit.entity.asset;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.entity.common.Supplier;
import com.jiongsoft.cocit.entity.common.Uom;
import com.jiongsoft.cocit.entity.hr.Address;
import com.jiongsoft.cocit.entity.hr.Employee;
import com.jiongsoft.cocit.entity.impl.NameEntity;
import com.jiongsoft.cocit.util.UrlAPI;

/**
 * 设备/资产管理
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "采购管理", code = "assets", catalog = "_soft_base", pathPrefix = UrlAPI.URL_NS//
/*
 * 操作按钮
 */
, actions = {
//
		@CocOperation(name = "入库", typeCode = 101, mode = "c")//
		, @CocOperation(name = "修改", typeCode = 102, mode = "e") //
		, @CocOperation(name = "领用", typeCode = 102, mode = "e1") //
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
		, @CocOperation(name = "导出到XLS", typeCode = 107, mode = "export")//
		, @CocOperation(name = "从XLS导入", typeCode = 108, mode = "import") //
}// end: actions
/*
 * 字段分组
 */
, groups = { //
@CocGroup(name = "基本信息", code = "basic_info"//
, fields = {
//
		@CocField(name = "资产分类", mode = "*:N v:S c:E e:E", property = "category", gridOrder = 3)//
		, @CocField(name = "设备名称", mode = "*:N v:S c:M e:M", property = "name", gridOrder = 1)//
		, @CocField(name = "设备编号", mode = "*:N v:S c:M e:M", property = "code", gridOrder = 2)//
		, @CocField(name = "设备来源", mode = "*:N v:S c:M e:M", property = "origin")//
		, @CocField(name = "设备规格", mode = "*:N v:S c:E e:E", property = "specification", disabledNavi = true)//
		, @CocField(name = "设备型号", mode = "*:N v:S c:E e:E", property = "model", disabledNavi = true)//
		, @CocField(name = "设备用途", mode = "*:N v:S c:M e:M", property = "purpose", disabledNavi = true)//
		, @CocField(name = "经费来源", mode = "*:N v:S c:M e:M", property = "fundsOrigin")//
		, @CocField(name = "供货商", mode = "*:N v:S c:M e:M", property = "supplier")//
		, @CocField(name = "计量单位", mode = "*:N v:S c:E e:E", property = "uom", disabledNavi = true)//
		, @CocField(name = "存放地点", mode = "*:N v:S c:M e:M", property = "depositAddress")//
		, @CocField(name = "经办人", mode = "*:N v:S c:M e:M", property = "dealPerson", disabledNavi = true)//
		, @CocField(name = "设备状态", mode = "*:N v:S c:M e:M", property = "status", options = "0:新购置,1:闲置,10:停用,99:已报废")//
		, @CocField(name = "购置日期", mode = "*:N v:S c:E e:E", property = "buyDate")//
		, @CocField(name = "设备单价", mode = "*:N v:S c:E e:E", property = "unitPrice")//
		, @CocField(name = "设备数量", mode = "*:N v:S c:E e:E", property = "number")//
		, @CocField(name = "总金额", mode = "*:N v:S c:E e:E", property = "totalPrice")//
		, @CocField(name = "附件单价", mode = "*:N v:S c:E e:E", property = "attachmentUnitPrice")//
		, @CocField(name = "附件数量", mode = "*:N v:S c:E e:E", property = "attachmentNumber")//
		, @CocField(name = "附件总额", mode = "*:N v:S c:E e:E", property = "attachmentTotalPrice")//
		, @CocField(name = "条码", mode = "*:N v:S c:E e:E", property = "barCode")//
		, @CocField(name = "合同编号", mode = "*:N v:S c:E e:E", property = "contractCode")//
		, @CocField(name = "发票单号", mode = "*:N v:S c:E e:E", property = "invoiceCode")//
		, @CocField(name = "入账编号", mode = "*:N v:S c:E e:E", property = "recordedCode")//
		, @CocField(name = "入账日期", mode = "*:N v:S c:E e:E", property = "recordedDate")//
		, @CocField(name = "已领用数量", mode = "*:N v:S c:E e:E", property = "usedNumber")//
		, @CocField(name = "未领用数量", mode = "*:N v:S c:E e:E", property = "unusedNumber")//
		, @CocField(name = "设备描述", mode = "*:N v:S c:E e:E", property = "desc") //
}), @CocGroup(name = "基本信息", code = "operation_log"//
, fields = {
//
		@CocField(name = "录入时间", mode = "*:N v:S", property = "created", pattern = "yyyy-MM-dd HH-mm-ss") //
		, @CocField(name = "录入帐号", mode = "*:N v:S", property = "createdBy") //
		, @CocField(name = "修改时间", mode = "*:N v:S", property = "updated", pattern = "yyyy-MM-dd HH-mm-ss") //
		, @CocField(name = "修改帐号", mode = "*:N v:S", property = "updatedBy") //
}) // end: CocGroup
}// end: groups
)
public class Assets extends NameEntity {

	// 资产分类
	@ManyToOne
	AssetCategory category;

	// 设备来源
	@ManyToOne
	AssetOrigin origin;

	// 设备规格
	@ManyToOne
	AssetSpecification specification;

	// 经费来源
	@ManyToOne
	AssetFundsOrigin fundsOrigin;

	// 设备型号
	@ManyToOne
	AssetModel model;

	// 设备用途
	@ManyToOne
	AssetPurpose purpose;

	// 计量单位
	@ManyToOne
	Uom uom;

	// 供货商
	@ManyToOne
	Supplier supplier;

	// 经办人
	@ManyToOne
	Employee dealPerson;

	// 存放地点
	@ManyToOne
	Address depositAddress;

	// 购置日期
	Date buyDate;

	// 设备单价
	Double unitPrice;

	// 设备数量
	Integer number;

	// 总金额 = 设备单价 * 设备数量
	Double totalPrice;

	// 附件单价
	Double attachmentUnitPrice;

	// 附件数量
	Integer attachmentNumber;

	// 附件总额 = 附件单价 * 附件数量
	Double attachmentTotalPrice;

	// 条码
	@Column(length = 128)
	String barCode;

	// 合同编号
	@Column(length = 128)
	String contractCode;

	// 发票单号
	@Column(length = 128)
	String invoiceCode;

	// 入账编号
	@Column(length = 128)
	String recordedCode;

	// 入账日期
	Date recordedDate;

	// 新购置：已领用数量
	Integer usedNumber;

	// 新购置：未领用数量
	Integer unusedNumber;

	// 退还：闲置数量
	Integer returnNumber;

	// 损坏：待维修数量
	Integer badNumber;
}
