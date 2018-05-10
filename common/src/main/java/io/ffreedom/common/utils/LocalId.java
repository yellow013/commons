package io.ffreedom.common.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class LocalId {

	private AtomicLong localId;

	private LocalId(long initValue) {
		this.localId = new AtomicLong(initValue);
	}

	public long genId() {
		return localId.incrementAndGet();
	}

	public static LocalId newInstance(long initValue) {
		return new LocalId(initValue);
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
				if(random.nextLong() % 2 == 0) {
					atomicLong.getAndAdd(1);
				}else {
					atomicLong.getAndAdd(-1);
				}
			}
		});

	}

}
