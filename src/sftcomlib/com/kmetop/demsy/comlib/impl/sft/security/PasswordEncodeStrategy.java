package com.kmetop.demsy.comlib.impl.sft.security;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_DEMSY_ADMIN;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_DEMSY_LIB_ENCODER;
import static com.kmetop.demsy.comlib.LibConst.ORDER_DEMSY_LIB_ENCODER;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.entity.IEncryption;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

@Entity
@CocTable(name = "加密策略组件库", code = BIZSYS_DEMSY_LIB_ENCODER, catalog = BIZCATA_DEMSY_ADMIN, orderby = ORDER_DEMSY_LIB_ENCODER, buildin = true//
, actions = { @CocOperation(name = "新增", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(name = "名称", property = "name", mode = "c:M e:M")//
		, @CocField(name = "编号", property = "code") //
		, @CocField(property = "encodeClass") //
		, @CocField(name = "描述", property = "desc") //
}) }// end groups
)
public class PasswordEncodeStrategy extends SFTBizComponent implements IEncryption {

	@ManyToOne
	private PasswordEncodeStrategy parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<PasswordEncodeStrategy> children;

	@CocField(name = "加密类")
	private String encodeClass;

	public PasswordEncodeStrategy getParent() {
		return parent;
	}

	public void setParent(PasswordEncodeStrategy parent) {
		this.parent = parent;
	}

	public List<PasswordEncodeStrategy> getChildren() {
		return children;
	}

	public void setChildren(List<PasswordEncodeStrategy> children) {
		this.children = children;
	}

	public String getEncodeClass() {
		return encodeClass;
	}

	public void setEncodeClass(String encodeClass) {
		this.encodeClass = encodeClass;
	}

}
