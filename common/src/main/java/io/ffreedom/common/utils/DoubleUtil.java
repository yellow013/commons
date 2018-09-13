package io.ffreedom.common.utils;

import java.math.MathContext;

public final class DoubleUtil {

	private final static long DOUBLE_TO_LONG_MULTIPLIER = 10_000_000_00L;

	private final static long DOUBLE_TO_LONG_CORRECTION_FACTOR = 1L;

	private final static long DOUBLE_TO_LONG_DIVISOR = 10L;

	private final static double LONG_TO_DOUBLE_DIVISOR = 1_000_000_00D;

	/**
	 * 保留小数点后8位的精度
	 * 
	 * @param d1
	 * @return
	 */
	private static long doubleToLong(double d1) {
		return ((long) (d1 * DOUBLE_TO_LONG_MULTIPLIER) + DOUBLE_TO_LONG_CORRECTION_FACTOR) / DOUBLE_TO_LONG_DIVISOR;
	}

	/**
	 * 小数点左移8位
	 * 
	 * @param l1
	 * @return
	 */
	private static double longToDouble(long l1) {
		return l1 / LONG_TO_DOUBLE_DIVISOR;
	}

	/**
	 * 
	 * @param d1
	 * @return
	 */
	public static double correction(double d1) {
		return longToDouble(doubleToLong(d1));
	}

	public static double add(double d1, double d2) {
		return correction(d1 + d2);
	}

	public static double subtraction(double d1, double d2) {
		return correction(d1 - d2);
	}

	public static double multiply(double d1, double d2) {
		return correction(d1 * d2);
	}

	public static double division(double d1, double d2) {
		return correction(d1 / d2);
	}

	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
		System.out.println(Math.PI);
		double d = 1.0D;
		for (;;) {
			d = multiply(d, 1.1);
			System.out.println(d);
			if (d > 10) {
				break;
			}
		}

	}

}
