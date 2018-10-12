package io.ffreedom.common.log;

import org.slf4j.Logger;

public final class UseLogger {

	public static void error(Logger logger, Exception e) {
		logger.error("***Throw Exception -> {}, Exception Message : [{}]", e.getClass(), e.getMessage(), e);
	}

	public static void error(Logger logger, Exception e, String msg) {
		error(logger, e);
		logger.error(msg);
	}

	public static void error(Logger logger, Exception e, String msgTemplate, Object... args) {
		error(logger, e);
		logger.error(msgTemplate, args);
	}

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(UseLogger.class);

		logger.error("asss {} ---- {}", "3hhh", "hfdsh", new RuntimeException());

	}

}
