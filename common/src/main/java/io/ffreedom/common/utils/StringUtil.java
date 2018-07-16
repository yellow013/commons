package io.ffreedom.common.utils;

public final class StringUtil {

	private StringUtil() {
	}

	public final static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	public final static boolean isEquals(String str1, String str2) {
		return str1.equals(str2);
	}

}
