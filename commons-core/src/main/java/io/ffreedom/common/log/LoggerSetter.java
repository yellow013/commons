package io.ffreedom.common.log;

public class LoggerSetter {

	private static volatile boolean isLogFolderSetted;
	private static volatile boolean isLogFilenameSetted;
	private static volatile boolean isLogLevelSetted;

	public static boolean isLogFolderSetted() {
		return isLogFolderSetted;
	}

	public static boolean isLogFilenameSetted() {
		return isLogFilenameSetted;
	}

	public static boolean isLogLevelSetted() {
		return isLogLevelSetted;
	}

	public static void setLogFolder(String logFolder) {
		System.setProperty(LoggerConstant.LOG4_FOLDER, logFolder);
		isLogFolderSetted = true;
	}

	public static void setLogFileName(String logFileName) {
		System.setProperty(LoggerConstant.LOG4_FILENAME, logFileName);
		isLogFilenameSetted = true;
	}

	public static void setLogLevel(LogLevel logLevel) {
		System.setProperty(LoggerConstant.LOG4_LEVEL, logLevel.name());
		isLogLevelSetted = true;
	}

}
