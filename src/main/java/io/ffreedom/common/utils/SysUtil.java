package io.ffreedom.common.utils;

public final class SysUtil {

	private SysUtil() {
	}

	public static void println(Object... objs) {
		if (objs == null)
			return;
		for (Object obj : objs) {
			System.out.println(obj);
		}
	}

	public static void print(Object... objs) {
		if (objs == null)
			return;
		for (Object obj : objs) {
			System.out.print(obj + " , ");
		}
	}

	public static void main(String[] args) {

		SysUtil.print(1,2,3);

	}

}
