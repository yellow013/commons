package io.ffreedom.common.utils;

public final class DoubleUtil {

	private final static long DOUBLE_TO_LONG_MULTIPLIER = 10_000_000_000L;

	private final static long DOUBLE_TO_LONG_CORRECTION_FACTOR = 1L;

	private final static long DOUBLE_TO_LONG_DIVISOR = 10L;

	private final static double LONG_TO_DOUBLE_DIVISOR = 1_000_000_000D;

	/**
	 * 保留小数点后9位的精度
	 * 
	 * @param d1
	 * @return
	 */
	public static long doubleToLong(double d1) {
		return ((long) (d1 * DOUBLE_TO_LONG_MULTIPLIER) + DOUBLE_TO_LONG_CORRECTION_FACTOR) / DOUBLE_TO_LONG_DIVISOR;
	}

	/**
	 * 小数点左移9位
	 * 
	 * @param l1
	 * @return
	 */
	public static double longToDouble(long l1) {
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

	public static void main(String[] args) {

		double d = 1.0D;
		for (;;) {
			d = correction(d + 0.01);
			System.out.println(d);
			if (d > 3) {
				break;
			}
		}

	}

}
