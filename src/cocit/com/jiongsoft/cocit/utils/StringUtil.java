package com.jiongsoft.cocit.utils;

import java.io.IOException;
import java.util.ArrayList;
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

	public static String[] toArray(String str, String token) {
		if (str == null)
			return null;
		if (token == null)
			token = ";, ";

		List<String> list = new ArrayList();

		StringTokenizer st = new StringTokenizer(str, token);
		while (st.hasMoreElements()) {
			list.add((String) st.nextElement());
		}

		String[] array = new String[list.size()];

		for (int i = list.size() - 1; i >= 0; i--) {
			array[i] = list.get(i);
		}

		return array;
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
}
