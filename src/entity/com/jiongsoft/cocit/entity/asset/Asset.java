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
import com.jiongsoft.cocit.entity.hr.Department;
import com.jiongsoft.cocit.entity.hr.Employee;
import com.jiongsoft.cocit.entity.impl.NameEntity;
import com.jiongsoft.cocit.util.UrlAPI;

/**
 * 设备/资产明细：用于描述单台资产使用状况
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "资产明细", code = "asset_detail", catalog = "_soft_base", pathPrefix = UrlAPI.URL_NS//
/*
 * 操作按钮
 */
, actions = {
//
		@CocOperation(name = "变更", typeCode = 102, mode = "e") //
		, @CocOperation(name = "拆分", typeCode = 102, mode = "e1") //
		, @CocOperation(name = "折旧", typeCode = 102, mode = "e2") //
		// , @CocOperation(name = "维修", typeCode = 102, mode = "e3") //
		, @CocOperation(name = "转移", typeCode = 102, mode = "e4") //
		// , @CocOperation(name = "调拨", typeCode = 102, mode = "e5") //
		, @CocOperation(name = "归还", typeCode = 102, mode = "e9") //
		, @CocOperation(name = "报废", typeCode = 102, mode = "e7") //
		, @CocOperation(name = "处置", typeCode = 102, mode = "e8") //
		// , @CocOperation(name = "删除", typeCode = 299, mode = "d") //
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
		@CocField(name = "设备名称", mode = "*:N v:S c:E e:E", property = "assets")//
		, @CocField(name = "资产分类", mode = "*:N v:S c:E e:E", property = "category", gridOrder = 1)//
		// , @CocField(name = "设备名称", mode = "*:N v:S c:M e:M", property = "name", gridOrder = 2)//
		, @CocField(name = "设备编号", mode = "*:N v:S c:M e:M", property = "code", gridOrder = 3)//
		, @CocField(name = "设备来源", mode = "*:N v:S c:M e:M", property = "origin")//
		, @CocField(name = "设备规格", mode = "*:N v:S c:M e:M", property = "specification")//
		, @CocField(name = "设备型号", mode = "*:N v:S c:M e:M", property = "model")//
		, @CocField(name = "设备用途", mode = "*:N v:S c:M e:M", property = "purpose")//
		, @CocField(name = "供货商", mode = "*:N v:S c:M e:M", property = "supplier")//
		, @CocField(name = "计量单位", mode = "*:N v:S c:M e:M", property = "uom")//
		, @CocField(name = "购置日期", mode = "*:N v:S c:M e:M", property = "buyDate")//
		, @CocField(name = "使用部门", mode = "*:N v:S c:M e:M", property = "usedByDepartment")//
		, @CocField(name = "存放地点", mode = "*:N v:S c:M e:M", property = "depositAddress")//
		, @CocField(name = "领用人", mode = "*:N v:S c:M e:M", property = "usedByPerson")//
		, @CocField(name = "使用状态", mode = "*:N v:S c:M e:M", property = "usingStatus", options = "0:领用,9:闲置")//
		, @CocField(name = "运行状态", mode = "*:N v:S c:M e:M", property = "runningStatus", options = "0:正常,1:待维修,9:停用,99:报废")//
		, @CocField(name = "设备单价", mode = "*:N v:S c:E e:E", property = "unitPrice")//
		, @CocField(name = "设备数量", mode = "*:N v:S c:M e:M", property = "number")//
		, @CocField(name = "总金额", mode = "*:N v:S c:M e:M", property = "totalPrice")//
		, @CocField(name = "折旧金额", mode = "*:N v:S c:E e:E", property = "depreciationPrice")//
		, @CocField(name = "附件单价", mode = "*:N v:S c:E e:E", property = "attachmentUnitPrice")//
		, @CocField(name = "附件数量", mode = "*:N v:S c:E e:E", property = "attachmentNumber")//
		, @CocField(name = "附件总额", mode = "*:N v:S c:E e:E", property = "attachmentTotalPrice")//
		, @CocField(name = "条码", mode = "*:N v:S c:E e:E", property = "barCode")//
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
public class Asset extends NameEntity {

	// 关联资产实体
	@ManyToOne
	Assets assets;

	/*
	 * 以下为“关联资产实体”冗余字段
	 */
	// 资产分类
	@ManyToOne
	AssetCategory category;

	// 设备来源
	@ManyToOne
	AssetOrigin origin;

	// 设备规格
	@ManyToOne
	AssetSpecification specification;

	// 供货商
	@ManyToOne
	Supplier supplier;

	// 设备型号
	@ManyToOne
	AssetModel model;

	// 设备用途
	@ManyToOne
	AssetPurpose purpose;

	// 计量单位
	@ManyToOne
	Uom uom;

	// 购置日期
	Date buyDate;

	// 设备单价
	double unitPrice;

	// 附件单价
	double attachmentUnitPrice;

	/*
	 * 以上为“关联资产实体”冗余字段
	 */

	/*
	 * 以下为资产使用情况说明相关字段
	 */

	// 存放地点
	@ManyToOne
	Address depositAddress;

	// 使用部门
	@ManyToOne
	Department usedByDepartment;

	// 领用人
	@ManyToOne
	Employee usedByPerson;

	// 使用状态
	int usingStatus;

	// 运行状态
	int runningStatus;

	// 领用数量
	int number;

	// 总金额 = 设备单价 * 设备数量
	double totalPrice;

	// 领用附件数量
	int attachmentNumber;

	// 附件总额 = 附件单价 * 附件数量
	double attachmentTotalPrice;

	// 损坏数量
	int badNumber;

	// 损坏程度（%）
	int badLevel;

	// 折旧金额
	double depreciationPrice;

	// 条码
	@Column(length = 128)
	String barCode;

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public AssetCategory getCategory() {
		return category;
	}

	public void setCategory(AssetCategory category) {
		this.category = category;
	}

	public AssetOrigin getOrigin() {
		return origin;
	}

	public void setOrigin(AssetOrigin origin) {
		this.origin = origin;
	}

	public AssetSpecification getSpecification() {
		return specification;
	}

	public void setSpecification(AssetSpecification specification) {
		this.specification = specification;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public AssetModel getModel() {
		return model;
	}

	public void setModel(AssetModel model) {
		this.model = model;
	}

	public AssetPurpose getPurpose() {
		return purpose;
	}

	public void setPurpose(AssetPurpose purpose) {
		this.purpose = purpose;
	}

	public Address getDepositAddress() {
		return depositAddress;
	}

	public void setDepositAddress(Address depositAddress) {
		this.depositAddress = depositAddress;
	}

	public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	public Department getUsedByDepartment() {
		return usedByDepartment;
	}

	public void setUsedByDepartment(Department usedByDepartment) {
		this.usedByDepartment = usedByDepartment;
	}

	public Employee getUsedByPerson() {
		return usedByPerson;
	}

	public void setUsedByPerson(Employee usedByPerson) {
		this.usedByPerson = usedByPerson;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getDepreciationPrice() {
		return depreciationPrice;
	}

	public void setDepreciationPrice(double depreciationPrice) {
		this.depreciationPrice = depreciationPrice;
	}

	public double getAttachmentUnitPrice() {
		return attachmentUnitPrice;
	}

	public void setAttachmentUnitPrice(double attachmentUnitPrice) {
		this.attachmentUnitPrice = attachmentUnitPrice;
	}

	public int getAttachmentNumber() {
		return attachmentNumber;
	}

	public void setAttachmentNumber(int attachmentNumber) {
		this.attachmentNumber = attachmentNumber;
	}

	public double getAttachmentTotalPrice() {
		return attachmentTotalPrice;
	}

	public void setAttachmentTotalPrice(double attachmentTotalPrice) {
		this.attachmentTotalPrice = attachmentTotalPrice;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Assets getAsset() {
		return assets;
	}

	public void setAsset(Assets assets) {
		this.assets = assets;
	}

	public int getUsingStatus() {
		return usingStatus;
	}

	public void setUsingStatus(int usingStatus) {
		this.usingStatus = usingStatus;
	}

	public int getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(int runningStatus) {
		this.runningStatus = runningStatus;
	}

}
