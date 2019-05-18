package io.ffreedom.common.utils;

public final class JointIdUtil {

	private final static long HighPosMultiplier = 10000000000L;

	/**
	 * Long.MAX_VALUE的高9位值
	 */
	private final static int HighPosMaxValue = 922337203;

	/**
	 * 将两个int value合并为long value<br>
	 * long value高9位为highPos, 低10位为lowPos
	 * 
	 * @param highPos max value = Long.MAX_VALUE / 10000000000L @param lowPos max
	 *                value = Integer.MAX_VALUE @return long value @throws
	 * 
	 */
	public static long jointId(int highPos, int lowPos) {
		if (highPos >= HighPosMaxValue)
			throw new IllegalArgumentException(
					"Input highPos is too many, MaxValue==[" + HighPosMaxValue + "], InputValue==[" + highPos + "]");
		return highPos * HighPosMultiplier + lowPos;
	}

	/**
	 * 取出高位数值
	 * 
	 * @param jointId
	 * @return
	 */
	public static int getHighPos(long jointId) {
		return (int) (jointId / HighPosMultiplier);
	}

	/**
	 * 取出低位数值
	 * 
	 * @param jointId
	 * @return
	 */
	public static int getLowPos(long jointId) {
		return (int) Math.floorMod(jointId, HighPosMultiplier);
	}

}