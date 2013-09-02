package com.jiongsoft.cocit.utils;

import java.util.Collection;

public abstract class Lang {

	public static boolean hasContent(Collection coll) {
		if (coll == null)
			return false;

		return coll.size() > 0;
	}
}
