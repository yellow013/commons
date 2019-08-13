package io.ffreedom.common.functional;

@FunctionalInterface
public interface Processor<T> {

	void process(T input) throws Exception;

}
