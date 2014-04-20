package com.jiongsoft.cocit.entity.asset;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.entity.asset.plugins.AssetPlugins;
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
@CocTable(name = "资产管理", code = "asset_detail", catalog = "_soft_base", pathPrefix = UrlAPI.URL_NS//
/*
 * 操作按钮
 */
, actions = {
//
		@CocOperation(name = "批量录入", typeCode = 9101, mode = "bc", template = "/asset/entryAssets.jsp", plugin = AssetPlugins.EntryAssets.class) //
		, @CocOperation(name = "录入", typeCode = 101, mode = "c") //
		, @CocOperation(name = "变更", typeCode = 102, mode = "e") //
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
		// @CocField(name = "设备名称", mode = "*:N v:S c:E e:E", property = "assets", gridOrder = 3),//
		@CocField(name = "设备名称", mode = "*:N v:S c:M e:M bc:M", property = "name", gridOrder = 1)//
		, @CocField(name = "设备编号", mode = "*:N v:S c:M e:M bc:M", property = "code", gridOrder = 2)//
		, @CocField(name = "资产类型", mode = "*:N v:S c:E e:E bc:E", property = "type", gridOrder = 4)//
		, @CocField(name = "资产分类", mode = "*:N v:S c:E e:E bc:E", property = "category", gridOrder = 5, uiTemplate = "combotree")//
		, @CocField(name = "设备来源", mode = "*:N v:S c:M e:M bc:M", property = "origin")//
		, @CocField(name = "设备规格", mode = "*:N v:S c:E e:E bc:E", property = "specification", disabledNavi = true)//
		, @CocField(name = "设备型号", mode = "*:N v:S c:E e:E bc:E", property = "model", disabledNavi = true)//
		, @CocField(name = "设备用途", mode = "*:N v:S c:M e:M bc:M", property = "purpose", disabledNavi = true)//
		, @CocField(name = "供货商", mode = "*:N v:S c:M e:M bc:M", property = "supplier")//
		, @CocField(name = "计量单位", mode = "*:N v:S c:E e:E bc:E", property = "uom", disabledNavi = true)//
		, @CocField(name = "购置日期", mode = "*:N v:S c:E e:E bc:E", property = "buyDate")//
		, @CocField(name = "使用部门", mode = "*:N v:S c:E e:E bc:E", property = "usedByDepartment", uiTemplate = "combotree")//
		, @CocField(name = "存放地点", mode = "*:N v:S c:M e:M bc:M", property = "depositAddress", uiTemplate = "combotree")//
		, @CocField(name = "领用人", mode = "*:N v:S c:E e:E bc:E", property = "usedByPerson", disabledNavi = true)//
		, @CocField(name = "使用状况", mode = "*:N v:S c:E e:E bc:E", property = "usingStatus", options = "1:在用,2:季节性停用,3:经营性出租,4:大修理停用,5:未使用,6:不需用,9:闲置")//
		// , @CocField(name = "运行状态", mode = "*:N v:S c:E e:E", property = "runningStatus", options = "0:正常,1:待维修,9:停用,99:报废")//
		, @CocField(name = "设备单价", mode = "*:N v:S c:E e:E bc:E", property = "unitPrice", disabledNavi = true)//
		, @CocField(name = "设备数量", mode = "*:N v:S c:E e:E bc:E", property = "number")//
		, @CocField(name = "总金额", mode = "*:N v:S c:E e:E bc:E", property = "totalPrice")//
		, @CocField(name = "折旧金额", mode = "*:N v:S c:E e:E bc:E", property = "depreciationPrice")//
		, @CocField(name = "附件单价", mode = "*:N v:S c:E e:E bc:E", property = "attachmentUnitPrice")//
		, @CocField(name = "附件数量", mode = "*:N v:S c:E e:E bc:E", property = "attachmentNumber")//
		, @CocField(name = "附件总额", mode = "*:N v:S c:E e:E bc:E", property = "attachmentTotalPrice")//
		, @CocField(name = "条码", mode = "*:N v:S c:E e:E bc:E", property = "barCode")//
		, @CocField(name = "设备描述", mode = "*:N v:S c:E e:E bc:E", property = "desc") //
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

	// // 关联资产实体
	// @ManyToOne
	// Assets assets;

	/*
	 * 以下为“关联资产实体”冗余字段
	 */
	// 资产分类
	@ManyToOne
	AssetCategory category;

	@ManyToOne
	AssetType type;

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
	Double unitPrice;

	// 附件单价
	Double attachmentUnitPrice;

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
	Integer usingStatus;

	// 运行状态
	Integer runningStatus;

	// 领用数量
	Integer number;

	// 总金额 = 设备单价 * 设备数量
	Double totalPrice;

	// 领用附件数量
	Integer attachmentNumber;

	// 附件总额 = 附件单价 * 附件数量
	Double attachmentTotalPrice;

	// 损坏数量
	Integer badNumber;

	// 损坏程度（%）
	Integer badLevel;

	// 折旧金额
	Double depreciationPrice;

	// 条码
	@Column(length = 128)
	String barCode;

	// public Assets getAssets() {
	// return assets;
	// }
	//
	// public void setAssets(Assets assets) {
	// this.assets = assets;
	// }

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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDepreciationPrice() {
		return depreciationPrice;
	}

	public void setDepreciationPrice(Double depreciationPrice) {
		this.depreciationPrice = depreciationPrice;
	}

	public Double getAttachmentUnitPrice() {
		return attachmentUnitPrice;
	}

	public void setAttachmentUnitPrice(Double attachmentUnitPrice) {
		this.attachmentUnitPrice = attachmentUnitPrice;
	}

	public Integer getAttachmentNumber() {
		return attachmentNumber;
	}

	public void setAttachmentNumber(Integer attachmentNumber) {
		this.attachmentNumber = attachmentNumber;
	}

	public Double getAttachmentTotalPrice() {
		return attachmentTotalPrice;
	}

	public void setAttachmentTotalPrice(Double attachmentTotalPrice) {
		this.attachmentTotalPrice = attachmentTotalPrice;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	// public Assets getAsset() {
	// return assets;
	// }
	//
	// public void setAsset(Assets assets) {
	// this.assets = assets;
	// }

	public Integer getUsingStatus() {
		return usingStatus;
	}

	public void setUsingStatus(Integer usingStatus) {
		this.usingStatus = usingStatus;
	}

	public Integer getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(Integer runningStatus) {
		this.runningStatus = runningStatus;
	}

	public AssetType getType() {
		return type;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

	public Integer getBadNumber() {
		return badNumber;
	}

	public void setBadNumber(Integer badNumber) {
		this.badNumber = badNumber;
	}

	public Integer getBadLevel() {
		return badLevel;
	}

	public void setBadLevel(Integer badLevel) {
		this.badLevel = badLevel;
	}

	public Asset clone() {
		Asset ret = new Asset();
		ret.attachmentNumber = this.attachmentNumber;
		ret.attachmentTotalPrice = this.attachmentTotalPrice;
		ret.attachmentUnitPrice = this.attachmentUnitPrice;
		ret.badLevel = this.badLevel;
		ret.badNumber = this.badLevel;
		ret.barCode = this.barCode;
		ret.buyDate = this.buyDate;
		ret.category = this.category;
		ret.code = this.code;
		ret.depositAddress = this.depositAddress;
		ret.depreciationPrice = this.depreciationPrice;
		ret.desc = this.desc;
		ret.model = this.model;
		ret.name = this.name;
		ret.number = this.number;
		ret.origin = this.origin;
		ret.purpose = this.purpose;
		ret.runningStatus = this.runningStatus;
		ret.softID = this.softID;
		ret.specification = this.specification;
		ret.supplier = this.supplier;
		ret.totalPrice = this.totalPrice;
		ret.type = this.type;
		ret.unitPrice = this.unitPrice;
		ret.uom = this.uom;
		ret.usedByDepartment = this.usedByDepartment;
		ret.usedByPerson = this.usedByPerson;
		ret.usingStatus = this.usingStatus;

		return ret;
	}

}
