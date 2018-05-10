package io.ffreedom.common.env;

public interface SysProperty {
	
	String OS_NAME = System.getProperty("os.name");
	
	String USER_NAME = System.getProperty("user.name");

	String USER_TIMEZONE = System.getProperty("user.timezone");

	String USER_HOME = System.getProperty("user.home");

	String USER_DIR = System.getProperty("user.dir");

}
