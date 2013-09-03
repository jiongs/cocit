package com.jiongsoft.cocit.utils;

import java.util.List;

import com.jiongsoft.cocit.utils.sort.InsertSort;
import com.jiongsoft.cocit.utils.sort.SortStrategy;

public abstract class SortUtil {
	private static final SortStrategy sort = new InsertSort();

	public static void sort(Object[] obj, boolean nullGT) {
		sort.sort(obj, "orderby", nullGT);
	}

	public static void sort(List obj, boolean nullGT) {
		sort.sort(obj, "orderby", nullGT);
	}

	public static void sort(Object[] obj, String field, boolean nullGT) {
		sort.sort(obj, field, nullGT);
	}

	public static void sort(List list, String field, boolean nullGT) {
		sort.sort(list, field, nullGT);
	}
}
