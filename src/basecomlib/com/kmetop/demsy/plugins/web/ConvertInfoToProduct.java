package com.kmetop.demsy.plugins.web;

import java.util.List;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.plugin.BasePlugin;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.impl.base.ebusiness.product.Product;
import com.kmetop.demsy.comlib.impl.sft.web.content.WebContent;
import com.kmetop.demsy.orm.IOrm;

public class ConvertInfoToProduct extends BasePlugin {

	@Override
	public void before(ActionEvent event) {
	}

	@Override
	public void after(ActionEvent event) {
	}

	@Override
	public void loaded(ActionEvent event) {
		IOrm orm = Demsy.orm();

		List<WebContent> list = (List<WebContent>) event.getReturnValue();
		for (WebContent info : list) {
			Product p = (Product) orm.load(Product.class, Expr.eq("name", info.getName()));
			if (p == null) {
				// p = new Product();
				// p.setImage(info.getImage());
				// p.setContent(info.getContent());
				// p.setName(info.getName());
				continue;
			}

			p.setClickNum(info.getClickNum());

			orm.save(p);
		}
	}

}
