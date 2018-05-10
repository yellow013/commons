package io.ffreedom.common.log;

public class LogSequence {

	public static long sysMicrosecond() {
		return System.nanoTime() / 1000;
	}

}
