package io.mercury.common.concurrent.signal;

import java.time.Duration;

public final class TimedAnnunciator {

	private final long interval;

	public TimedAnnunciator(Duration interval) {
		this.interval = interval.getNano();
	}

}
