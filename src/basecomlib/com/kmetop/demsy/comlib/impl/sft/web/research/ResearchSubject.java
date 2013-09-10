package com.kmetop.demsy.comlib.impl.sft.web.research;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZ_DEL;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_WEB;
import static com.kmetop.demsy.comlib.LibConst.ORDER_WEB_RESEARCH;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;
import com.kmetop.demsy.comlib.web.IResearchQuestion;
import com.kmetop.demsy.comlib.web.IResearchSubject;

@Entity
@CocTable(name = "网站调查管理", code = IResearchSubject.SYS_CODE, catalog = BIZCATA_WEB, orderby = ORDER_WEB_RESEARCH, buildin = true//
, actions = {
		@CocOperation(name = "添加调查", typeCode = TYPE_BZFORM_NEW, mode = "c", plugin = "com.kmetop.demsy.plugins.web.SaveResearchSubject")//
		,
		@CocOperation(name = "编辑", typeCode = TYPE_BZFORM_EDIT, mode = "e", plugin = "com.kmetop.demsy.plugins.web.SaveResearchSubject") //
		,
		@CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d") //
		,
		@CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v", plugin = "com.kmetop.demsy.plugins.web.LoadResearchSubject") //
		, @CocOperation(jsonData = "CommonBizAction_orderby.data.js") //
}, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = {
		@CocField(property = "category") //
		,
		@CocField(property = "name", name = "主题名称", mode = "c:M e:M *:N v:S")//
		,
		@CocField(property = "entryPolicy") //
		,
		@CocField(property = "entryTimes") //
		,
		@CocField(property = "viewPolicy") //
		,
		@CocField(property = "expiredFrom") //
		,
		@CocField(property = "expiredTo") //
		,
		@CocField(property = "image") //
		,
		@CocField(property = "result") //
		,
		@CocField(property = "questionsJson") //
		,
		@CocField(property = "orderby", name = "人工顺序", mode = "*:N v:S") //
		// , @BzFld(property = "desc", name = "调查描述", mode = "c:E e:E *:N v:S",
		// gridField = false) //
		,
		@CocField(property = "created", name = "创建时间", mode = "*:N v:S", pattern = "yyyy-MM-dd HH:mm", gridField = false) //
		,
		@CocField(property = "createdBy", name = "创建帐号", mode = "*:N v:S", gridField = false) //
		,
		@CocField(property = "updated", name = "更新时间", mode = "*:N v:S", pattern = "yyyy-MM-dd HH:mm", gridField = false) //
		, @CocField(property = "updatedBy", name = "更新帐号", mode = "*:N v:S", gridField = false) //

}) //
}// end groups
)
public class ResearchSubject extends SFTBizComponent implements IResearchSubject {

	@Transient
	private List<IResearchQuestion> questions;

	@ManyToOne
	@CocField(name = "调查分类", mode = "c:E e:E *:N v:S")
	private ResearchCategory category;

	@CocField(name = "参与策略", mode = "c:E e:E *:N v:S", options = "0:匿名参与,1:登录参与")
	private byte entryPolicy;

	@CocField(name = "限制次数", mode = "c:E e:E *:N v:S", desc = "限制每人最多只能参与多少次调查？")
	private byte entryTimes;

	@CocField(name = "查看策略", mode = "c:E e:E *:N v:S", options = "0:禁止查看,1:允许查看,2:登录查看,3:管理员查看,4:超级管理员查看")
	private byte viewPolicy = 1;

	@CocField(name = "开始时间", mode = "c:E e:E *:N v:S", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expiredFrom;

	@CocField(name = "截止时间", mode = "c:E e:E *:N v:S", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expiredTo;

	@CocField(name = "链接图片", mode = "c:E e:E *:N v:S", uploadType = "*.bmp;*.jpg;*.gif;*.png;*.swf", gridField = false)
	protected Upload image;

	@CocField(name = "参与次数", mode = "*:N v:S")
	private Long result;

	// 多个问题换行分隔
	@Transient
	@Column(length = 512)
	@CocField(name = "问题选项", mode = "c:E e:E *:N v:S", isTransient = true, gridField = false, uiTemplate = "ui.json.ResearchQuestionJsonSubSystem")
	private String questionsJson;

	public ResearchCategory getCategory() {
		return category;
	}

	public void setCategory(ResearchCategory category) {
		this.category = category;
	}

	public byte getEntryPolicy() {
		return entryPolicy;
	}

	public void setEntryPolicy(byte entryPolicy) {
		this.entryPolicy = entryPolicy;
	}

	public byte getEntryTimes() {
		return entryTimes;
	}

	public void setEntryTimes(byte entryTimes) {
		this.entryTimes = entryTimes;
	}

	public byte getViewPolicy() {
		return viewPolicy;
	}

	public void setViewPolicy(byte viewPolicy) {
		this.viewPolicy = viewPolicy;
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

	public String getQuestionsJson() {
		return questionsJson;
	}

	public void setQuestionsJson(String questions) {
		this.questionsJson = questions;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public List<IResearchQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<IResearchQuestion> questions) {
		this.questions = questions;
	}

	public Upload getImage() {
		return image;
	}

	public void setImage(Upload image) {
		this.image = image;
	}
}
