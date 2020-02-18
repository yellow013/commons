package io.mercury.common.util;

import javax.annotation.Nonnull;

import io.mercury.common.annotation.lang.MayThrowsRuntimeException;

public final class BytesUtil {

	private BytesUtil() {
	}

	/**
	 * 将byte转换为二进制输出,高位补0
	 * 
	 * @param b
	 * @return
	 */
	public static String binaryByte(byte b) {
		String binary = Integer.toBinaryString(b);
		return highPosFill(Byte.SIZE, Byte.SIZE - binary.length(), binary);
	}

	/**
	 * 将char转换为二进制输出,高位补0
	 * 
	 * @param c
	 * @return
	 */
	public static String binaryChar(char c) {
		String binaryStr = Integer.toBinaryString(c);
		return highPosFill(Character.SIZE, Character.SIZE - binaryStr.length(), binaryStr);
	}

	public static String formatBinaryChar(char c) {
		return new StringBuilder(binaryChar(c)).insert(8, ' ').toString();
	}

	/**
	 * 将int转换为二进制输出,高位补0
	 * 
	 * @param i
	 * @return
	 */
	public static String binaryInt(int i) {
		String binaryStr = Integer.toBinaryString(i);
		return highPosFill(Integer.SIZE, Integer.SIZE - binaryStr.length(), binaryStr);
	}

	public static String formatBinaryInt(int i) {
		return new StringBuilder(binaryInt(i)).insert(24, ' ').insert(16, ' ').insert(8, ' ').toString();
	}

	/**
	 * 将long转换为二进制输出,高位补0
	 * 
	 * @param l
	 * @return
	 */
	public static String binaryLong(long l) {
		String binaryStr = Long.toBinaryString(l);
		return highPosFill(Long.SIZE, Long.SIZE - binaryStr.length(), binaryStr);
	}

	public static String formatBinaryLong(long l) {
		return new StringBuilder(binaryLong(l)).insert(56, ' ').insert(48, ' ').insert(40, ' ').insert(32, ' ')
				.insert(24, ' ').insert(16, ' ').insert(8, ' ').toString();
	}

	/**
	 * 指定总长度, 空白长度, 实际值, 返回的字符串填充指定长度的0
	 * 
	 * @param sumLen
	 * @param blankLen
	 * @param binaryStr
	 * @return
	 */
	private static String highPosFill(int sumLen, int blankLen, String binaryStr) {
		StringBuilder builder = new StringBuilder(sumLen);
		for (int i = 0; i < blankLen; i++)
			builder.append('0');
		return builder.append(binaryStr).toString();
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

	public static final boolean isOdd(int i) {
		return (i & 1) != 0;
	}

	public static final boolean isOdd(long l) {
		return (l & 1) != 0;
	}

	private static final int MAXIMUM_CAPACITY = 1 << 30;

	/**
	 * 返回参数的最小的2的幂
	 * 
	 * @param i
	 * @return
	 */
	public static final int minPow2(int i) {
		int n = i - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	public static void main(String[] args) {

		int i1 = 1002;
		int i2 = 10777;

		System.out.println(formatBinaryInt(i1));
		System.out.println(formatBinaryInt(i2));

		System.out.println((mergeInt(i1, i2)));
		System.out.println(formatBinaryLong(mergeInt(i1, i2)));

		System.out.println((splitLongWithHighPos(mergeInt(i1, i2))));
		System.out.println(formatBinaryInt(splitLongWithHighPos(mergeInt(i1, i2))));

		System.out.println((splitLongWithLowPos(mergeInt(i1, i2))));
		System.out.println(formatBinaryInt(splitLongWithLowPos(mergeInt(i1, i2))));

		System.out.println(formatBinaryInt(1));
		System.out.println(formatBinaryInt(~1));

		System.out.println(formatBinaryInt(10));
		System.out.println(formatBinaryInt(20));
		System.out.println(formatBinaryInt(10 ^ 20));
		System.out.println(formatBinaryInt((10 ^ 20) ^ 20));

		System.out.println(formatBinaryInt(10243250));
		System.out.println(formatBinaryInt(1));
		System.out.println(formatBinaryInt(10243250 & 1));
		System.out.println(formatBinaryInt(1123121));
		System.out.println(formatBinaryInt(1));
		System.out.println(formatBinaryInt(1123121 & 1));

		byte b = 3;

		System.out.println(binaryChar('b'));
		System.out.println(formatBinaryChar('b'));

		System.out.println(binaryInt(10777));
		System.out.println(formatBinaryInt(10777));

		System.out.println(binaryLong(106544777L));
		System.out.println(formatBinaryLong(106544777L));

		System.out.println(binaryByte(b));

		System.out.println(binaryLong(-3L));
		System.out.println(binaryInt(-3));
		System.out.println(binaryChar('3'));
		System.out.println(binaryInt(2));
		System.out.println(Integer.toBinaryString(2));
		System.out.println(binaryInt(-10));

		System.out.println(binaryLong(Long.MAX_VALUE));
		System.out.println(binaryLong(Long.MIN_VALUE));
		System.out.println(binaryLong(-1L));
		System.out.println(binaryLong(2L << 10));

	}

}
