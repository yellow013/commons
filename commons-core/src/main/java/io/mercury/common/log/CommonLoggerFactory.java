package io.mercury.common.log;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.mercury.common.thread.ThreadUtil;
import io.mercury.common.utils.StringUtil;

public class CommonLoggerFactory {

	private static final String DefaultFolder = "default";

	private static final String DefaultFileName = "jruntime";

	public static Logger getLogger(Class<?> clazz) {
		if (!LoggerSetter.isLogFolderSetted()) {
			String logFolder = System.getProperty(LoggerConstant.LOG4J2_FOLDER);
			if (StringUtil.isNullOrEmpty(logFolder))
				LoggerSetter.setLogFolder(DefaultFolder);
		}
		if (!LoggerSetter.isLogFilenameSetted()) {
			String logFilename = System.getProperty(LoggerConstant.LOG4J2_FILENAME);
			if (StringUtil.isNullOrEmpty(logFilename))
				LoggerSetter.setLogFileName(DefaultFileName);
		}
		if (!LoggerSetter.isLogLevelSetted()) {
			String logLevel = System.getProperty(LoggerConstant.LOG4J2_LEVEL);
			if (StringUtil.isNullOrEmpty(logLevel))
				LoggerSetter.setLogLevel(LogLevel.INFO);
		}
		return LoggerFactory.getLogger(clazz);
	}

	public static void main(String[] args) {

		System.out.println(System.getProperty("user.home"));
		LoggerSetter.setLogFileName("new");

		LoggerSetter.setLogLevel(LogLevel.ERROR);
		Logger logger = getLogger(CommonLoggerFactory.class);

		logger.error("this is error");
		logger.warn("this is warn");
		logger.info("this is info");
		logger.debug("this is debug");
		
		System.out.println(System.getProperty(LoggerConstant.LOG4J2_FOLDER));
		System.out.println(LocalDateTime.now());
		
		for(;;) {
			logger.error("this is error");
			logger.warn("this is warn");
			logger.info("this is info");
			logger.debug("this is debug");
			ThreadUtil.sleep(3000);
		}
		
		

		
	}

}
