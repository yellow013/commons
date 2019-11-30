package io.mercury.common.thread;

import java.util.concurrent.ThreadPoolExecutor;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;

public final class ShutdownHooks {

	private MutableList<Runnable> runnables = MutableLists.newFastList(64);

	private static final ShutdownHooks INSTANCE = new ShutdownHooks();

	private ShutdownHooks() {
		Runtime.getRuntime().addShutdownHook(new Thread(this::executeShutdownHook, "ShutdownHooksThread"));
	}

	private void executeShutdownHook() {
		System.out.println("Start execution all shutdown hook");
		ThreadPoolExecutor executor = CommonThreadPool.newBuilder().build();
		for (Runnable runnable : runnables)
			executor.execute(runnable);
		executor.shutdown();
		while (executor.isTerminated())
			ThreadUtil.sleepIgnoreException(100);
		System.out.println("all shutdown hook execution completed");
	}

	public static void addShutdownHook(Runnable runnable) {
		INSTANCE.runnables.add(runnable);
	}
	
	public static void main(String[] args) {
		
		System.out.println("TEST");
		
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子1")));
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子2")));
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子3")));
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子4")));
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子5")));
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子6")));
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子7")));
		ShutdownHooks.addShutdownHook(new Thread(()-> System.out.println("关闭钩子8")));
		
		
		
		
	}

}
