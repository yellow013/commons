package io.ffreedom.common.utils;

public final class DebugUtil {

	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}

	public static String getSourceFileName() {
		return Thread.currentThread().getStackTrace()[2].getFileName();
	}

	public static void main(String[] args) {
		System.out.println(DebugUtil.getSourceFileName());
		System.out.println(DebugUtil.getLineNumber());
	}

}
