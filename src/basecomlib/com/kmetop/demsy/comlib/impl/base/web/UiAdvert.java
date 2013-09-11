package com.kmetop.demsy.comlib.impl.base.web;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_WEB;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_WEB_ADVERT;
import static com.kmetop.demsy.comlib.LibConst.ORDER_WEB_ADVERT;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "网站广告管理", code = BIZSYS_WEB_ADVERT, catalog = BIZCATA_WEB, orderby = ORDER_WEB_ADVERT//
, actions = {
//
		@CocOperation(name = "添加", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = {
//
		@CocField(property = "name", name = "广告名称", mode = "c:M e:M")//
		, @CocField(property = "priority") //
		, @CocField(property = "keywords") //
		, @CocField(property = "clickNum") //
		, @CocField(property = "linkNum") //
		, @CocField(property = "linkPath") //
		, @CocField(property = "linkTarget") //
		, @CocField(property = "image") //
		, @CocField(property = "disabled", name = "状态", disabledNavi = true, options = "1:停用,0:启用")//
		, @CocField(property = "created", name = "创建时间", mode = "*:N v:S") //
		, @CocField(property = "updated", name = "更新时间", mode = "*:N v:S") //
}) }// end groups
)
public class UiAdvert extends BizComponent {

	@Column(length = 128)
	@CocField(name = "链接地址")
	protected String linkPath;

	@Column(length = 16)
	@CocField(name = "链接目标", disabledNavi = true, gridField = false, options = "_blank:新窗口,_parent:父窗口")
	protected String linkTarget;

	@CocField(name = "广告文件", uploadType = "*.bmp;*.jpg;*.gif;*.png;*.swf;*.flv", mode = "c:M e:M")
	protected Upload image;

	@CocField(name = "优先级", disabledNavi = true, options = "1,2,3,4,5,6,7,8,9")
	protected Integer priority;

	@Column(length = 256)
	@CocField(name = "关键字")
	protected String keywords;

	@CocField(name = "查看次数", mode = "*:N v:S")
	protected Integer clickNum;

	@CocField(name = "链接次数", mode = "*:N v:S")
	protected Integer linkNum;

	public String getLinkPath() {
		return linkPath;
	}

	public String getLinkTarget() {
		return linkTarget;
	}

	public Integer getPriority() {
		return priority;
	}

	public String getKeywords() {
		return keywords;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setLinkPath(String linkPath) {
		this.linkPath = linkPath;
	}

	public void setLinkTarget(String linkTarget) {
		this.linkTarget = linkTarget;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Upload getImage() {
		return image;
	}

	public void setImage(Upload image) {
		this.image = image;
	}

	public Integer getLinkNum() {
		return linkNum;
	}

	public void setLinkNum(Integer linkNum) {
		this.linkNum = linkNum;
	}

}
