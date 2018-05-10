package io.ffreedom.common.callback.msg;

@FunctionalInterface
public interface MsgProcessor<IN> {

	void process(IN in) throws Exception;

}
