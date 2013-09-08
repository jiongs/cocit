package com.jiongsoft.cocit.utils;

public class CocException extends Exception {
	private static final long serialVersionUID = 1L;

	public CocException(String format, Object... args) {
		super(String.format(format, args));
	}

}
