package com.kmetop.demsy.comlib.biz.field;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.util.StringUtil;
import com.kmetop.demsy.lang.Str;

@CocField(columnDefinition = "text")
public class RichText implements IExtField {
	private String text;

	public RichText() {
		this("");
	}

	public RichText(String t) {
		this.text = t;
	}

	public String toString() {
		if (Str.isEmpty(text)) {
			return "";
		}
		return text;
	}

	public String toJson() {
		return StringUtil.trim(text).length() == 0 ? null : text;
	}

}
