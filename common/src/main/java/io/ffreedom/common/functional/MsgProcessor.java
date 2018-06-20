package io.ffreedom.common.functional;

@FunctionalInterface
public interface MsgProcessor<IN> {

	void process(IN in) throws Exception ;
	

}
