package io.ffreedom.common.queue.disruptor;

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
