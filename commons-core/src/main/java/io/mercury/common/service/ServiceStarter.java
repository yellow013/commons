package io.mercury.common.service;

import io.mercury.common.thread.ThreadUtil;

public final class ServiceStarter {

	private ServiceStarter() {

	}

	public static void startService(ServiceThread serviceThread) {
		ThreadUtil.startNewThread(serviceThread);
	}

	public static void startService(ServiceThread serviceThread, String serviceName) {
		ThreadUtil.startNewThread(serviceThread, serviceName);
	}

	public static void stopService(ServiceThread serviceThread) {
		serviceThread.stop();
	}

}
