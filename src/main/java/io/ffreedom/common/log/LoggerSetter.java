package io.ffreedom.common.log;

public class LoggerSetter {

	public static void setLogFileName(String logFileName) {
		System.setProperty(LoggerConstant.LOG_FILENAME, logFileName);
	}

	public static void setLogLevel(LogLevel logLevel) {
		System.setProperty(LoggerConstant.LOG_LEVEL, logLevel.name());
	}

	public static void setLogFolder(String logFolder) {
		System.setProperty(LoggerConstant.LOG_FOLDER, logFolder);
	}

}
