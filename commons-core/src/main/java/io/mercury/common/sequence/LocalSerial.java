package io.mercury.common.sequence;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import io.mercury.common.thread.ThreadUtil;

public final class LocalSerial {

	private AtomicLong innerId;

	private LocalSerial(long initValue) {
		this.innerId = new AtomicLong(initValue);
	}

	public static LocalSerial newInstance(long initValue) {
		return new LocalSerial(initValue);
	}

	public static LocalSerial newInstance() {
		return new LocalSerial(0);
	}

	public long incrementAndGet() {
		return innerId.incrementAndGet();
	}

	public static void main(String[] args) {

		AtomicLong atomicLong = new AtomicLong(50);

		Random random = new Random(2);

		ThreadUtil.startNewThread(() -> {
			if (atomicLong.get() < 0) {
				return;
			} else if (atomicLong.get() > 100) {
				return;
			} else {
				if (random.nextLong() % 2 == 0) {
					atomicLong.getAndAdd(1);
				} else {
					atomicLong.getAndAdd(-1);
				}
			}
		});

	}

}
