package io.ffreedom.common.number;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class RandomNumber {

	private static final Random RandomInstance = new Random();

	public static void unsafeSetSeed(long seed) {
		RandomInstance.setSeed(seed);
	}

	public static int unsafeRandomUnsignedInt() {
		return Math.abs(RandomInstance.nextInt());
	}

	public static int unsafeRandomInt() {
		return RandomInstance.nextInt();
	}

	public static long unsafeRandomUnsignedLong() {
		return Math.abs(RandomInstance.nextLong());
	}

	public static long unsafeRandomLong() {
		return RandomInstance.nextLong();
	}

	public static double unsafeRandomUnsignedDouble() {
		return Math.abs(RandomInstance.nextDouble());
	}

	public static double unsafeRandomDouble() {
		return RandomInstance.nextDouble();
	}

	public static void safeSetSeed(long seed) {
		ThreadLocalRandom.current().setSeed(seed);
	}

	public static int safeRandomUnsignedInt() {
		return Math.abs(ThreadLocalRandom.current().nextInt());
	}

	public static int safeRandomInt() {
		return ThreadLocalRandom.current().nextInt();
	}

	public static long safeRandomUnsignedLong() {
		return Math.abs(ThreadLocalRandom.current().nextLong());
	}

	public static long safeRandomLong() {
		return ThreadLocalRandom.current().nextLong();
	}

	public static double safeRandomUnsignedDouble() {
		return Math.abs(ThreadLocalRandom.current().nextDouble());
	}

	public static double safeRandomDouble() {
		return ThreadLocalRandom.current().nextDouble();
	}

}
