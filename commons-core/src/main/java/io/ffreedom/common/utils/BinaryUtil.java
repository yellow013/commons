package io.ffreedom.common.utils;

public class BinaryUtil {

	public static final int BYTE_BIT_SIZE = Byte.SIZE;

	public static final int SHORT_BIT_SIZE = Short.SIZE;

	public static final int CHAR_BIT_SIZE = Character.SIZE;

	public static final int INT_BIT_SIZE = Integer.SIZE;

	public static final int LONG_BIT_SIZE = Long.SIZE;

	public static String byteToBinaryString(byte b) {
		String binaryString = Integer.toBinaryString(b);
		int length0 = BYTE_BIT_SIZE - binaryString.length();
		return highPositionFilling(binaryString, length0);
	}

	public static String shortToBinaryString(short s) {
		String binaryString = Integer.toBinaryString(s);
		int length0 = SHORT_BIT_SIZE - binaryString.length();
		return highPositionFilling(binaryString, length0);
	}

	public static String charToBinaryString(char c) {
		String binaryString = Integer.toBinaryString(c);
		int length0 = CHAR_BIT_SIZE - binaryString.length();
		return highPositionFilling(binaryString, length0);
	}

	public static String intToBinaryString(int i) {
		String binaryString = Integer.toBinaryString(i);
		int length0 = INT_BIT_SIZE - binaryString.length();
		return highPositionFilling(binaryString, length0);
	}

	public static String longToBinaryString(long l) {
		String binaryString = Long.toBinaryString(l);
		int length0 = LONG_BIT_SIZE - binaryString.length();
		return highPositionFilling(binaryString, length0);
	}

	private static String highPositionFilling(String binaryString, int i) {
		for (; i > 0; i--)
			binaryString = "0" + binaryString;
		return binaryString;
	}

	public static void main(String[] args) {

		System.out.println(BinaryUtil.longToBinaryString(3L));
		System.out.println(BinaryUtil.intToBinaryString(2));
		System.out.println(BinaryUtil.intToBinaryString(-10));
	}

}
