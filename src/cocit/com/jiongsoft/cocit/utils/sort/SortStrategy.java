package com.jiongsoft.cocit.utils.sort;

import java.util.List;

public interface SortStrategy {
	public static final SortStrategy defaultStrategy = new InsertSort();

	public void sort(Object[] obj, String field, boolean nullGT);

	public void sort(List list, String field, boolean nullGT);
}
