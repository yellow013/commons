package io.ffreedom.common.log;

import org.slf4j.Logger;

public final class UseLogger {

	public static void debug(Logger logger, String msg) {
		logger.debug(msg);
	}

	public static void debug(Logger logger, String msgTemplate, Object... args) {
		logger.debug(msgTemplate, args);
	}

	public static void info(Logger logger, String msg) {
		logger.info(msg);
	}

	public static void info(Logger logger, String msgTemplate, Object... args) {
		logger.info(msgTemplate, args);
	}

	public static void error(Logger logger, Exception e) {
		logger.error("***Throw {}, Message : [{}]", e.getClass().getSimpleName(), e.getMessage(), e);
	}

	public static void error(Logger logger, Exception e, String msg) {
		logger.error(msg);
		error(logger, e);
	}

	public static void error(Logger logger, Exception e, String msgTemplate, Object... args) {
		logger.error(msgTemplate, args);
		error(logger, e);
	}

	public static void error(Logger logger, String msg) {
		logger.error(msg);
	}

	public static void error(Logger logger, String msgTemplate, Object... args) {
		logger.error(msgTemplate, args);
	}

}
