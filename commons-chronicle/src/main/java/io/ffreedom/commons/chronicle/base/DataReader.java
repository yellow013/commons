package io.ffreedom.common.concurrent.persistence.base;

import net.openhft.chronicle.queue.ExcerptTailer;

public abstract class DataReader<T> {

	protected ExcerptTailer tailer;
	private FileCycle fileCycle;

	protected DataReader(ExcerptTailer tailer, FileCycle fileCycle) {
		super();
		this.tailer = tailer;
		this.fileCycle = fileCycle;
	}

	public ExcerptTailer getTailer() {
		return tailer;
	}

	public boolean moveTo(long epochSecond) {
		return tailer.moveToIndex(fileCycle.calculateIndex(epochSecond));
	}

	public void moveToStart() {
		tailer.toStart();
	}

	public void moveToEnd() {
		tailer.toEnd();
	}

	public int currentCycle() {
		return tailer.cycle();
	}

	public T next() throws Exception {
		return next0();
	}

	abstract protected T next0();

}
