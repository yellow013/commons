package io.ffreedom.common.log;

import org.slf4j.Logger;

public final class ErrorLogger {

	public static void error(Logger logger, Exception ex) {
		logger.error("***Throw -> [{}], ExceptionMessage -> [{}]", ex.getClass().getSimpleName(), ex.getMessage(), ex);
	}

	public static void error(Logger logger, Exception ex, String msg) {
		logger.error(msg);
		error(logger, ex);
	}

	public static void error(Logger logger, Exception ex, String msgTemplate, Object... args) {
		logger.error(msgTemplate, args);
		error(logger, ex);
	}

}
