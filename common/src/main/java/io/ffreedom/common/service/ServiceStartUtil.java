package io.ffreedom.common.service;

import io.ffreedom.common.utils.ThreadUtil;

public final class ServiceStartUtil {

	private ServiceStartUtil() {
		// TODO Auto-generated constructor stub
	}

	public void startService(ServiceThread serviceThread) {
		ThreadUtil.startNewThread(serviceThread);
	}

	public void startService(ServiceThread serviceThread, String serviceName) {
		ThreadUtil.startNewThread(serviceThread, serviceName);
	}

	public void stopService(ServiceThread serviceThread) {
		serviceThread.stop();
	}

}
