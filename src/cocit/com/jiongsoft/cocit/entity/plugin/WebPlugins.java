package com.jiongsoft.cocit.entity.plugin;

import java.util.List;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.WebCatalogEntity;
import com.jiongsoft.cocit.entity.WebContentEntity;
import com.jiongsoft.cocit.orm.Orm;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.jiongsoft.cocit.util.CocException;
import com.jiongsoft.cocit.util.StringUtil;

public abstract class WebPlugins {
	public static class SaveWebCatalog extends BasePlugin<WebCatalogEntity> {
		/*
		 * 检查栏目编号的唯一性
		 */

		@Override
		public void before(ActionEvent<WebCatalogEntity> event) {
			synchronized (SaveWebCatalog.class) {
				Orm orm = event.getOrm();

				WebCatalogEntity entity = event.getEntity();

				String catalogCode = entity.getCode();
				if (!StringUtil.isNil(catalogCode)) {
					WebCatalogEntity existedCatalog = (WebCatalogEntity) orm.get(entity.getClass(), Expr.eq("code", catalogCode));
					if (existedCatalog != null && catalogCode.equals(existedCatalog.getCode())) {
						if (entity.getId() != existedCatalog.getId()) {
							throw new CocException("栏目编码已经被占用！");
						}
					}
				}
			}
		}
	}

	public static class SaveWebContent extends BasePlugin<WebContentEntity> {

		@Override
		public void before(ActionEvent<WebContentEntity> event) {
			Orm orm = event.getOrm();

			WebContentEntity entity = event.getEntity();

			/*
			 * 获取栏目编码
			 */
			WebCatalogEntity catalog = entity.getCatalog();
			String catalogCode = catalog.getCode();
			if (StringUtil.isNil(catalogCode)) {
				catalog = (WebCatalogEntity) orm.load(catalog.getClass(), catalog.getId());
				catalogCode = catalog.getCode();
			}

			/**
			 * 设置栏目编码（冗余字段）
			 */
			entity.setCatalogCode(catalogCode);
		}
	}

	public static class SaveWebContentList extends BasePlugin<List<WebContentEntity>> {

		@Override
		public void before(ActionEvent<List<WebContentEntity>> event) {
			Orm orm = event.getOrm();

			WebCatalogEntity catalog = null;
			String catalogCode = "";

			List<WebContentEntity> list = event.getEntity();
			for (WebContentEntity entity : list) {

				/*
				 * 获取栏目编码
				 */
				if (catalog == null) {
					catalog = entity.getCatalog();
					catalogCode = catalog.getCode();
					if (StringUtil.isNil(catalogCode)) {
						catalog = (WebCatalogEntity) orm.load(catalog.getClass(), catalog.getId());
						catalogCode = catalog.getCode();
					}
				}

				/**
				 * 设置栏目编码（冗余字段）
				 */
				entity.setCatalogCode(catalogCode);
			}
		}
	}
}
