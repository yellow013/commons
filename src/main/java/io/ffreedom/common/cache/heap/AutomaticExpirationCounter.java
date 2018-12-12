package io.ffreedom.common.cache.heap;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import io.ffreedom.common.utils.ThreadUtil;

public class AutomaticExpirationCounter implements Counter<AutomaticExpirationCounter> {

	private AtomicLong value = new AtomicLong(0L);

	// TODO 增加强一致性 使用自旋锁
	// private AtomicBoolean spin;

	private Cache<Long, Long> saveCache;

	public AutomaticExpirationCounter(Duration duration, int total) {
		this.saveCache = CacheBuilder.newBuilder().concurrencyLevel(1).initialCapacity(total).maximumSize(total)
				.expireAfterWrite(duration).removalListener(notification -> subtract((long) notification.getValue()))
				.build();
	}

	@Override
	public AutomaticExpirationCounter add(long delta) {
		saveCache.put(System.nanoTime(), delta);
		value.addAndGet(delta);
		return this;
	}

	@Override
	public AutomaticExpirationCounter subtract(long delta) {
		value.addAndGet(-delta);
		return this;
	}

	@Override
	public long value() {
		return value.get();
	}

	public static void main(String[] args) {

		AutomaticExpirationCounter counter = new AutomaticExpirationCounter(Duration.ofSeconds(5), 1024);

		ThreadUtil.startNewThread(() -> {
			for (;;) {
				counter.add(10);
				ThreadUtil.sleep(1000);
			}
		});

		for (;;) {
			System.out.println(counter.value());
			ThreadUtil.sleep(1000);
		}

	}

}
