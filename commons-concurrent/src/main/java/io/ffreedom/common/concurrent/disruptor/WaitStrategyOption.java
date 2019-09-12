package io.ffreedom.common.concurrent.queue.impl.disruptor;

public enum WaitStrategyOption {

	BusySpin,

	Blocking,

	LiteBlocking,

	TimeoutBlocking,

	LiteTimeoutBlocking,

	PhasedBackoff,

	Sleeping,

	Yielding,

	;

}
