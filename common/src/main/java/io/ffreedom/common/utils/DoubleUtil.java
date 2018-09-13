package io.ffreedom.common.utils;

public final class DoubleUtil {

	private final static long PRECISION_8_MULTIPLIER = 1_000_000_000L;

	private final static double PRECISION_8_DIVISOR = 100_000_000D;

	private final static long PRECISION_4_MULTIPLIER = 100_000L;

	private final static double PRECISION_4_DIVISOR = 10_000D;

	private final static long CORRECTION_FACTOR = 1L;

	private final static long CORRECTION_DIVISOR = 10L;

	/**
	 * 保留小数点后8位的精度
	 * 
	 * @param d1
	 * @return
	 */
	private static long doubleToLong8(double d1) {
		return ((long) (d1 * PRECISION_8_MULTIPLIER) + CORRECTION_FACTOR) / CORRECTION_DIVISOR;
	}

	/**
	 * 小数点左移8位
	 * 
	 * @param l1
	 * @return
	 */
	private static double longToDouble8(long l1) {
		return l1 / PRECISION_8_DIVISOR;
	}

	/**
	 * 保留小数点后4位的精度
	 * 
	 * @param d1
	 * @return
	 */
	private static long doubleToLong4(double d1) {
		return ((long) (d1 * PRECISION_4_MULTIPLIER) + CORRECTION_FACTOR) / CORRECTION_DIVISOR;
	}

	/**
	 * 小数点左移4位
	 * 
	 * @param l1
	 * @return
	 */
	private static double longToDouble4(long l1) {
		return l1 / PRECISION_4_DIVISOR;
	}

	/**
	 * 修正double保留8位精度
	 * 
	 * @param d1
	 * @return
	 */
	public static double correction8(double d1) {
		return longToDouble8(doubleToLong8(d1));
	}

	/**
	 * 修正double保留4位精度
	 * 
	 * @param d1
	 * @return
	 */
	public static double correction4(double d1) {
		return longToDouble4(doubleToLong4(d1));
	}

	/**
	 * 8位精度相加
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add8(double d1, double d2) {
		return correction8(d1 + d2);
	}

	/**
	 * 4位精度相加
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add4(double d1, double d2) {
		return correction4(d1 + d2);
	}

	/**
	 * 8位精度相减
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double subtraction(double d1, double d2) {
		return correction8(d1 - d2);
	}

	/**
	 * 8位精度乘法
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double multiply8(double d1, double d2) {
		return correction8(d1 * d2);
	}

	/**
	 * 4位精度乘法
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double multiply4(double d1, double d2) {
		return correction4(d1 * d2);
	}

	/**
	 * 8位精度除法
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double division(double d1, double d2) {
		return correction8(d1 / d2);
	}

}
