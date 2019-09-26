package io.ffreedom.common.utils;

public class BinaryUtil {

	public static final int BYTE_BIT_SIZE = Byte.SIZE;

	public static final int CHAR_BIT_SIZE = Character.SIZE;

	public static final int INT_BIT_SIZE = Integer.SIZE;

	public static final int LONG_BIT_SIZE = Long.SIZE;

	public static String byteToBinaryString(byte b) {
		String binaryString = Integer.toBinaryString(b);
		int length = BYTE_BIT_SIZE - binaryString.length();
		return highPositionFilling(new StringBuilder(BYTE_BIT_SIZE), binaryString, length);
	}

	public static String charToBinaryString(char c) {
		String binaryString = Integer.toBinaryString(c);
		int length = CHAR_BIT_SIZE - binaryString.length();
		return highPositionFilling(new StringBuilder(CHAR_BIT_SIZE), binaryString, length);
	}

	public static String intToBinaryString(int i) {
		String binaryString = Integer.toBinaryString(i);
		int length = INT_BIT_SIZE - binaryString.length();
		return highPositionFilling(new StringBuilder(INT_BIT_SIZE), binaryString, length);
	}

	public static String longToBinaryString(long l) {
		String binaryString = Long.toBinaryString(l);
		int length = LONG_BIT_SIZE - binaryString.length();
		return highPositionFilling(new StringBuilder(LONG_BIT_SIZE), binaryString, length);
	}

	private static String highPositionFilling(StringBuilder builder, String binaryValue, int length) {
		builder.append(binaryValue);
		for (; length > 0; length--)
			builder.insert(0, '0');
		return builder.toString();
	}

	public static void main(String[] args) {
		byte b = 3;

		System.out.println(BinaryUtil.longToBinaryString(-3L));
		System.out.println(BinaryUtil.intToBinaryString(-3));
		System.out.println(BinaryUtil.charToBinaryString('3'));
		System.out.println(BinaryUtil.byteToBinaryString(b));
		System.out.println(BinaryUtil.intToBinaryString(2));
		System.out.println(Integer.toBinaryString(2));
		System.out.println(BinaryUtil.intToBinaryString(-10));
		
		System.out.println(BinaryUtil.longToBinaryString(Long.MAX_VALUE));
		System.out.println(BinaryUtil.longToBinaryString(Long.MIN_VALUE));
		System.out.println(BinaryUtil.longToBinaryString(-1L));
		
		System.out.println(BinaryUtil.longToBinaryString(2L << 10));

	}

}
