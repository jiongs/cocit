package com.jiongsoft.ynby.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.util.UrlAPI;
import com.kmetop.demsy.comlib.impl.sft.SFTBizEntity;

/**
 * 抵用卷
 * 
 * @author yongshan.ji
 * 
 */
@Entity
@CocTable(name = "抵用卷管理", code = "Voucher", catalog = "_catalog_ebusiness", pathPrefix = UrlAPI.URL_NS, orderby = 9
//
// , sortExpr = "grid:name desc, tree:name desc"
// 操作按钮
, actions = {
//
		@CocOperation(name = "添加", typeCode = 101, mode = "c")//
		, @CocOperation(name = "修改", typeCode = 102, mode = "e") //
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
// 业务字段
, fields = { @CocField(name = "抵用卷编码", mode = "*:N v:S c:M e:M", property = "code", gridOrder = 1) //
		, @CocField(name = "抵用卷金额", mode = "*:N v:S c:M e:M", property = "price", pattern = "#,##0.00", gridOrder = 4)//
		, @CocField(name = "有效起始时间", mode = "*:N v:S c:E e:E", property = "expiredFrom", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 5)//
		, @CocField(name = "有效截止时间", mode = "*:N v:S c:M e:M", property = "expiredTo", pattern = "yyyy-MM-dd HH:mm:ss", gridOrder = 6) //
		, @CocField(name = "抵用卷状态", mode = "*:N v:S", property = "statusCode",options = "0:新建,1:已被使用", gridOrder = 9) //
}// end: fields
) // end: CocGroup
}// end: groups
)
public class Voucher extends SFTBizEntity {

	// 抵用卷编码
	String code;

	// 抵用卷有效期
	Date expiredFrom;

	// 抵用卷有效截止日期
	Date expiredTo;

	// 抵用卷金额
	Double price;

	// 抵用卷状态：0——新建 1——已被使用
	int statusCode;

	public boolean isExpired() {
		Date now = new Date();
		if (expiredTo != null && expiredFrom != null) {
			return expiredTo.getTime() < now.getTime() || expiredFrom.getTime() > now.getTime();
		}

		if (expiredTo != null)
			return expiredTo.getTime() < now.getTime();

		if (expiredFrom != null)
			return expiredFrom.getTime() > now.getTime();

		return false;
	}

	public Date getExpiredFrom() {
		return expiredFrom;
	}

	public void setExpiredFrom(Date expiredFrom) {
		this.expiredFrom = expiredFrom;
	}

	public Date getExpiredTo() {
		return expiredTo;
	}

	public void setExpiredTo(Date expiredTo) {
		this.expiredTo = expiredTo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int status) {
		this.statusCode = status;
	}
}
