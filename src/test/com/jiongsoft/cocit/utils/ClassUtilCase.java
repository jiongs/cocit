package com.jiongsoft.cocit.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClassUtilCase {
	String prop1;
	String prop2;
	String prop3;

	public ClassUtilCase() {

	}

	public ClassUtilCase(String prop1) {
		this.prop1 = prop1;
	}

	public ClassUtilCase(String prop1, String prop2) {
		this.prop1 = prop1;
		this.prop2 = prop2;
	}

	public ClassUtilCase(String prop1, String prop2, String prop3) {
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
	}

	public ClassUtilCase(HttpServletRequest req, HttpServletResponse res) {
	}

	public static class InnerClass {

	}
}
