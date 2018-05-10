package io.ffreedom.common.comparison;

public interface ComparableBean<T> extends Comparable<T> {

	void setComparisonFunction(ComparisonFunction<T> function);

}
