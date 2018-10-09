package io.ffreedom.common.cache.heap.map;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import io.ffreedom.common.log.LoggerFactory;

public class GuavaCacheMap<K, V> {

	private LoadingCache<K, V> loadingCache;
	private CacheRefresher<K, V> refresher;

	private long maximumSize;
	private long duration;
	private TimeUnit timeUnit;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private GuavaCacheMap(CacheMapBuilder builder, CacheRefresher<K, V> refresher) {
		this.maximumSize = builder.maximumSize;
		this.duration = builder.duration;
		this.timeUnit = builder.timeUnit;
		this.refresher = refresher;
		initLoadingCache();
	}

	private void initLoadingCache() {
		loadingCache = CacheBuilder.newBuilder().maximumSize(maximumSize).expireAfterAccess(duration, timeUnit)
				.build(new CacheLoader<K, V>() {
					@Override
					public V load(K key) throws Exception {
						return refresher.refresh(key);
					}
				});
	}

	public static CacheMapBuilder builder() {
		return new CacheMapBuilder();
	}

	public V get(K k) {
		try {
			return loadingCache.get(k);
		} catch (ExecutionException e) {
			logger.error("GuavaCacheMap.get -> [" + k + "] : " + e.getMessage());
			logger.error(e.getStackTrace());
			return null;
		}
	}

	/**
	 * Builder for GuavaCacheMap
	 * 
	 * @author phoenix
	 *
	 */
	public static class CacheMapBuilder {
		private long maximumSize = 1024;
		private long duration = 2;
		private TimeUnit timeUnit = TimeUnit.HOURS;

		public CacheMapBuilder maximumSize(long maximumSize) {
			this.maximumSize = maximumSize;
			return this;
		}

		public CacheMapBuilder expirePeriod(long duration, TimeUnit timeUnit) {
			this.duration = duration;
			this.timeUnit = timeUnit;
			return this;
		}

		public <K, V> GuavaCacheMap<K, V> build(CacheRefresher<K, V> refresher) {
			return new GuavaCacheMap<>(this, refresher);
		}

	}

	/**
	 * Test Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		GuavaCacheMap<String, String> cacheMap = GuavaCacheMap.builder().maximumSize(100).expirePeriod(1, TimeUnit.DAYS)
				.build((key) -> {
					System.out.println("load into cache!");
					return "hello " + key + "!";
				});
		String string1 = cacheMap.get("1");
		String string2 = cacheMap.get("1");
		System.out.println(string1);
		System.out.println(string2);
	}

}
