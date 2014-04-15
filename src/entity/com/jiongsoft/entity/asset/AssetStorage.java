package com.jiongsoft.entity.asset;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.entity.impl.NameEntity;
import com.jiongsoft.cocit.util.UrlAPI;
import com.jiongsoft.entity.hr.EmployeeEntity;
import com.jiongsoft.entity.hr.SupplierEntity;

/**
 * 设备/资产采购管理
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "设备库存管理", code = "asset_storage", catalog = "_soft_base", pathPrefix = UrlAPI.URL_NS//
/*
 * 操作按钮
 */
, actions = {
//
		@CocOperation(name = "添加", typeCode = 101, mode = "c")//
		, @CocOperation(name = "修改", typeCode = 102, mode = "e") //
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
		@CocField(name = "资产分类", mode = "*:N v:S c:E e:E", property = "category", gridOrder = 1)//
		, @CocField(name = "设备名称", mode = "*:N v:S c:M e:M", property = "name", gridOrder = 2)//
		, @CocField(name = "设备编号", mode = "*:N v:S c:M e:M", property = "code", gridOrder = 3)//
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
public class AssetStorage extends NameEntity {

	// 设备来源
	@ManyToOne
	AssetOrigin origin;

	// 经费来源
	@ManyToOne
	AssetFundsOrigin fundsOrigin;

	// 供货商
	@ManyToOne
	SupplierEntity supplier;

	// 经办人
	@ManyToOne
	EmployeeEntity dealPerson;

	// 采购用途
	@ManyToOne
	AssetPurpose purpose;
	
	//入库类型：采购，退回
	int enterType;

	// 设备数量
	int number;

	// 总金额
	double totalPrice;

	// 附件数量
	int attachmentNumber;

	//
	// // 附件总金额
	// double attachmentTotalPrice;

	// 购置日期
	Date buyDate;

	// 合同编号
	@Column(length = 128)
	String contractCode;

	// 发票单号
	@Column(length = 128)
	String invoiceCode;

	// 入账编号
	@Column(length = 128)
	String recordedCode;
	
	//入账日期
	Date recordedDate;

}
