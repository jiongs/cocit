// $codepro.audit.disable unnecessaryCast
package com.jiongsoft.cocit.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public abstract class StringUtil {
	/**
	 * 判断字符串是否为空或一串空白？
	 * 
	 * @param str
	 * @return 参数为null或空白串都将返回true，否则返回false。
	 */
	public static boolean isNil(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 剪切字符串两端的空白。
	 * 
	 * @param str
	 * @return 参数为null将返回空串"", 否则返回str.trim();
	 */
	public static String trim(String str) {
		if (str == null)
			return "";

		return str.trim();
	}

	public static String[] toArray(String str) {
		return toArray(str, null);
	}

	public static String[] toArray(String str, String token) {
		List<String> list = toList(str, token);

		String[] array = new String[list.size()];

		for (int i = list.size() - 1; i >= 0; i--) {
			array[i] = list.get(i);
		}

		return array;
	}

	public static List<String> toList(String str) {
		return toList(str, null);
	}

	public static List<String> toList(String str, String token) {
		List<String> list = new ArrayList();

		if (!isNil(str)) {
			if (isNil(token))
				token = ";, ";

			StringTokenizer st = new StringTokenizer(str, token);
			while (st.hasMoreElements()) {
				list.add((String) st.nextElement());
			}
		}

		return list;
	}

	/**
	 * Hex解密
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String decodeHex(String str) {
		if (str == null)
			return null;

		// return new String(BinaryCodec.fromAscii(str.toCharArray()));

		try {
			return new String(Hex.decodeHex(str.toCharArray()));
		} catch (DecoderException e) {
			Log.warn("", e);
			return null;
		}
	}

	/**
	 * Hex加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeHex(String str) {
		if (str == null)
			return null;

		// return new String(BinaryCodec.toAsciiBytes(str.getBytes()));
		return new String(Hex.encodeHex(str.getBytes()));
	}

	/**
	 * 将指定的值转换成特定类型的对象，可以是String/Long/Integer/Short/Byte/Double/Float/Boolean/Date/Number。
	 * <p>
	 * 如果值类型不属于上面的任何类型，则将value当作一个JSON文本，并试图将其转换成指定类型的java对象。
	 * 
	 * @param text
	 *            文本字符串值
	 * @param defaultReturn
	 *            返回的默认值
	 * @return 转换后的值对象
	 */
	public static <T> T castTo(String text, T defaultReturn) {

		if (text == null)
			return defaultReturn;
		if (defaultReturn == null)
			return (T) text;

		Class valueType = defaultReturn.getClass();

		try {
			return (T) castTo(text, valueType);
		} catch (Throwable e) {
			Log.error("将文本转换成指定的Java对象失败！text=%s, valueType=%s, defaultReturn=%s", text, valueType, defaultReturn, e);
		}

		return defaultReturn;
	}

	/**
	 * 将指定的值转换成特定类型的对象，可以是String/Long/Integer/Short/Byte/Double/Float/Boolean/Date/Number。
	 * <p>
	 * 如果值类型不属于上面的任何类型，则将value当作一个JSON文本，并试图将其转换成指定类型的java对象。
	 * 
	 * @param text
	 *            文本字符串值
	 * @param valueType
	 *            需要转换的值类型
	 * @return 转换后的值对象
	 */
	public static <T> T castTo(String text, Class<T> valueType) {
		if (text == null || valueType == null)
			return null;

		if (valueType.equals(String.class))
			return (T) text;
		if (valueType.equals(Long.class))
			return (T) Long.valueOf(text);
		if (valueType.equals(Integer.class))
			return (T) Integer.valueOf(text);
		if (valueType.equals(Short.class))
			return (T) Short.valueOf(text);
		if (valueType.equals(Byte.class))
			return (T) Byte.valueOf(text);
		if (valueType.equals(Double.class))
			return (T) Double.valueOf(text);
		if (valueType.equals(Float.class))
			return (T) Float.valueOf(text);
		if (valueType.equals(Boolean.class))
			return (T) Boolean.valueOf(text);
		if (Date.class.isAssignableFrom(valueType))
			return (T) CoCalendar.parse(text);
		if (Number.class.isAssignableFrom(valueType))
			return ClassUtil.newInstance(valueType, text);

		return Json.fromJson(valueType, text);
	}

	public static String join(String[] arr, String sep, boolean ignoreNil) {
		StringBuffer sb = new StringBuffer();

		for (String str : arr) {
			if (ignoreNil && isNil(str))
				continue;

			sb.append(sep).append(str);
		}

		return sb.length() > 0 ? sb.substring(1) : "";
	}
}
