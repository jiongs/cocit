package com.kmetop.demsy.comlib.impl.base.web;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_UDF_CONSOLE;
import static com.kmetop.demsy.comlib.LibConst.ORDER_UIUDF_THEME_STYLE;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.biz.field.FakeSubSystem;
import com.kmetop.demsy.comlib.ui.IStyle;
import com.kmetop.demsy.orm.ann.Prop;

@Entity
@CocTable(name = "主题样式", catalog = BIZCATA_UDF_CONSOLE, orderby = ORDER_UIUDF_THEME_STYLE//
, actions = { @CocOperation(name = "新增样式", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = {
//
		@CocField(property = "theme") //
		, @CocField(property = "style") //
		, @CocField(property = "catalog") //
		, @CocField(property = "created", name = "创建时间", mode = "*:N v:S") //
		, @CocField(property = "updated", name = "更新时间", mode = "*:N v:S") //
		, @CocField(property = "desc", name = "行内样式", gridField = false) //
		, @CocField(property = "detailState") //
		, @CocField(property = "usage") //
}), // end groups
		@CocGroup(name = "整体样式", code = "box"//
		, fields = { @CocField(property = "box") //
		}), // end groups
		@CocGroup(name = "顶部", code = "top"//
		, fields = { @CocField(property = "top") //
		}), // end groups
		@CocGroup(name = "顶部-链接", code = "topLink"//
		, fields = { @CocField(property = "topLink") //
		}), // end groups
		@CocGroup(name = "顶部-左", code = "topL"//
		, fields = { @CocField(property = "topL") //
		}), // end groups
		@CocGroup(name = "顶部-标题", code = "topT"//
		, fields = { @CocField(property = "topT") //
		}), // end groups
		@CocGroup(name = "顶部-右", code = "topR"//
		, fields = { @CocField(property = "topR") //
		}), // end groups
		@CocGroup(name = "内容", code = "data"//
		, fields = { @CocField(property = "data") //
		}), // end groups
		@CocGroup(name = "内容-链接", code = "dataLink"//
		, fields = { @CocField(property = "dataLink") //
		}), // end groups
		@CocGroup(name = "内容-条目", code = "item"//
		, fields = { @CocField(property = "item") //
		}), // end groups
		@CocGroup(name = "条目-左(上)", code = "itemL"//
		, fields = { @CocField(property = "itemL") //
		}), // end groups
		@CocGroup(name = "条目-标题", code = "itemT"//
		, fields = { @CocField(property = "itemT") //
		}), // end groups
		@CocGroup(name = "条目-右(下)", code = "itemR"//
		, fields = { @CocField(property = "itemR") //
		}), // end groups
		@CocGroup(name = "底部", code = "bottom"//
		, fields = { @CocField(property = "bottom") //
		}), // end groups
		@CocGroup(name = "底部-链接", code = "bottomLink"//
		, fields = { @CocField(property = "bottomLink") //
		}), // end groups
		@CocGroup(name = "底部-左", code = "bottomL"//
		, fields = { @CocField(property = "bottomL") //
		}), // end groups
		@CocGroup(name = "底部-标题", code = "bottomT"//
		, fields = { @CocField(property = "bottomT") //
		}), // end groups
		@CocGroup(name = "底部-右", code = "bottomR"//
		, fields = { @CocField(property = "bottomR") //
		}) // end groups
})
public class UiThemeStyle extends BaseStyle {
	@ManyToOne
	@CocField(name = "界面分类", groupBy = true, mode = "c:M e:M")
	@Prop("uiCatalog")
	protected UiCatalog catalog;

	@ManyToOne
	@CocField(name = "界面主题", mode = "c:M e:M", isChildTable = true, cascadeMode = "catalog:*:catalog")
	@Prop("uiTheme")
	protected UiCatalogTheme theme;

	@ManyToOne
	@CocField(name = "界面样式", mode = "c:M e:M", cascadeMode = "catalog:*:catalog")
	@Prop("uiStyle")
	protected UiCatalogStyle style;

	public UiCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(UiCatalog uiCatalog) {
		this.catalog = uiCatalog;
	}

	public UiCatalogTheme getTheme() {
		return theme;
	}

	public void setTheme(UiCatalogTheme uiTheme) {
		this.theme = uiTheme;
	}

	public UiCatalogStyle getStyle() {
		return style;
	}

	public void setStyle(UiCatalogStyle uiStyle) {
		this.style = uiStyle;
	}

	@Override
	public IStyle getParent() {
		return null;
	}

	@Override
	public FakeSubSystem<StyleItem> getItems() {
		return null;
	}
}
