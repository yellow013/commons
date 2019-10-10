package io.ffreedom.common.utils;

import java.nio.charset.Charset;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.charset.StrConstants;

public final class StringUtil {

	private StringUtil() {
	}

	public static String toString(Object obj) {
		return obj == null ? StrConstants.NULL : obj.toString();
	}

	/**
	 * Use ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE)
	 * 
	 * @param obj
	 * @return String
	 * 
	 */
	public static String reflectionToString(Object obj) {
		return obj == null ? StrConstants.NULL
				: ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
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
	// 改进性能,使用str.charAt(index)
	// 不进行char数组的copy
	public static boolean isDecimal(String str) {
		// Character.isDigit(ch);
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

	public static String conversionGbkToUtf8(String gbkStr) {
		return conversionTo(gbkStr, Charsets.GBK, Charsets.UTF8);
	}

	public static String conversionUtf8ToGbk(String utf8Str) {
		return conversionTo(utf8Str, Charsets.UTF8, Charsets.GBK);
	}

	public static String conversionGb2312ToUtf8(String gbkStr) {
		return conversionTo(gbkStr, Charsets.GB2312, Charsets.UTF8);
	}

	public static String conversionUtf8ToGb2312(String utf8Str) {
		return conversionTo(utf8Str, Charsets.UTF8, Charsets.GB2312);
	}

	public static String conversionToUtf8(String sourceStr, Charset sourceCoding) {
		return conversionTo(sourceStr, sourceCoding, Charsets.UTF8);
	}

	public static String conversionTo(String sourceStr, Charset sourceCoding, Charset targetCoding) {
		return sourceStr == null ? sourceStr : new String(sourceStr.getBytes(sourceCoding), targetCoding);
	}

	public static String bytesToStr(byte[] bytes) {
		return bytesToStr(bytes, Charsets.UTF8);
	}

	public static String bytesToStr(byte[] bytes, Charset charset) {
		return new String(bytes, charset);
	}

	public static boolean isPath(String path) {
		return isNullOrEmpty(path) ? false : path.endsWith("/") || path.endsWith("\\");
	}

	public static boolean notPath(String path) {
		return !isPath(path);
	}

	/**
	 * 使用','将字符串连接
	 * 
	 * @param strs
	 * @return
	 */
	public static String concatenateStr(String... strs) {
		return concatenateStr(strs.length * 16 + strs.length, ',', strs);
	}

	/**
	 * 指定缓冲区长度, 使用','将字符串连接
	 * 
	 * @param capacity 缓冲区长度
	 * @param strs     字符串数组
	 * @return
	 */
	public static String concatenateStr(int capacity, String... strs) {
		return concatenateStr(capacity, ',', strs);
	}

	/**
	 * 使用指定符号将字符串连接
	 * 
	 * @param symbol 连接符号
	 * @param strs   字符串数组
	 * @return
	 */
	public static String concatenateStr(char symbol, String... strs) {
		return concatenateStr(strs.length * 16 + strs.length, symbol, strs);
	}

	/**
	 * 指定缓冲区长度, 使用指定符号将字符串连接
	 * 
	 * @param capacity 缓冲区长度
	 * @param symbol   连接符号
	 * @param strs     字符串数组
	 * @return
	 */
	public static String concatenateStr(int capacity, char symbol, String... strs) {
		if (strs == null || strs.length == 0)
			return StrConstants.EMPTY;
		StringBuilder builder = new StringBuilder(capacity);
		for (int i = 0; i < strs.length; i++) {
			builder.append(strs[i]);
			if (i < strs.length - 1)
				builder.append(symbol);
		}
		return builder.toString();
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

		System.out.println(concatenateStr("A", "BB", "CCC"));
		System.out.println(concatenateStr("A", "BB", null));
		System.out.println(concatenateStr("A", "BB", "", null, "null"));
		System.out.println(concatenateStr("A", "BB", "", null));
		System.out.println(concatenateStr('%', "A", "BB", "", null));
		System.out.println(concatenateStr(null, null));

	}

}
