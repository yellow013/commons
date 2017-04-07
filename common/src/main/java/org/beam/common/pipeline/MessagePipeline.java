package org.beam.common.pipeline;

public interface MessagePipeline<IN, OUT> {

	OUT stream(IN in);

}
