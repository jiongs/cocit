package com.jiongsoft.cocit.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.lang.Mirror;

public abstract class Lang {

	public static boolean hasContent(Collection coll) {
		if (coll == null)
			return false;

		return coll.size() > 0;
	}

	public static String format(Object value, String pattern) {
		if (pattern != null && pattern.startsWith("*")) {
			pattern = pattern.substring(1);
		}
		if (value instanceof String) {
			return (String) value;
		} else if (value instanceof Date) {
			if (!StringUtil.isNil(pattern)) {
				try {
					return DateUtil.formatDate((Date) value, pattern);
				} catch (Throwable e) {
					return DateUtil.formatDateTime((Date) value);
				}
			} else {
				return DateUtil.formatDate((Date) value);
			}
		} else if (value instanceof Number && !StringUtil.isNil(pattern)) {
			// if (Strings.isEmpty(pattern)) {
			// if (value instanceof Long || value instanceof Integer ||
			// value instanceof Short)
			// pattern = "#,##0";
			// else
			// pattern = "#,##0.0#";
			// }
			return new DecimalFormat(pattern).format(new BigDecimal(value.toString()));
		}

		if (value instanceof List) {
			List list = (List) value;
			if (list.size() == 0) {
				return "";
			} else {
				StringBuffer sb = new StringBuffer();
				for (Object obj : list) {
					sb.append(",").append(obj);
				}
				return sb.substring(1);
			}
		}

		return value == null ? "" : value.toString();
	}

	public static <T> T getValue(Object obj, final String path) {
		if (obj == null) {
			return null;
		}

		if (StringUtil.isNil(path))
			return null;

		int dot = path.indexOf(".");
		if (dot > -1) {
			Object subObj = null;
			try {
				if (obj instanceof Map) {
					subObj = ((Map) obj).get(path.substring(0, dot));
				} else {
					subObj = Mirror.me(obj).getValue(obj, path.substring(0, dot));
				}
			} catch (Throwable e) {
			}
			if (subObj == null) {
				return null;
			}

			return (T) getValue(subObj, path.substring(dot + 1));
		} else {
			try {
				return (T) Mirror.me(obj).getValue(obj, path);
			} catch (Throwable e) {
				return null;
			}
		}
	}

	public static void setValue(Object obj, String path, Object value) {
		if (obj == null) {
			return;
		}
		Mirror me = Mirror.me(obj);
		try {
			int dot = path.indexOf(".");
			if (dot < 0) {
				me.setValue(obj, path, value);
			} else {
				String prop = path.substring(0, dot);
				Class fldtype = me.getField(prop).getType();
				Mirror fldme = Mirror.me(fldtype);
				Object fldval = fldme.born();
				setValue(fldval, path.substring(dot + 1), value);
				setValue(obj, prop, fldval);
			}
		} catch (NoSuchFieldException e) {
		}
	}

}
