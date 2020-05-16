package io.mercury.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

import io.mercury.common.io.FileLoader;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;

public final class PropertiesReader {

	private static final Logger log = CommonLoggerFactory.getLogger(PropertiesReader.class);

	/**
	 * TODO 增加重新加载配置文件的功能
	 */
	// fileName-propertyName -> value
	private static final ConcurrentHashMap<String, String> AllPropMap = new ConcurrentHashMap<>();

	private static final String APPLICATION_FILE_NAME = "application";

	private static final String PROPERTIES_FILE_SUFFIX = ".properties";

	static {
		Set<File> allPropFile = FileLoader.recursiveLoad(
				new File(PropertiesReader.class.getResource("/").getPath()),
				file -> file.getName().endsWith(PROPERTIES_FILE_SUFFIX));
		try {
			for (File propFile : allPropFile) {
				log.info("Properties file -> [{}] start load", propFile);
				String fileName = propFile.getName();
				Properties prop = new Properties();
				prop.load(new FileInputStream(propFile));
				for (String propName : prop.stringPropertyNames()) {
					String propKey = getPropertiesKey(fileName, propName);
					String propValue = prop.getProperty(propName);
					log.info("Put property, propertiesKey==[{}], propertyValue==[{}]", propKey, propValue);
					AllPropMap.put(propKey, propValue);
				}
			}
		} catch (FileNotFoundException e) {
			log.error("File -> [{}] is not found");
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error("File -> [{}] load failed");
			throw new RuntimeException(e);
		}
	}

	private static String getPropertiesKey(String fileName, String propName) {
		if (fileName == null)
			fileName = "";
		if (fileName.endsWith(PROPERTIES_FILE_SUFFIX))
			fileName = fileName.split(PROPERTIES_FILE_SUFFIX)[0];
		return new StringBuilder(16).append(fileName).append("-").append(propName).toString();
	}

	public synchronized static String getProperty(String fileName, String propName) {
		String propValue = AllPropMap.get(getPropertiesKey(fileName, propName));
		if (propValue == null) {
			log.error("Property name -> [{}] is not found of file name -> [{}]", propName, fileName);
			throw new RuntimeException("Read property error.");
		}
		return propValue;
	}

	public static int getIntProperty(String fileName, String propertyName) {
		String propertyValue = getProperty(fileName, propertyName);
		if (StringUtil.notDecimal(propertyValue)) {
			log.error("Property name -> [{}] is not decimal of file name -> [{}]", propertyName, fileName);
			throw new RuntimeException("Read property error.");
		}
		try {
			return Integer.parseInt(propertyValue);
		} catch (NumberFormatException e) {
			log.error("Property name -> [{}], value -> [{}] from file name -> [{}] throw NumberFormatException",
					propertyName, propertyValue, fileName);
			throw new RuntimeException(e);
		}
	}

	public static long getLongProperty(String fileName, String propertyName) {
		String propertyValue = getProperty(fileName, propertyName);
		if (StringUtil.notDecimal(propertyValue)) {
			log.error("Property name -> [{}] is not decimal of file name -> [{}]", propertyName, fileName);
			throw new RuntimeException("Read property error.");
		}
		try {
			return Long.parseLong(propertyValue);
		} catch (NumberFormatException e) {
			log.error("Property name -> [{}], value -> [{}] from file name -> [{}] throw NumberFormatException",
					propertyName, propertyValue, fileName);
			throw new RuntimeException(e);
		}
	}

	public static double getDoubleProperty(String fileName, String propertyName) {
		String propertyValue = getProperty(fileName, propertyName);
		if (StringUtil.notDecimal(propertyValue)) {
			log.error("Property name -> [{}] is not decimal of file name -> [{}]", propertyName, fileName);
			throw new RuntimeException("Read property error.");
		}
		try {
			return Double.parseDouble(propertyValue);
		} catch (NumberFormatException e) {
			log.error("Property name -> [{}], value -> [{}] from file name -> [{}] throw NumberFormatException",
					propertyName, propertyValue, fileName);
			throw new RuntimeException(e);
		}
	}

	public static String getApplicationProperty(String propertyName) {
		return getProperty(APPLICATION_FILE_NAME, propertyName);
	}

	public static int getApplicationIntProperty(String propertyName) {
		return getIntProperty(APPLICATION_FILE_NAME, propertyName);
	}

	public static long getApplicationLongProperty(String propertyName) {
		return getLongProperty(APPLICATION_FILE_NAME, propertyName);
	}

	public static double getApplicationDoubleProperty(String propertyName) {
		return getDoubleProperty(APPLICATION_FILE_NAME, propertyName);
	}

	public static void main(String[] args) {

		File file = new File("");

		System.out.println(file.getPath());

		System.out.println(file.getAbsolutePath());

		System.out.println();

		File[] listFiles = file.listFiles();

		if (listFiles != null)
			for (File file2 : listFiles) {
				System.out.println(file2.getName());
			}

		System.out.println(System.getProperty("user.dir"));

	}

}
