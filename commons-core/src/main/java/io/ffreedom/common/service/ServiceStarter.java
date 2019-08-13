package io.ffreedom.common.service;

import io.ffreedom.common.utils.ThreadUtil;

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
