package io.ffreedom.common.log;

import java.time.LocalDateTime;

import org.slf4j.Logger;

import io.ffreedom.common.utils.StringUtil;

public class LoggerFactory {

	private static boolean isLogFilenameSetted;
	private static boolean isLogLevelSetted;
	private static boolean isLogFolderSetted;

	public static Logger getLogger(Class<?> clazz) {
		if (!isLogFolderSetted) {
			String logFolder = System.getProperty(LoggerConstant.LOG_FOLDER);
			if (StringUtil.isNullOrEmpty(logFolder)) {
				LoggerSetter.setLogFolder("default");
			}
		}
		if (!isLogFilenameSetted) {
			String logFilename = System.getProperty(LoggerConstant.LOG_FILENAME);
			if (StringUtil.isNullOrEmpty(logFilename)) {
				LoggerSetter.setLogFileName("java.runtime");
			}
		}
		if (!isLogLevelSetted) {
			String logLevel = System.getProperty(LoggerConstant.LOG_LEVEL);
			if (StringUtil.isNullOrEmpty(logLevel)) {
				LoggerSetter.setLogLevel(LogLevel.INFO);
			}
		}
		return org.slf4j.LoggerFactory.getLogger(clazz);
	}

	public static void main(String[] args) {

		System.out.println(System.getProperty("user.home"));

		LoggerSetter.setLogFileName("new");

		LoggerSetter.setLogLevel(LogLevel.INFO);

		Logger logger = getLogger(LoggerFactory.class);

		logger.info("777");

		logger.info("778");

		logger.debug("779");

		System.out.println(System.getProperty(LoggerConstant.LOG_FOLDER));

		System.out.println(LocalDateTime.now());
	}

}
