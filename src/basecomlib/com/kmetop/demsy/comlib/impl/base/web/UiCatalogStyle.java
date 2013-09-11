package com.kmetop.demsy.comlib.impl.base.web;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZ_DEL;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_UDF_CONSOLE;
import static com.kmetop.demsy.comlib.LibConst.BIZSYS_UIUDF_STYLE;
import static com.kmetop.demsy.comlib.LibConst.ORDER_UIUDF_CATALOG_STYLE;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.orm.ann.Prop;

@Entity
@CocTable(name = "样式管理", code = BIZSYS_UIUDF_STYLE, catalog = BIZCATA_UDF_CONSOLE, orderby = ORDER_UIUDF_CATALOG_STYLE//
, layout = 1, actions = {
		@CocOperation(name = "新增样式", typeCode = TYPE_BZFORM_NEW, mode = "c", plugin = "com.kmetop.demsy.plugins.web.SaveCatalogStyle")//
		// , @CocOperation(name = "批量修改", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu")//
		,
		@CocOperation(name = "编辑", typeCode = TYPE_BZFORM_EDIT, mode = "e", plugin = "com.kmetop.demsy.plugins.web.SaveCatalogStyle") //
		, @CocOperation(name = "删除", typeCode = TYPE_BZ_DEL, mode = "d") //
		, @CocOperation(name = "查看", typeCode = TYPE_BZFORM_EDIT, mode = "v") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = { @CocField(property = "catalog") //
		, @CocField(property = "parent") //
		, @CocField(property = "name", name = "样式名称", mode = "c:M e:M", gridOrder = 1)//
		, @CocField(property = "code", name = "样式编号", mode = "c:E e:E")//
		, @CocField(property = "created", name = "创建时间", mode = "*:N v:S") //
		, @CocField(property = "updated", name = "更新时间", mode = "*:N v:S") //
		, @CocField(property = "detailState") //
		, @CocField(property = "usage") //
}), // end groups
		@CocGroup(name = "样式编辑器", code = "cssDesigner"//
		, fields = { @CocField(property = "items") //
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
//
})
public class UiCatalogStyle extends BaseStyle {

	@ManyToOne
	@CocField(name = "界面分类", disabledNavi = true, groupBy = true, mode = "c:M e:M")
	@Prop("uiCatalog")
	protected UiCatalog catalog;

	@ManyToOne
	@CocField(name = "上级样式", mode = "bu:E")
	protected UiCatalogStyle parent;

	@OneToMany(mappedBy = "parent")
	protected List<UiCatalogStyle> children;

	public UiCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(UiCatalog uiCatalog) {
		this.catalog = uiCatalog;
	}

	public UiCatalogStyle getParent() {
		return parent;
	}

	public void setParent(UiCatalogStyle parent) {
		this.parent = parent;
	}

	public List<UiCatalogStyle> getChildren() {
		return children;
	}

	public void setChildren(List<UiCatalogStyle> children) {
		this.children = children;
	}

}
