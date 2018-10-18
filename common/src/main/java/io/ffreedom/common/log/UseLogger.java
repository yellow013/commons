package io.ffreedom.common.log;

import org.slf4j.Logger;

public final class UseLogger {

	public static void error(Logger logger, Exception e) {
		logger.error("***Throw Exception -> {}, Exception Message : [{}]", e.getClass().getSimpleName(), e.getMessage(),
				e);
	}

	public static void error(Logger logger, Exception e, String msg) {
		logger.error(msg);
		error(logger, e);
	}

	public static void error(Logger logger, Exception e, String msgTemplate, Object... args) {
		logger.error(msgTemplate, args);
		error(logger, e);
	}

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(UseLogger.class);

		logger.error("asss {} ---- {}", "3hhh", "hfdsh", new RuntimeException());

	}

}
