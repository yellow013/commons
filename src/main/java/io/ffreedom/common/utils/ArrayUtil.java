package io.ffreedom.common.utils;

public final class ArrayUtil {

	public static byte[] copyOf(byte[] origin) {
		byte[] newArray = new byte[origin.length];
		System.arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static char[] copyOf(char[] origin) {
		char[] newArray = new char[origin.length];
		System.arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static int[] copyOf(int[] origin) {
		int[] newArray = new int[origin.length];
		System.arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static double[] copyOf(double[] origin) {
		double[] newArray = new double[origin.length];
		System.arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static String[] copyOf(String[] origin) {
		String[] newArray = new String[origin.length];
		System.arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

	public static Object[] copyOf(Object[] origin) {
		Object[] newArray = new Object[origin.length];
		System.arraycopy(origin, 0, newArray, 0, origin.length);
		return newArray;
	}

}
