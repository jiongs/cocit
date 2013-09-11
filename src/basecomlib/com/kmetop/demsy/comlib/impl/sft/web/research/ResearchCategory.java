package com.kmetop.demsy.comlib.impl.sft.web.research;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_WEB;
import static com.kmetop.demsy.comlib.LibConst.ORDER_WEB_RESEARCH_CATALOG;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.impl.sft.SFTBizComponent;

@Entity
@CocTable(name = "网站调查分类", code = "_web_research_category", catalog = BIZCATA_WEB, orderby = ORDER_WEB_RESEARCH_CATALOG, buildin = true//
, actions = { @CocOperation(name = "添加分类", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(property = "name", name = "分类名称", mode = "c:M e:M *:N v:S")//
		, @CocField(property = "parent") //
		, @CocField(property = "orderby", name = "人工顺序", mode = "*:N v:S") //
		, @CocField(property = "desc", name = "分类描述", mode = "c:E e:E *:N v:S", gridField = false) //
		, @CocField(property = "created", name = "创建时间", mode = "*:N v:S", pattern = "yyyy-MM-dd HH:mm", gridField = false) //
		, @CocField(property = "createdBy", name = "创建帐号", mode = "*:N v:S", gridField = false) //
		, @CocField(property = "updated", name = "更新时间", mode = "*:N v:S", pattern = "yyyy-MM-dd HH:mm", gridField = false) //
		, @CocField(property = "updatedBy", name = "更新帐号", mode = "*:N v:S", gridField = false) //

}) //
}// end groups
)
public class ResearchCategory extends SFTBizComponent {

	@ManyToOne
	@CocField(name = "上级分类", mode = "c:E e:E *:N v:S")
	protected ResearchCategory parent;

	public ResearchCategory getParent() {
		return parent;
	}

	public void setParent(ResearchCategory parent) {
		this.parent = parent;
	}

}
