package io.ffreedom.common.functional;

@FunctionalInterface
public interface Processor<IN> {

	void process(IN in) throws Exception;

}
