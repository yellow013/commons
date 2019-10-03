package io.ffreedom.commons.chronicle.base;

import io.ffreedom.common.annotations.lang.MayThrowRuntimeException;
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

	/**
	 * Move cursor to input epoch seconds.
	 * 
	 * @param epochSecond
	 * @return
	 */
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

	/**
	 * Get next element of current cursor position.
	 * 
	 * @return
	 * @throws ChronicleReadException
	 */
	@MayThrowRuntimeException
	public T next() throws ChronicleReadException {
		try {
			return next0();
		} catch (Exception e) {
			throw new ChronicleReadException(e.getMessage(), e);
		}
	}

	protected abstract T next0();

}
