package io.ffreedom.common.env;

import java.util.Properties;

public final class SysPropertys {

	public static void showAll() {
		getAll().entrySet().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
	}

	public static Properties getAll() {
		return System.getProperties();
	}

	public static String get(String key) {
		return System.getProperty(key);
	}

}
