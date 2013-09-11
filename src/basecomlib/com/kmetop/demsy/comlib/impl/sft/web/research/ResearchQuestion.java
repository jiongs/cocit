package com.kmetop.demsy.comlib.impl.sft.web.research;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_WEB;
import static com.kmetop.demsy.comlib.LibConst.ORDER_WEB_RESEARCH;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;
import com.kmetop.demsy.comlib.web.IResearchQuestion;
import com.kmetop.demsy.comlib.web.IResearchSubject;
import com.kmetop.demsy.orm.ann.Prop;

@Entity
@CocTable(name = "问题列表", code = IResearchQuestion.SYS_CODE, catalog = BIZCATA_WEB, orderby = ORDER_WEB_RESEARCH, buildin = true//
, actions = {
// @CocOperation(name = "添加问题", typeCode = TYPE_BZFORM_NEW, mode = "c", plugin =
// "com.kmetop.demsy.plugins.web.SaveResearchQuestion")//
// , @CocOperation(name = "编辑", typeCode = TYPE_BZFORM_EDIT, mode = "e", plugin =
// "com.kmetop.demsy.plugins.web.SaveResearchQuestion") //
// , @CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d") //
@CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
// , @CocOperation(jsonData = "CommonBizAction_orderby.data.js") //
}, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(property = "subject") //
		, @CocField(property = "name", name = "问题名称", mode = "c:M e:M *:N v:S")//
		, @CocField(property = "type") //
		, @CocField(property = "mustable") //
		, @CocField(property = "result") //
		, @CocField(property = "optionsJson", gridField = false) //
		, @CocField(property = "orderby", name = "人工顺序", mode = "*:N v:S") //
		// , @CocField(property = "desc", name = "问题描述", mode = "c:E e:E *:N v:S",
		// gridField = false) //
		, @CocField(property = "created", name = "创建时间", mode = "*:N v:S", pattern = "yyyy-MM-dd HH:mm", gridField = false) //
		, @CocField(property = "createdBy", name = "创建帐号", mode = "*:N v:S", gridField = false) //
		, @CocField(property = "updated", name = "更新时间", mode = "*:N v:S", pattern = "yyyy-MM-dd HH:mm", gridField = false) //
		, @CocField(property = "updatedBy", name = "更新帐号", mode = "*:N v:S", gridField = false) //

}) //
}// end groups
)
public class ResearchQuestion extends SFTBizComponent implements IResearchQuestion {
	@Transient
	private List<ResearchAnswerOption> options;

	@ManyToOne
	@CocField(name = "调查主题", mode = "c:M e:M *:N v:S", isFkChild = true)
	private ResearchSubject subject;

	@Prop("allowMultiple")
	@CocField(name = "问题类型", mode = "c:M e:M *:N v:S", options = "0:单选,1:多选,2:问卷")
	private byte type;
	
	@CocField(name = "是否必填", mode = "c:M e:M *:N v:S", options = "0:可选,1:必填")
	private byte mustable;

	@CocField(name = "回答次数", mode = "*:N v:S")
	private Long result;

	@Transient
	@Column(length = 512)
	@CocField(name = "选择项", mode = "c:E e:E *:N v:S", isTransient = true)
	private String optionsJson;

	public ResearchSubject getSubject() {
		return subject;
	}

	public void setSubject(IResearchSubject subject) {
		this.subject = (ResearchSubject) subject;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte selectPolicy) {
		this.type = selectPolicy;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long selectedTimes) {
		this.result = selectedTimes;
	}

	public String getOptionsJson() {
		return optionsJson;
	}

	public void setOptionsJson(String optionText) {
		this.optionsJson = optionText;
	}

	public List getOptions() {
		return options;
	}

	public void setOptions(List options) {
		this.options = options;
	}

	public byte getMustable() {
		return mustable;
	}

	public void setMustable(byte mustable) {
		this.mustable = mustable;
	}
}
