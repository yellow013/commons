package io.ffreedom.common.log;

import java.time.LocalDateTime;

import org.slf4j.Logger;

public class LoggerFactory {

	public static Logger getLogger(Class<?> clazz) {
		String logFilename = System.getProperty(LoggerConstant.LOG_FILENAME);
		if (logFilename == null || logFilename.equals("")) {
			LoggerSetter.setLogFileName("java.runtime");
		}
		String logLevel = System.getProperty(LoggerConstant.LOG_LEVEL);
		if (logLevel == null || logLevel.equals("")) {
			LoggerSetter.setLogLevel(LogLevel.INFO);
		}
		String projectName = System.getProperty(LoggerConstant.PROJECT_NAME);
		if (projectName == null || projectName.equals("")) {
			LoggerSetter.setProjectName("unset");
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

		System.out.println(System.getProperty(LoggerConstant.PROJECT_NAME));

		System.out.println(LocalDateTime.now());
	}

}
