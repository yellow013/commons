package io.ffreedom.common.sequence;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import io.ffreedom.common.utils.ThreadUtil;

public class LocalId {

	private AtomicLong ID;

	private LocalId(long initValue) {
		this.ID = new AtomicLong(initValue);
	}

	public long incrementAndGet() {
		return ID.incrementAndGet();
	}

	public static LocalId newInstance(long initValue) {
		return new LocalId(initValue);
	}

	public static LocalId newInstance() {
		return new LocalId(0);
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
