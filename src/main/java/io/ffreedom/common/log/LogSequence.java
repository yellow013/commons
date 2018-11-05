package io.ffreedom.common.log;

import org.slf4j.Logger;

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

		Logger log = LoggerFactory.getLogger(LogSequence.class);

		for (int i = 0; i < 20; i++) {
			log.debug(String.valueOf(LogSequence.sysMicrosecond()));
		}
		
	}

}
