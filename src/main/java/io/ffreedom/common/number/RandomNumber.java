package io.ffreedom.common.number;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class RandomNumber {

	private static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

	public static void setSeed(long seed) {
		threadLocalRandom.setSeed(seed);
	}

	public static int randomUnsignedInt() {
		return Math.abs(threadLocalRandom.nextInt());
	}

	public static int randomInt() {
		return threadLocalRandom.nextInt();
	}

	public static long randomUnsignedLong() {
		return Math.abs(threadLocalRandom.nextLong());
	}

	public static long randomLong() {
		return threadLocalRandom.nextLong();
	}

	public static double randomUnsignedDouble() {
		return Math.abs(threadLocalRandom.nextDouble());
	}

	public static double randomDouble() {
		return threadLocalRandom.nextDouble();
	}

}
