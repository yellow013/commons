package io.ffreedom.common.log;

import org.slf4j.Logger;

public final class ErrorLogger {

	public static void error(Logger logger, Exception e) {
		logger.error("***Throw -> [{}], Message -> [{}]", e.getClass().getSimpleName(), e.getMessage(), e);
	}

	public static void error(Logger logger, Exception ex, String msg) {
		error(logger, ex);
		logger.error(msg);
	}

	public static void error(Logger logger, Exception ex, String msgTemplate, Object... args) {
		error(logger, ex);
		logger.error(msgTemplate, args);
	}

}
