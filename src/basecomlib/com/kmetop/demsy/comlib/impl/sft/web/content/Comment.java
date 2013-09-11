package com.kmetop.demsy.comlib.impl.sft.web.content;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT_N;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZ_DEL;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_WEB;
import static com.kmetop.demsy.comlib.LibConst.ORDER_WEB_COMMENT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.impl.base.security.Module;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;
import com.kmetop.demsy.orm.ann.Prop;

@Entity
@CocTable(name = "网站留言管理", code = "Comment", catalog = BIZCATA_WEB, orderby = ORDER_WEB_COMMENT, buildin = true//
, actions = { @CocOperation(name = "添加", typeCode = TYPE_BZFORM_NEW, mode = "c", disabled = true, info = "提交成功！审核通过后发布。", pluginName = "com.kmetop.demsy.plugins.web.SaveComment")//
		, @CocOperation(name = "审核", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu")//
		, @CocOperation(name = "回复", typeCode = TYPE_BZFORM_EDIT, mode = "e") //
		, @CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d") //
		, @CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = {
//
		@CocField(property = "module", gridOrder = 1) //
		, @CocField(property = "name", name = "主题", mode = "*:N v:S", gridOrder = 2) //
		, @CocField(property = "commenter") //
		, @CocField(property = "createdIP", gridOrder = 5) //
		, @CocField(property = "created", name = "时间", pattern = "yyyy-MM-dd hh:mm:ss", mode = "bu:N *:S", gridOrder = 6) //
		, @CocField(property = "status", gridOrder = 4) //
		, @CocField(property = "content", gridOrder = 3) //
		, @CocField(property = "replyContent") //
		, @CocField(property = "subjectID") //
}) //
}// end groups
)
public class Comment extends SFTBizComponent {
	public static final int STATUS_UNCHECKED = 0;

	public static final int STATUS_SHOWN = 2;

	public static final int STATUS_HIDDEN = 99;

	@CocField(name = "网友", mode = "c:E bu:N *:S")
	protected String commenter;// 评论员

	@Column(length = 2000)
	@CocField(name = "内容", mode = "c:M bu:N *:S")
	protected String content;// 内容

	protected Boolean hidden;

	@CocField(name = "状态", mode = "e:E bu:E *:S", options = "0:未审核,2:显示,99:屏蔽")
	@Prop("commentStatus")
	protected byte status;// 评论状态

	@Column(length = 32)
	@Prop("ipAddress")
	@CocField(name = "IP地址", mode = "bu:N *:S")
	protected String createdIP;// 远程IP地址

	@Column(length = 2000)
	@CocField(name = "回复内容", mode = "e:M bu:N *:S")
	protected String replyContent;// 回复

	@ManyToOne
	@CocField(name = "模块", disabledNavi = true, mode = "c:M bu:N *:S")
	protected Module module;

	@CocField(name = "主题", disabledNavi = true, mode = "c:M bu:N *:S")
	protected Long subjectID;

	@ManyToOne
	@CocField(name = "标题", disabledNavi = true, mode = "c:M bu:N *:S")
	protected WebContent webContent;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Byte getStatus() {
		return status;
	}

	public void setCommentStatus(Byte commentStatus) {
		this.status = commentStatus;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String ipAddress) {
		this.createdIP = ipAddress;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Long data) {
		this.subjectID = data;
	}

	public String getCommenter() {
		return commenter;
	}

	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	public WebContent getWebContent() {
		return webContent;
	}

}
