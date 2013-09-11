package com.kmetop.demsy.comlib.impl.base.web;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_UDF_CONSOLE;
import static com.kmetop.demsy.comlib.LibConst.ORDER_UIUDF_CATALOG;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.impl.BizComponent;

@Entity
@CocTable(name = "界面分类", catalog = BIZCATA_UDF_CONSOLE, orderby = ORDER_UIUDF_CATALOG//
, actions = { @CocOperation(name = "新增分类", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(property = "name", name = "分类名称", mode = "c:M e:M")//
		, @CocField(property = "code", name = "分类编号") //
		, @CocField(property = "desc", name = "分类说明") //
		, @CocField(property = "created", name = "创建时间", mode = "*:P") //
		, @CocField(property = "updated", name = "更新时间", mode = "*:P") //
}) }// end groups
, jsonData = "UiCatalog.data.js"//
)
public class UiCatalog extends BizComponent {

	@OneToMany(mappedBy = "catalog")
	protected List<UiCatalogTheme> themes;

	@OneToMany(mappedBy = "catalog")
	protected List<UiCatalogStyle> styles;

	public List<UiCatalogTheme> getThemes() {
		return themes;
	}

	public void setThemes(List<UiCatalogTheme> themes) {
		this.themes = themes;
	}

	public List<UiCatalogStyle> getStyles() {
		return styles;
	}

	public void setStyles(List<UiCatalogStyle> styles) {
		this.styles = styles;
	}
}
