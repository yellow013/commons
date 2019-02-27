package io.ffreedom.common.number.api;

public interface Num<N extends Num<?>> extends Comparable<N> {

	int IntZero = 0;
	float FloatZero = 0.0F;
	long LongZero = 0L;
	double DoubleZero = 0.0D;

	N plusBy(Num<?> augend);

	N minusBy(Num<?> subtrahend);

	N multipliedBy(Num<?> multiplicand);

	N dividedBy(Num<?> divisor);

	N powBy(int n);

	boolean isZero();

	boolean isPositive();

	boolean isPositiveOrZero();

	boolean isNegative();

	boolean isNegativeOrZero();

	boolean isEqual(Num<?> other);

	boolean isGreaterThan(Num<?> other);

	boolean isGreaterThanOrEqual(Num<?> other);

	boolean isLessThan(Num<?> other);

	boolean isLessThanOrEqual(Num<?> other);

	default int min(int other) {
		return Math.min(intValue(), other);
	}

	default int max(int other) {
		return Math.max(intValue(), other);
	}

	default long min(long other) {
		return Math.min(longValue(), other);
	}

	default long max(long other) {
		return Math.max(longValue(), other);
	}

	default float min(float other) {
		return Math.min(floatValue(), other);
	}

	default float max(float other) {
		return Math.max(floatValue(), other);
	}

	default double min(double other) {
		return Math.min(doubleValue(), other);
	}

	default double max(double other) {
		return Math.max(doubleValue(), other);
	}

	default boolean isNaN() {
		return false;
	}

	double doubleValue();

	int intValue();

	long longValue();

	float floatValue();

	@Override
	int hashCode();

	@Override
	String toString();

	@Override
	boolean equals(Object obj);

	@Override
	default int compareTo(N o) {
		return isGreaterThan(o) ? -1 : isLessThan(o) ? 1 : 0;
	}

}
