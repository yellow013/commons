package org.beam.common.pipeline;

public interface Processor<IN> {

	void process(IN in) throws RuntimeException;

}
