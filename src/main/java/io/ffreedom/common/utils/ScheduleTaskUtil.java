package io.ffreedom.common.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

	public static void singleThreadSchedule(LocalDateTime firstTime, Runnable runnable) {
		singleThreadSchedule(TimeUnit.MILLISECONDS, Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				runnable);
	}

	public static void singleThreadSchedule(TimeUnit timeUnit, long delay, Runnable runnable) {
		InnerSingleThreadExecutor.schedule(runnable, delay, timeUnit);
	}

	public static void singleThreadScheduleWithFixedDelay(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runnable runnable) {
		singleThreadScheduleWithFixedDelay(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	public static void singleThreadScheduleWithFixedDelay(TimeUnit timeUnit, long delay, long period,
			Runnable runnable) {
		InnerSingleThreadExecutor.scheduleWithFixedDelay(runnable, delay, period, timeUnit);
	}

	/**
	 * 
	 * @param firstTime
	 * @param timeUnit
	 * @param period
	 * @param runnable
	 */
	public static void singleThreadScheduleAtFixedRate(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runnable runnable) {
		singleThreadScheduleAtFixedRate(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	/**
	 * 
	 * @param timeUnit
	 * @param delay
	 * @param period
	 * @param runnable
	 */
	public static void singleThreadScheduleAtFixedRate(TimeUnit timeUnit, long delay, long period, Runnable runnable) {
		InnerSingleThreadExecutor.scheduleAtFixedRate(runnable, delay, period, timeUnit);
	}

	
	private static ScheduledExecutorService InnerMultipleThreadExecutor = Executors.newScheduledThreadPool(
			Runtime.getRuntime().availableProcessors() + 2,
			runnable -> new Thread(runnable, "MultipleThreadScheduledExecutorService"));

	/**
	 * Creates and executes a one-shot action that becomes enabled after the given
	 * delay.
	 * 
	 * @param firstTime
	 * @param runnable
	 */
	public static void multipleThreadSchedule(LocalDateTime firstTime, Runnable runnable) {
		multipleThreadSchedule(TimeUnit.MILLISECONDS, Duration.between(LocalDateTime.now(), firstTime).toMillis(),
				runnable);
	}

	/**
	 * Creates and executes a one-shot action that becomes enabled after the given
	 * delay.
	 * 
	 * @param timeUnit
	 * @param delay
	 * @param runnable
	 */
	public static void multipleThreadSchedule(TimeUnit timeUnit, long delay, Runnable runnable) {
		InnerMultipleThreadExecutor.schedule(runnable, delay, timeUnit);
	}

	/**
	 * The given delay between the termination of one execution and the commencement
	 * of the next.
	 * 
	 * @param firstTime
	 * @param timeUnit
	 * @param period
	 * @param runnable
	 */
	public static void multipleThreadScheduleWithFixedDelay(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runnable runnable) {
		multipleThreadScheduleWithFixedDelay(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	/**
	 * The given delay between the termination of one execution and the commencement
	 * of the next.
	 * 
	 * @param timeUnit
	 * @param delay
	 * @param period
	 * @param runnable
	 */
	public static void multipleThreadScheduleWithFixedDelay(TimeUnit timeUnit, long delay, long period,
			Runnable runnable) {
		InnerMultipleThreadExecutor.scheduleWithFixedDelay(runnable, delay, period, timeUnit);
	}

	/**
	 * That is executions will commence after initialDelay then initialDelay+period,
	 * then initialDelay + 2 * period, and so on.
	 * 
	 * @param firstTime
	 * @param timeUnit
	 * @param period
	 * @param runnable
	 */
	public static void multipleThreadScheduleAtFixedRate(LocalDateTime firstTime, TimeUnit timeUnit, long period,
			Runnable runnable) {
		multipleThreadScheduleAtFixedRate(TimeUnit.MILLISECONDS,
				Duration.between(LocalDateTime.now(), firstTime).toMillis(), timeUnit.toMillis(period), runnable);
	}

	/**
	 * That is executions will commence after initialDelay then initialDelay+period,
	 * then initialDelay + 2 * period, and so on.
	 * 
	 * @param timeUnit
	 * @param delay
	 * @param period
	 * @param runnable
	 */
	public static void multipleThreadScheduleAtFixedRate(TimeUnit timeUnit, long delay, long period,
			Runnable runnable) {
		InnerMultipleThreadExecutor.scheduleAtFixedRate(runnable, delay, period, timeUnit);
	}

	public static void main(String[] args) {

		multipleThreadScheduleAtFixedRate(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 38, 00)), TimeUnit.SECONDS,
				3, () -> System.out.println(12345));

	}

}
