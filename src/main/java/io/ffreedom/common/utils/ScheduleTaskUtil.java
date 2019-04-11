package io.ffreedom.common.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import io.ffreedom.common.functional.Runner;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.log.ErrorLogger;

public final class ScheduleTaskUtil {

	private ScheduleTaskUtil() {
	}

	private static Logger logger = CommonLoggerFactory.getLogger(ScheduleTaskUtil.class);

	public static Timer startNewDelayTask(LocalDateTime firstTime, Runner runner) {
		return startNewDelayTask(TimeUnit.MILLISECONDS, Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				runner);
	}

	public static Timer startNewDelayTask(TimeUnit timeUnit, long delay, Runner runner) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					runner.run();
				} catch (Exception e) {
					ErrorLogger.error(logger, e, "TimerTask runner throw Exception -> {}", e.getMessage());
				}
			}
		}, timeUnit == TimeUnit.MILLISECONDS ? delay : timeUnit.toMillis(delay));
		return timer;
	}

	public static Timer startNewCycleTask(LocalDateTime firstTime, TimeUnit timeUnit, long period, Runner runner) {
		return startNewCycleTask(TimeUnit.MILLISECONDS, Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				timeUnit == TimeUnit.MILLISECONDS ? period : timeUnit.toMillis(period), runner);
	}

	public static Timer startNewCycleTask(TimeUnit timeUnit, long delay, long period, Runner runner) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					runner.run();
				} catch (Exception e) {
					ErrorLogger.error(logger, e, "TimerTask runner throw Exception -> {}", e.getMessage());
				}
			}
		}, timeUnit == TimeUnit.MILLISECONDS ? delay : timeUnit.toMillis(delay),
				timeUnit == TimeUnit.MILLISECONDS ? period : timeUnit.toMillis(period));
		return timer;
	}

	public static Timer startNewFixedRateCycleTask(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runner runner) {
		return startNewFixedRateCycleTask(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				timeUnit == TimeUnit.MILLISECONDS ? period : timeUnit.toMillis(period), runner);
	}

	public static Timer startNewFixedRateCycleTask(TimeUnit timeUnit, long delay, long period, Runner runner) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate((new TimerTask() {
			@Override
			public void run() {
				try {
					runner.run();
				} catch (Exception e) {
					ErrorLogger.error(logger, e, "TimerTask runner throw Exception -> {}", e.getMessage());
				}
			}
		}), timeUnit == TimeUnit.MILLISECONDS ? delay : timeUnit.toMillis(delay),
				timeUnit == TimeUnit.MILLISECONDS ? period : timeUnit.toMillis(period));
		return timer;
	}

	private static ScheduledExecutorService InnerSingleThreadExecutor = Executors
			.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "SingleThreadScheduledExecutorService"));

	public static void addTaskToSingleThreadExecutor(LocalDateTime firstTime, Runnable runnable) {
		addTaskToSingleThreadExecutor(Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				TimeUnit.MILLISECONDS, runnable);
	}

	public static void addTaskToSingleThreadExecutor(long delay, TimeUnit timeUnit, Runnable runnable) {
		InnerSingleThreadExecutor.schedule(runnable, delay, timeUnit);
	}

	public static void addCycleTaskToSingleThreadExecutor(LocalDateTime firstTime, long period, TimeUnit timeUnit,
			Runnable runnable) {
		addCycleTaskToSingleThreadExecutor(Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				timeUnit.toMillis(period), TimeUnit.MILLISECONDS, runnable);
	}

	public static void addCycleTaskToSingleThreadExecutor(long delay, long period, TimeUnit timeUnit,
			Runnable runnable) {
		InnerSingleThreadExecutor.scheduleWithFixedDelay(runnable, delay, period, timeUnit);
	}

	public static void addFixedRateCycleTaskToSingleThreadExecutor(LocalDateTime firstTime, long period,
			TimeUnit timeUnit, Runnable runnable) {
		addFixedRateCycleTaskToSingleThreadExecutor(Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				timeUnit.toMillis(period), TimeUnit.MILLISECONDS, runnable);
	}

	public static void addFixedRateCycleTaskToSingleThreadExecutor(long delay, long period, TimeUnit timeUnit,
			Runnable runnable) {
		InnerSingleThreadExecutor.scheduleAtFixedRate(runnable, delay, period, timeUnit);
	}

	private static ScheduledExecutorService InnerMultipleThreadExecutor = Executors.newScheduledThreadPool(
			Runtime.getRuntime().availableProcessors(),
			runnable -> new Thread(runnable, "MultipleThreadScheduledExecutorService"));

	public static void addTaskToMultipleThreadExecutor(LocalDateTime firstTime, Runnable runnable) {
		addTaskToMultipleThreadExecutor(Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				TimeUnit.MILLISECONDS, runnable);
	}

	public static void addTaskToMultipleThreadExecutor(long delay, TimeUnit timeUnit, Runnable runnable) {
		InnerMultipleThreadExecutor.schedule(runnable, delay, timeUnit);
	}

	public static void addCycleTaskToMultipleThreadExecutor(LocalDateTime firstTime, long period, TimeUnit timeUnit,
			Runnable runnable) {
		addCycleTaskToMultipleThreadExecutor(Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				timeUnit.toMillis(period), TimeUnit.MILLISECONDS, runnable);
	}

	public static void addCycleTaskToMultipleThreadExecutor(long delay, long period, TimeUnit timeUnit,
			Runnable runnable) {
		InnerMultipleThreadExecutor.scheduleWithFixedDelay(runnable, delay, period, timeUnit);
	}

	public static void addFixedRateCycleTaskToMultipleThreadExecutor(LocalDateTime firstTime, long period,
			TimeUnit timeUnit, Runnable runnable) {
		addFixedRateCycleTaskToMultipleThreadExecutor(Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				timeUnit.toMillis(period), TimeUnit.MILLISECONDS, runnable);
	}

	public static void addFixedRateCycleTaskToMultipleThreadExecutor(long delay, long period, TimeUnit timeUnit,
			Runnable runnable) {
		InnerMultipleThreadExecutor.scheduleAtFixedRate(runnable, delay, period, timeUnit);
	}

}
