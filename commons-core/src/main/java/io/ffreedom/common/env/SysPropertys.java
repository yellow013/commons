package io.ffreedom.common.env;

import java.util.Properties;

public final class SystemPropertys {

	/*
	 * System.getProperty("os.name")
	 */
	public static final String OS_NAME = System.getProperty("os.name");

	/*
	 * System.getProperty("java.home")
	 */
	public static final String JAVA_HOME = System.getProperty("java.home");

	/*
	 * System.getProperty("java.class.path")
	 */
	public static final String JAVA_CLASS_PATH = System.getProperty("java.class.path");

	/*
	 * System.getProperty("java.version")
	 */
	public static final String JAVA_VERSION = System.getProperty("java.version");

	/*
	 * System.getProperty("java.vm.name")
	 */
	public static final String JAVA_VM_NAME = System.getProperty("java.vm.name");

	/*
	 * System.getProperty("java.runtime.name")
	 */
	public static final String JAVA_RUNTIME_NAME = System.getProperty("java.runtime.name");

	/*
	 * System.getProperty("java.runtime.version")
	 */
	public static final String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");

	/*
	 * System.getProperty("java.io.tmpdir")
	 */
	public static final String JAVA_IO_TMPDIR = System.getProperty("java.io.tmpdir");

	/*
	 * System.getProperty("user.name")
	 */
	public static final String USER_NAME = System.getProperty("user.name");

	/*
	 * System.getProperty("user.home")
	 */
	public static final String USER_HOME = System.getProperty("user.home");

	/*
	 * System.getProperty("user.dir")
	 */
	public static final String USER_DIR = System.getProperty("user.dir");

	/*
	 * System.getProperty("user.timezone")
	 */
	public static final String USER_TIMEZONE = System.getProperty("user.timezone");

	/*
	 * System.getProperty("user.language")
	 */
	public static final String USER_LANGUAGE = System.getProperty("user.language");

	/*
	 * System.getProperty("user.country")
	 */
	public static final String USER_COUNTRY = System.getProperty("user.country");

	public static Properties getProperties() {
		return System.getProperties();
	}

	public static String getProperty(String key) {
		return System.getProperty(key);
	}

	public static void main(String[] args) {
		getProperties().entrySet().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
	}

}
