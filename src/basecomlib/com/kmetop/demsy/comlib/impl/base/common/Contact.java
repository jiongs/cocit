package com.kmetop.demsy.comlib.impl.base.common;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZ_DEL;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_BASE;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.common.IContact;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "联系人信息", code = IContact.SYS_CODE, catalog = BIZCATA_BASE,//
actions = {
//
		@CocOperation(name = "添加", typeCode = TYPE_BZFORM_NEW, mode = "c", plugin = "com.kmetop.demsy.plugins.common.SaveContact")//
		, @CocOperation(name = "修改", typeCode = TYPE_BZFORM_EDIT, mode = "e", plugin = "com.kmetop.demsy.plugins.common.SaveContact") //
		, @CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
		, @CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d") //
},//
groups = { //
@CocGroup(name = "基本信息", code = "basic",//
fields = { @CocField(property = "createdBy", mode = "*:N v:P", name = "登录帐号", gridOrder = 1, privacy = true) //
		, @CocField(property = "name", mode = "c:M e:M", name = "真实姓名", gridOrder = 2, privacy = true)//
		, @CocField(property = "code", mode = "c:M e:M", name = "联系电话", gridOrder = 3, privacy = true)//
		, @CocField(property = "province", gridOrder = 4)//
		, @CocField(property = "city", gridOrder = 5)//
		, @CocField(property = "area", gridOrder = 6)//
		, @CocField(property = "desc", mode = "c:M e:M", name = "街道地址", gridOrder = 7, privacy = true) //
		, @CocField(property = "postcode", gridOrder = 8) //
}),//
		@CocGroup(name = "其他信息", code = "other",//
		fields = { @CocField(name = "创建时间", property = "created", mode = "*:N v:P") //
				, @CocField(name = "修改时间", property = "created", mode = "*:N v:P") //
				, @CocField(property = "createdIP", privacy = true) //
		}) // @BzGrp
}// end groups
)
public class Contact extends BizComponent implements IContact {
	/*
	 * 收货地址
	 */
	@Column(length = 64)
	@CocField(name = "省", mode = "c:M e:M")
	protected String province;

	@Column(length = 64)
	@CocField(name = "市", mode = "c:M e:M")
	protected String city;

	@Column(length = 64)
	@CocField(name = "区", mode = "c:M e:M")
	protected String area;

	@Column(length = 15)
	@CocField(name = "邮政编码", mode = "c:M e:M")
	protected String postcode;

	@CocField(name = "默认地址", mode = "*:N v:P")
	protected Boolean defaults;

	@Column(length = 64)
	@CocField(name = "IP地址", mode = "*:N v:P")
	protected String createdIP;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	public Boolean getDefaults() {
		return defaults;
	}

	public void setDefaults(Boolean defaults) {
		this.defaults = defaults;
	}

	@Override
	public String getStreet() {
		return desc;
	}

	@Override
	public String getPerson() {
		return name;
	}

	@Override
	public String getTelcode() {
		return code;
	}

	@Override
	public void setStreet(String street) {
		this.desc = street;
	}

	@Override
	public void setPerson(String person) {
		this.name = person;
	}

	@Override
	public void setTelcode(String telcode) {
		this.code = telcode;
	}

}
