package io.ffreedom.common.log;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.slf4j.Log4jLogger;
import org.apache.logging.slf4j.Log4jLoggerFactory;

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
		org.slf4j.Logger
		return LogManager.getLogger(clazz);
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
