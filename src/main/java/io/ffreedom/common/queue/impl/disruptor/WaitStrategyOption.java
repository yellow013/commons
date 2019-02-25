package io.ffreedom.common.queue.impl.disruptor;

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
