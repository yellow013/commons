package io.mercury.common.utils;

import static java.lang.System.arraycopy;

public final class ArrayUtil {

	public static boolean isNullOrEmpty(byte[] array) {
		return array == null ? true : array.length == 0 ? true : false;
	}

	public static boolean isNullOrEmpty(char[] array) {
		return array == null ? true : array.length == 0 ? true : false;
	}

	public static boolean isNullOrEmpty(int[] array) {
		return array == null ? true : array.length == 0 ? true : false;
	}

	public static boolean isNullOrEmpty(long[] array) {
		return array == null ? true : array.length == 0 ? true : false;
	}

	public static boolean isNullOrEmpty(double[] array) {
		return array == null ? true : array.length == 0 ? true : false;
	}

	public static boolean isNullOrEmpty(Object[] array) {
		return array == null ? true : array.length == 0 ? true : false;
	}

	public static boolean[] newOf(boolean[] origin) {
		boolean[] newArray = new boolean[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static boolean[] copy(boolean[] origin, boolean[] newArray) {
		if (newArray == null)
			newArray = new boolean[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static byte[] newOf(byte[] origin) {
		byte[] newArray = new byte[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static byte[] copy(byte[] origin, byte[] newArray) {
		if (newArray == null)
			newArray = new byte[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static char[] newOf(char[] origin) {
		char[] newArray = new char[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static char[] copy(char[] origin, char[] newArray) {
		if (newArray == null)
			newArray = new char[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static int[] newOf(int[] origin) {
		int[] newArray = new int[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static int[] copy(int[] origin, int[] newArray) {
		if (newArray == null)
			newArray = new int[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static long[] newOf(long[] origin) {
		long[] newArray = new long[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static long[] copy(long[] origin, long[] newArray) {
		if (newArray == null)
			newArray = new long[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static float[] newOf(float[] origin) {
		float[] newArray = new float[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static float[] copy(float[] origin, float[] newArray) {
		if (newArray == null)
			newArray = new float[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static double[] newOf(double[] origin) {
		double[] newArray = new double[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static double[] copy(double[] origin, double[] newArray) {
		if (newArray == null)
			newArray = new double[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static String[] newOf(String[] origin) {
		String[] newArray = new String[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static String[] copy(String[] origin, String[] newArray) {
		if (newArray == null)
			newArray = new String[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static Object[] newOf(Object[] origin) {
		Object[] newArray = new Object[origin.length];
		arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static Object[] copy(Object[] origin, Object[] newArray) {
		if (newArray == null)
			newArray = new Object[origin.length];
		arraycopy(origin, 0, newArray, 0, newArray.length < origin.length ? newArray.length : origin.length);
		return newArray;
	}

	public static String toString(Object... objs) {
		if (objs == null)
			return "";
		StringBuilder builder = new StringBuilder(objs.length * 2 * 16).append('[');
		for (int i = 0, j = objs.length - 1; i < objs.length; i++) {
			builder.append(objs[i].toString());
			if (i < j)
				builder.append(',');
		}
		return builder.append(']').toString();
	}

}
