package io.ffreedom.common.log;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.ffreedom.common.utils.StringUtil;

public class CommonLoggerFactory {

	public static Logger getLogger(Class<?> clazz) {
		if (!LoggerSetter.isLogFolderSetted()) {
			String logFolder = System.getProperty(LoggerConstant.LOG_FOLDER);
			if (StringUtil.isNullOrEmpty(logFolder))
				LoggerSetter.setLogFolder("default");
		}
		if (!LoggerSetter.isLogFilenameSetted()) {
			String logFilename = System.getProperty(LoggerConstant.LOG_FILENAME);
			if (StringUtil.isNullOrEmpty(logFilename))
				LoggerSetter.setLogFileName("java.runtime");
		}
		if (!LoggerSetter.isLogLevelSetted()) {
			String logLevel = System.getProperty(LoggerConstant.LOG_LEVEL);
			if (StringUtil.isNullOrEmpty(logLevel))
				LoggerSetter.setLogLevel(LogLevel.INFO);
		}
		return LoggerFactory.getLogger(clazz);
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.home"));

		LoggerSetter.setLogFileName("new");
		LoggerSetter.setLogLevel(LogLevel.INFO);
		Logger logger = getLogger(CommonLoggerFactory.class);

		logger.warn("777");
		logger.info("778");
		logger.debug("779");

		System.out.println(System.getProperty(LoggerConstant.LOG_FOLDER));
		System.out.println(LocalDateTime.now());
	}

}
