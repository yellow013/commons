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
		}, timeUnit.toMillis(delay));
		return timer;
	}

	public static Timer startNewCycleTask(LocalDateTime firstTime, TimeUnit timeUnit, long period, Runner runner) {
		return startNewCycleTask(TimeUnit.MILLISECONDS, Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				timeUnit.toMillis(period), runner);
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
		}, timeUnit.toMillis(delay), timeUnit.toMillis(period));
		return timer;
	}

	public static Timer startNewFixedRateCycleTask(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runner runner) {
		return startNewFixedRateCycleTask(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runner);
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
		}), timeUnit.toMillis(delay), timeUnit.toMillis(period));
		return timer;
	}

	private static ScheduledExecutorService InnerSingleThreadExecutor = Executors
			.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "SingleThreadScheduledExecutorService"));

	public static void addTaskToSingleThreadExecutor(LocalDateTime firstTime, Runnable runnable) {
		addTaskToSingleThreadExecutor(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), runnable);
	}

	public static void addTaskToSingleThreadExecutor(TimeUnit timeUnit, long delay, Runnable runnable) {
		InnerSingleThreadExecutor.schedule(runnable, delay, timeUnit);
	}

	public static void addCycleTaskToSingleThreadExecutor(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runnable runnable) {
		addCycleTaskToSingleThreadExecutor(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	public static void addCycleTaskToSingleThreadExecutor(TimeUnit timeUnit, long delay, long period,
			Runnable runnable) {
		InnerSingleThreadExecutor.scheduleWithFixedDelay(runnable, delay, period, timeUnit);
	}

	public static void addFixedRateCycleTaskToSingleThreadExecutor(LocalDateTime firstTime, TimeUnit timeUnit,
			long period, Runnable runnable) {
		addFixedRateCycleTaskToSingleThreadExecutor(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	public static void addFixedRateCycleTaskToSingleThreadExecutor(TimeUnit timeUnit, long delay, long period,
			Runnable runnable) {
		InnerSingleThreadExecutor.scheduleAtFixedRate(runnable, delay, period, timeUnit);
	}

	private static ScheduledExecutorService InnerMultipleThreadExecutor = Executors.newScheduledThreadPool(
			Runtime.getRuntime().availableProcessors(),
			runnable -> new Thread(runnable, "MultipleThreadScheduledExecutorService"));

	public static void addTaskToMultipleThreadExecutor(LocalDateTime firstTime, Runnable runnable) {
		addTaskToMultipleThreadExecutor(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), runnable);
	}

	public static void addTaskToMultipleThreadExecutor(TimeUnit timeUnit, long delay, Runnable runnable) {
		InnerMultipleThreadExecutor.schedule(runnable, delay, timeUnit);
	}

	public static void addCycleTaskToMultipleThreadExecutor(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runnable runnable) {
		addCycleTaskToMultipleThreadExecutor(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	public static void addCycleTaskToMultipleThreadExecutor(TimeUnit timeUnit, long delay, long period,
			Runnable runnable) {
		InnerMultipleThreadExecutor.scheduleWithFixedDelay(runnable, delay, period, timeUnit);
	}

	public static void addFixedRateCycleTaskToMultipleThreadExecutor(LocalDateTime firstTime, TimeUnit timeUnit,
			long period, Runnable runnable) {
		addFixedRateCycleTaskToMultipleThreadExecutor(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	public static void addFixedRateCycleTaskToMultipleThreadExecutor(TimeUnit timeUnit, long delay, long period,
			Runnable runnable) {
		InnerMultipleThreadExecutor.scheduleAtFixedRate(runnable, delay, period, timeUnit);
	}

}