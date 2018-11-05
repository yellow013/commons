package io.ffreedom.common.utils;

public final class SysUtil {

	private SysUtil() {
	}

	public static void println(Object... objs) {
		for (Object obj : objs) {
			System.out.println(obj);
		}
	}

	public static void print(Object... objs) {
		for (Object obj : objs) {
			System.out.print(obj + " , ");
		}
	}

}
