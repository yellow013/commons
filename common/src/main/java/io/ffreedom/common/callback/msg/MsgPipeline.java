package io.ffreedom.common.callback.msg;

@FunctionalInterface
public interface MsgPipeline<IN, OUT>{

	OUT stream(IN in);

}
