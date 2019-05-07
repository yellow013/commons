package io.ffreedom.common.utils;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class Randoms {

	private static ThreadLocalRandom random = ThreadLocalRandom.current();

	public static int randomUnsignedInt() {
		return Math.abs(random.nextInt());
	}

	public static int randomInt() {
		return random.nextInt();
	}

	public static long randomUnsignedLong() {
		return Math.abs(random.nextLong());
	}

	public static long randomLong() {
		return random.nextLong();
	}

	public static double randomUnsignedDouble() {
		return Math.abs(random.nextDouble());
	}

	public static double randomDouble() {
		return random.nextDouble();
	}

}
