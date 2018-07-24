package io.ffreedom.common.functional;

@FunctionalInterface
public interface MsgPipeline<IN, OUT> {

	OUT stream(IN in);

}
