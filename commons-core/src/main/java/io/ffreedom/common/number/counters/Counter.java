package io.ffreedom.common.number.counters;

/**
 * 累加计数器接口
 * 
 * @author yellow013
 *
 * @param <T>
 */
public interface Counter<T extends Counter<T>> {

	T add(long tag, long delta);

	T subtract(long tag, long delta);

	T removeHistoryDelta(long tag);

	T removeHistoryDelta(long tag, long delta);

	default T increment(long tag) {
		return add(tag, 1);
	}

	default T decrement(long tag) {
		return subtract(tag, 1);
	}

	long getValue();

}
