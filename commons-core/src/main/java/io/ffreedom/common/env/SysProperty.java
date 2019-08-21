package io.ffreedom.common.env;

public interface SysProperty {

	/*
	 * System.getProperty("os.name")
	 */
	String OS_NAME = System.getProperty("os.name");

	/*
	 * System.getProperty("java.home")
	 */
	String JAVA_HOME = System.getProperty("java.home");

	/*
	 * System.getProperty("java.class.path")
	 */
	String JAVA_CLASS_PATH = System.getProperty("java.class.path");

	/*
	 * System.getProperty("java.version")
	 */
	String JAVA_VERSION = System.getProperty("java.version");

	/*
	 * System.getProperty("java.vm.name")
	 */
	String JAVA_VM_NAME = System.getProperty("java.vm.name");

	/*
	 * System.getProperty("java.runtime.name")
	 */
	String JAVA_RUNTIME_NAME = System.getProperty("java.runtime.name");

	/*
	 * System.getProperty("java.runtime.version")
	 */
	String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");

	/*
	 * System.getProperty("java.io.tmpdir")
	 */
	String JAVA_IO_TMPDIR = System.getProperty("java.io.tmpdir");

	/*
	 * System.getProperty("user.name")
	 */
	String USER_NAME = System.getProperty("user.name");

	/*
	 * System.getProperty("user.home")
	 */
	String USER_HOME = System.getProperty("user.home");

	/*
	 * System.getProperty("user.dir")
	 */
	String USER_DIR = System.getProperty("user.dir");

	/*
	 * System.getProperty("user.timezone")
	 */
	String USER_TIMEZONE = System.getProperty("user.timezone");

	/*
	 * System.getProperty("user.language")
	 */
	String USER_LANGUAGE = System.getProperty("user.language");

	/*
	 * System.getProperty("user.country")
	 */
	String USER_COUNTRY = System.getProperty("user.country");

}
