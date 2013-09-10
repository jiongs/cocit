package com.kmetop.demsy.comlib.impl.base.web;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_UDF_CONSOLE;
import static com.kmetop.demsy.comlib.LibConst.ORDER_UIUDF_CATALOG_THEME;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.impl.BizComponent;
import com.kmetop.demsy.orm.ann.Prop;

@Entity
@CocTable(name = "主题管理", catalog = BIZCATA_UDF_CONSOLE, orderby = ORDER_UIUDF_CATALOG_THEME//
, actions = { @CocOperation(name = "新增主题", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(property = "name", name = "主题名称", mode = "c:M e:M")//
		, @CocField(property = "code", name = "主题编号", mode = "c:E e:E")//
		, @CocField(property = "catalog") //
		, @CocField(property = "parent") //
		, @CocField(property = "desc", gridField = false, name = "全局样式文本") //
		, @CocField(property = "created", name = "创建时间", mode = "*:P") //
		, @CocField(property = "updated", name = "更新时间", mode = "*:P") //
}) }// end groups
)
public class UiCatalogTheme extends BizComponent {

	@ManyToOne
	@CocField(name = "界面分类", groupBy = true, mode = "c:M e:M")
	@Prop("uiCatalog")
	protected UiCatalog catalog;

	@ManyToOne
	@CocField(name = "上级主题", cascadeMode = "catalog:*:catalog")
	protected UiCatalogTheme parent;

	@CocField(name = "默认风格")
	protected boolean defaults;

	@OneToMany(mappedBy = "parent")
	protected List<UiCatalogTheme> children;

	public UiCatalog getCatalog() {
		return catalog;
	}

	public UiCatalogTheme getParent() {
		return parent;
	}

	public boolean isDefaults() {
		return defaults;
	}

	public void setCatalog(UiCatalog uiCatalog) {
		this.catalog = uiCatalog;
	}

	public void setParent(UiCatalogTheme parent) {
		this.parent = parent;
	}

	public void setDefaults(boolean defaults) {
		this.defaults = defaults;
	}

	public List<UiCatalogTheme> getChildren() {
		return children;
	}

	public void setChildren(List<UiCatalogTheme> children) {
		this.children = children;
	}
}
