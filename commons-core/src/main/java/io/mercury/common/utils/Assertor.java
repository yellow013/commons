package io.mercury.common.utils;

import java.util.Collection;
import java.util.Map;

import io.mercury.common.annotations.lang.MayThrowsRuntimeException;

public final class Assertor {

	private Assertor() {
	}

	@MayThrowsRuntimeException(NullPointerException.class)
	public static <T> T nonNull(T t, String objName) {
		if (t == null)
			throw new NullPointerException(objName + " can not be null");
		return t;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static <T extends Collection<?>> T nonEmpty(T t, String objName) {
		if (t == null)
			throw new NullPointerException(objName + " can not be null");
		if (t.isEmpty())
			throw new IllegalArgumentException(objName + " can not be empty");
		return t;
	}

	@MayThrowsRuntimeException({ NullPointerException.class, IllegalArgumentException.class })
	public static <T extends Map<?, ?>> T nonEmpty(T t, String objName) {
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
