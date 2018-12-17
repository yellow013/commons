package io.ffreedom.common.utils;

import java.util.Arrays;

public final class StringUtil {

	private StringUtil() {
	}

	public final static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public final static boolean notNullAndEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public final static boolean isEquals(String str1, String str2) {
		return str1 != null ? str1.equals(str2) : str2 != null ? str2.equals(str1) : true;
	}

	// TODO
	// 改进性能
	// 不进行char数组copy
	public final static boolean isNumber(String str) {
		if (isNullOrEmpty(str))
			return false;
		char[] chars = str.toCharArray();
		if (chars[0] == '-')
			chars[0] = '0';
		char lastChar = chars[chars.length - 1];
		if (lastChar == 'L' || lastChar == 'l' || lastChar == 'D' || lastChar == 'd' || lastChar == 'F'
				|| lastChar == 'f')
			chars[chars.length - 1] = '0';
		boolean haveDecimalPoint = false;
		for (char c : chars) {
			if (!Character.isDigit(c))
				if (haveDecimalPoint) {
					return false;
				} else {
					if (c == '.')
						haveDecimalPoint = true;
					else
						return false;
				}
		}
		return true;
	}

	public static void main(String[] args) {

		System.out.println(isNumber("877f"));
		System.out.println(isNumber("877F"));
		System.out.println(isNumber("877l"));
		System.out.println(isNumber("877L"));
		System.out.println(isNumber("877d"));
		System.out.println(isNumber("877D"));
		System.out.println(isNumber("-877"));
		System.out.println(isNumber("-877L"));
		System.out.println(isNumber("8.77L"));
		System.out.println(isNumber(".877"));
		System.out.println(isNumber("-.877"));
		System.out.println(isNumber("-.87.7"));

	}

}
