package com.jiongsoft.cocit.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testEncodeHex_1() throws Exception {
		String str = "1";
		String result = StringUtil.encodeHex(str);
		System.out.println(result);

		str = "2";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "3";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "11";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "1111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "11111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "111111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "1111111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "11111111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "111111111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
		str = "1111111111";
		result = StringUtil.encodeHex(str);
		System.out.println(result);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(StringUtilTest.class);
	}
}