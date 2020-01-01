package io.mercury.common.util;

import javax.annotation.Nonnull;

import io.mercury.common.annotations.lang.MayThrowsRuntimeException;

public final class BytesUtil {

	private BytesUtil() {
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final int bytesToInt(@Nonnull byte[] bytes) {
		if (bytes == null || bytes.length < 4)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [4]");
		return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8) | ((bytes[3] & 0xFF));
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final int bytesToInt(@Nonnull byte[] bytes, int offset) {
		if (bytes == null || bytes.length < offset + 4)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [offset + 4]");
		return ((bytes[offset] & 0xFF) << 24) | ((bytes[offset + 1] & 0xFF) << 16) | ((bytes[offset + 2] & 0xFF) << 8)
				| ((bytes[offset + 3] & 0xFF));
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final char bytesToChar(@Nonnull byte[] bytes) {
		if (bytes == null || bytes.length < 2)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [2]");
		return (char) (((bytes[0] & 0xFF) << 8) | ((bytes[1] & 0xFF)));
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final char bytesToChar(@Nonnull byte[] bytes, int offset) {
		if (bytes == null || bytes.length < offset + 2)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [offset + 2]");
		return (char) (((bytes[offset] & 0xFF) << 8) | ((bytes[offset + 1] & 0xFF)));
	}

	public static final long mergeInt(int highPos, int lowPos) {
		return (((long) highPos) << 32) | ((long) lowPos);
	}

	public static final int mergeChar(char highPos, char lowPos) {
		return (((int) highPos) << 16) | ((int) lowPos);
	}

	public static final long mergeChar(char highPos, char second, char third, char lowPos) {
		return (((long) highPos) << 48) | ((long) second << 32) | ((long) third << 16) | ((int) lowPos);
	}

	private static final long LongHighPosMask = 0xFFFF_FFFF_0000_0000L;

	public static final int splitLongWithHighPos(long l) {
		return (int) ((l & LongHighPosMask) >> 32);
	}

	private static final long LongLowPosMask = 0x0000_0000_FFFF_FFFFL;

	public static final int splitLongWithLowPos(long l) {
		return (int) (l & LongLowPosMask);
	}

	public static void main(String[] args) {

		int i1 = 1002;
		int i2 = 10777;

		System.out.println(StringUtil.formatBinaryInt(i1));
		System.out.println(StringUtil.formatBinaryInt(i2));

		System.out.println((mergeInt(i1, i2)));
		System.out.println(StringUtil.formatBinaryLong(mergeInt(i1, i2)));

		System.out.println((splitLongWithHighPos(mergeInt(i1, i2))));
		System.out.println(StringUtil.formatBinaryInt(splitLongWithHighPos(mergeInt(i1, i2))));

		System.out.println((splitLongWithLowPos(mergeInt(i1, i2))));
		System.out.println(StringUtil.formatBinaryInt(splitLongWithLowPos(mergeInt(i1, i2))));
		
		System.out.println(StringUtil.formatBinaryInt(1));
		System.out.println(StringUtil.formatBinaryInt(~1));
		
		System.out.println(StringUtil.formatBinaryInt(10));
		System.out.println(StringUtil.formatBinaryInt(20));
		System.out.println(StringUtil.formatBinaryInt(10 ^ 20));
		System.out.println(StringUtil.formatBinaryInt((10 ^ 20) ^ 20));
		
		
		System.out.println(StringUtil.formatBinaryInt(10243250));
		System.out.println(StringUtil.formatBinaryInt(1));
		System.out.println(StringUtil.formatBinaryInt(10243250 & 1));
		System.out.println(StringUtil.formatBinaryInt(1123121));
		System.out.println(StringUtil.formatBinaryInt(1));
		System.out.println(StringUtil.formatBinaryInt(1123121 & 1));
		
	}

}
