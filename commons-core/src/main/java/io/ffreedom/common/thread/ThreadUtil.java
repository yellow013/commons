package io.ffreedom.common.thread;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.log.ErrorLogger;

public final class ThreadUtil {

	private ThreadUtil() {
	}

	private static Logger logger = CommonLoggerFactory.getLogger(ThreadUtil.class);

	public static Thread newThread(Runnable runnable) {
		return new Thread(runnable);
	}

	public static Thread newThread(Runnable runnable, String threadName) {
		return new Thread(runnable, threadName);
	}

	public static Thread newMaxPriorityThread(Runnable runnable) {
		return setThreadPriority(newThread(runnable), Thread.MAX_PRIORITY);
	}

	public static Thread newMaxPriorityThread(Runnable runnable, String threadName) {
		return setThreadPriority(newThread(runnable, threadName), Thread.MAX_PRIORITY);
	}

	public static Thread newMinPriorityThread(Runnable runnable) {
		return setThreadPriority(newThread(runnable), Thread.MIN_PRIORITY);
	}

	public static Thread newMinPriorityThread(Runnable runnable, String threadName) {
		return setThreadPriority(newThread(runnable, threadName), Thread.MIN_PRIORITY);
	}

	private static Thread setThreadPriority(Thread thread, int priority) {
		thread.setPriority(priority);
		return thread;
	}

	public static Thread startNewThread(Runnable runnable) {
		return startThread(newThread(runnable));
	}

	public static Thread startNewThread(Runnable runnable, String threadName) {
		return startThread(newThread(runnable, threadName));
	}

	public static Thread startNewMaxPriorityThread(Runnable runnable) {
		return startThread(newMaxPriorityThread(runnable));
	}

	public static Thread startNewMaxPriorityThread(Runnable runnable, String threadName) {
		return startThread(newMaxPriorityThread(runnable, threadName));
	}

	public static Thread startNewMinPriorityThread(Runnable runnable) {
		return startThread(newMinPriorityThread(runnable));
	}

	public static Thread startNewMinPriorityThread(Runnable runnable, String threadName) {
		return startThread(newMinPriorityThread(runnable, threadName));
	}

	private static Thread startThread(Thread thread) {
		thread.start();
		return thread;
	}

	public static void sleepIgnoreException(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleepIgnoreException(long millis, int nanos) {
		try {
			Thread.sleep(millis, nanos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			ErrorLogger.error(logger, e, "Call ThreadUtil.sleep(millis==[{}]) throw InterruptedException -> {}", millis,
					e.getMessage());
		}
	}

	public static void sleep(long millis, int nanos) {
		try {
			Thread.sleep(millis, nanos);
		} catch (InterruptedException e) {
			ErrorLogger.error(logger, e,
					"Call ThreadUtil.sleep(millis==[{}], nanos==[{}]) throw InterruptedException -> {}", millis, nanos,
					e.getMessage());
		}
	}

	public static void sleep(TimeUnit timeUnit, long time) {
		try {
			timeUnit.sleep(time);
		} catch (InterruptedException e) {
			ErrorLogger.error(logger, e,
					"Call ThreadUtil.sleep(time==[{}], timeUnit==[{}]) throw InterruptedException -> {}", time,
					timeUnit, e.getMessage());
		}
	}

	public static void join() {
		join(Thread.currentThread());
	}

	public static void join(Thread thread) {
		try {
			thread.join();
		} catch (InterruptedException e) {
			ErrorLogger.error(logger, e, "Thread join throw InterruptedException from thread -> id==[{}] name==[{}]",
					thread.getId(), thread.getName());
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
