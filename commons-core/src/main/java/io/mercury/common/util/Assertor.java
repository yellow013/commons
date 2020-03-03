package io.mercury.common.util;

import java.util.Collection;
import java.util.Map;

import io.mercury.common.annotation.lang.MayThrowsRuntimeException;

public final class Assertor {

	private Assertor() {
	}

	@MayThrowsRuntimeException(IllegalArgumentException.class)
	public static int greaterThan(int i, int min, String objName) {
		if (i > min)
			return i;
		throw new IllegalArgumentException(objName + " must greater than " + min);
	}

	@MayThrowsRuntimeException(IllegalArgumentException.class)
	public static long greaterThan(long l, long min, String objName) {
		if (l > min)
			return l;
		throw new IllegalArgumentException(objName + " must greater than " + min);
	}

	@MayThrowsRuntimeException(IllegalArgumentException.class)
	public static int lessThan(int i, int max, String objName) {
		if (i < max)
			return i;
		throw new IllegalArgumentException(objName + " must less than " + max);
	}

	@MayThrowsRuntimeException(IllegalArgumentException.class)
	public static long lessThan(long l, long max, String objName) {
		if (l < max)
			return l;
		throw new IllegalArgumentException(objName + " must less than " + max);
	}

	@MayThrowsRuntimeException(IllegalArgumentException.class)
	public static int withinRange(int i, int min, int max, String objName) {
		if (i >= min && i <= max)
			return i;
		throw new IllegalArgumentException(objName + " must in the range of [" + min + "] to [" + max + "]");
	}

	@MayThrowsRuntimeException(IllegalArgumentException.class)
	public static long withinRange(long l, long min, long max, String objName) {
		if (l >= min && l <= max)
			return l;
		throw new IllegalArgumentException(objName + " must in the range of [" + min + "] to [" + max + "]");
	}

	@MayThrowsRuntimeException(NullPointerException.class)
	public static <T> T nonNull(T t, String objName) {
		if (t == null)
			throw new NullPointerException(objName + " can not be null");
		return t;
	}

	public static <T, E extends Throwable> T nonNull(T t, E e) throws E {
		if (t == null)
			throw e;
		return t;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static String nonNullAndEmpty(String str, String objName) {
		if (str == null)
			throw new NullPointerException(objName + " can not be null");
		if (str.isEmpty())
			throw new IllegalArgumentException(objName + " can not be empty");
		return str;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static <T extends Collection<?>> T nonNullAndEmpty(T t, String objName) {
		if (t == null)
			throw new NullPointerException(objName + " can not be null");
		if (t.isEmpty())
			throw new IllegalArgumentException(objName + " can not be empty");
		return t;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static <T extends Map<?, ?>> T nonNullAndEmpty(T t, String objName) {
		if (t == null)
			throw new NullPointerException(objName + " can not be null");
		if (t.isEmpty())
			throw new IllegalArgumentException(objName + " can not be empty");
		return t;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static <T> T[] validArray(T[] array, int minLength, String arrayName) {
		if (array == null)
			throw new NullPointerException(arrayName + " can not be null");
		if (array.length < minLength)
			throw new IllegalArgumentException(arrayName + " length must be greater than " + minLength);
		return array;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static byte[] validArray(byte[] array, int minLength, String arrayName) {
		if (array == null)
			throw new NullPointerException(arrayName + " can not be null");
		if (array.length < minLength)
			throw new IllegalArgumentException(arrayName + " length must be greater than " + minLength);
		return array;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static char[] validArray(char[] array, int minLength, String arrayName) {
		if (array == null)
			throw new NullPointerException(arrayName + " can not be null");
		if (array.length < minLength)
			throw new IllegalArgumentException(arrayName + " length must be greater than " + minLength);
		return array;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static int[] validArray(int[] array, int minLength, String arrayName) {
		if (array == null)
			throw new NullPointerException(arrayName + " can not be null");
		if (array.length < minLength)
			throw new IllegalArgumentException(arrayName + " length must be greater than " + minLength);
		return array;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static long[] validArray(long[] array, int minLength, String arrayName) {
		if (array == null)
			throw new NullPointerException(arrayName + " can not be null");
		if (array.length < minLength)
			throw new IllegalArgumentException(arrayName + " length must be greater than " + minLength);
		return array;
	}

}
