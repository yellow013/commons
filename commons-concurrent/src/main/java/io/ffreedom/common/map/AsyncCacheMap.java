package io.ffreedom.common.map;

import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.ffreedom.common.collections.MutableMaps;
import io.ffreedom.common.queue.api.SCQueue;
import io.ffreedom.common.queue.impl.ArrayBlockingMPSCQueue;

public final class AsyncCacheMap<K, V> {

	private MutableMap<K, V> mutableMap = MutableMaps.newUnifiedMap(256);

	private MutableLongObjectMap<Consumer<V>> consumerMap = MutableMaps.newLongObjectHashMap(128);

	private String cacheName;

	private SCQueue<ExecEvent> execQueue;

	private SCQueue<QueryResult> queryQueue;

	// private ExecutorService executor = Executors.newSingleThreadExecutor();

	private final class ExecEvent {

		private K key;
		private long nanoTime;

		public ExecEvent(K key, long nanoTime) {
			this.key = key;
			this.nanoTime = nanoTime;
		}

		public ExecEvent(K key) {
			this.key = key;
		}
	}

	private final class QueryResult {

		private V value;
		private long nanoTime;

		public QueryResult(V value, long nanoTime) {
			this.value = value;
			this.nanoTime = nanoTime;
		}

	}

	private Function<K, V> refresher;

	public AsyncCacheMap(String cacheName, Function<K, V> refresher) {
		if (refresher == null)
			throw new IllegalArgumentException("refresher is can't null...");
		this.refresher = refresher;
		this.cacheName = cacheName == null ? "AsyncCacheMap-" + hashCode() : cacheName;
		this.execQueue = ArrayBlockingMPSCQueue.autoStartQueue(this.cacheName + "-execQueue", 64,
				event -> asyncExec(event));
		this.queryQueue = ArrayBlockingMPSCQueue.autoStartQueue(this.cacheName + "-execQueue", 64,
				result -> consumerMap.remove(result.nanoTime).accept(result.value));
	}

	private void asyncExec(ExecEvent event) {
		if (event.nanoTime == 0L) {
			V value = refresher.apply(event.key);
			if (value != null)
				mutableMap.put(event.key, value);
		} else {
			V v = mutableMap.get(event.key);
			queryQueue.enqueue(new QueryResult(v, event.nanoTime));
		}
	}

	public void asyncUpdate(K key) {
		execQueue.enqueue(new ExecEvent(key));
	}

	public void asyncGet(K key, Consumer<V> consumer) {
		long nanoTime = System.nanoTime();
		consumerMap.put(nanoTime, consumer);
		execQueue.enqueue(new ExecEvent(key, nanoTime));
	}

	public static void main(String[] args) {
		AsyncCacheMap<Integer, String> asyncCacheMap = new AsyncCacheMap<>("TEST", k -> k + "*&");
		for (int i = 0; i < 100; i++) {
			asyncCacheMap.asyncUpdate(i);
			asyncCacheMap.asyncGet(i, v -> System.out.println(v));
		}
		System.out.println(1111);
	}

}
