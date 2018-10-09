package io.ffreedom.common.log;

public final class LogSequence {

	public static long sysMillisecond() {
		return System.nanoTime() / 1000_000;
	}

	public static long sysMicrosecond() {
		return System.nanoTime() / 1000;
	}

	public static long sysNanosecond() {
		return System.nanoTime();
	}

	public static void main(String[] args) {

		System.out.println(LogSequence.sysNanosecond());
		System.out.println(LogSequence.sysMicrosecond());
		System.out.println(LogSequence.sysMillisecond());

		
		
		
	}

}
