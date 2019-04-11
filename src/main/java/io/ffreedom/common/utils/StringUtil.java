package io.ffreedom.common.utils;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.charset.StringConstants;

public final class StringUtil {

	private StringUtil() {
	}

	public static String toString(Object obj) {
		return obj == null ? StringConstants.NullStr : obj.toString();
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean notNullAndEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public static boolean isEquals(String str1, String str2) {
		return str1 != null ? str1.equals(str2) : str2 != null ? str2.equals(str1) : true;
	}

	// TODO
	// 改进性能
	// 不进行char数组的copy
	public static boolean isDecimal(String str) {
		if (isNullOrEmpty(str))
			return false;
		char[] chars = str.toCharArray();
		// 判断是否负数
		if (chars[0] == '-')
			chars[0] = '0';
		// 获取最后一个字符
		char lastChar = chars[chars.length - 1];
		// 判断是否为long double float的写法
		if (lastChar == 'L' || lastChar == 'l' || lastChar == 'D' || lastChar == 'd' || lastChar == 'F'
				|| lastChar == 'f')
			chars[chars.length - 1] = '0';
		// 小数点标识
		boolean decimalPointFlag = false;
		for (char ch : chars) {
			// 判断每个字母是否为数字
			if (!(ch >= '0' && ch <= '9'))
				// 出现第二个小数点返回false
				if (decimalPointFlag)
					return false;
				// 标识已出现一个小数点
				else if (ch == '.')
					decimalPointFlag = true;
				// 出现其他字符返回false
				else
					return false;
		}
		return true;
	}

	public static boolean notDecimal(String str) {
		return !isDecimal(str);
	}

	public static String gbkConversionToUtf8(String gbkStr) {
		return gbkStr == null ? "" : new String(gbkStr.getBytes(Charsets.GBK), Charsets.UTF8);
	}

	public static String utf8ConversionToGbk(String utf8Str) {
		return utf8Str == null ? new String("".getBytes(Charsets.UTF8), Charsets.GBK)
				: new String(utf8Str.getBytes(Charsets.UTF8), Charsets.GBK);
	}

	public static void main(String[] args) {

		System.out.println(isDecimal("877f"));
		System.out.println(isDecimal("877F"));
		System.out.println(isDecimal("877l"));
		System.out.println(isDecimal("877L"));
		System.out.println(isDecimal("877d"));
		System.out.println(isDecimal("877D"));
		System.out.println(isDecimal("-877"));
		System.out.println(isDecimal("-877L"));
		System.out.println(isDecimal("8.77L"));
		System.out.println(isDecimal(".877"));
		System.out.println(isDecimal("-.877"));
		System.out.println(isDecimal("-.87.7"));

	}

}
