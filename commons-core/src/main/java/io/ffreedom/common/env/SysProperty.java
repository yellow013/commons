package io.ffreedom.common.env;

public interface SysProperty {

	String OS_NAME = System.getProperty("os.name");

	String JAVA_HOME = System.getProperty("java.home");

	String JAVA_CLASS_PATH = System.getProperty("java.class.path");

	String JAVA_VERSION = System.getProperty("java.version");

	String JAVA_VM_NAME = System.getProperty("java.vm.name");

	String JAVA_RUNTIME_NAME = System.getProperty("java.runtime.name");

	String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");

	String JAVA_IO_TMPDIR = System.getProperty("java.io.tmpdir");

	String USER_NAME = System.getProperty("user.name");

	String USER_HOME = System.getProperty("user.home");

	String USER_DIR = System.getProperty("user.dir");

	String USER_TIMEZONE = System.getProperty("user.timezone");

	String USER_LANGUAGE = System.getProperty("user.language");

	String USER_COUNTRY = System.getProperty("user.country");

}
