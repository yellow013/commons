package io.ffreedom.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.utils.FileUtil;
import io.ffreedom.common.utils.StringUtil;

public final class FromPropertiesFile {

	private static Logger logger = CommonLoggerFactory.getLogger(FromPropertiesFile.class);

	/**
	 * TODO 增加重新加载配置文件的功能
	 */
	// fileName
	//// |- propertyName -> value
	private static final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> AllPropertiesMap = new ConcurrentHashMap<>();

	private static final String APPLICATION_FILE_NAME = "application";

	private static final String PROPERTIES_FILE_SUFFIX = ".properties";

	static {
		List<File> allPropertiesFile = FileUtil.loadAllChildFile(
				new File(FromPropertiesFile.class.getResource("/").getPath()),
				file -> file.getName().endsWith(PROPERTIES_FILE_SUFFIX));
		for (File propertiesFile : allPropertiesFile) {
			logger.info("Properties file -> [{}] start load", propertiesFile);
			Properties properties = new Properties();
			String fileName = propertiesFile.getName().split(PROPERTIES_FILE_SUFFIX)[0];
			try {
				properties.load(new FileInputStream(propertiesFile));
				Set<String> propertyNames = properties.stringPropertyNames();
				ConcurrentHashMap<String, String> propertiesMap = getPropertiesMap(fileName);
				for (String propertyName : propertyNames) {
					String propertyValue = properties.getProperty(propertyName);
					logger.info("Put property, neme==[{}], value==[{}]", propertyName, propertyValue);
					propertiesMap.put(propertyName, propertyValue);
				}
			} catch (FileNotFoundException e) {
				logger.error("File -> [{}] is not found");
				throw new RuntimeException(e);
			} catch (IOException e) {
				logger.error("File -> [{}] load failed");
				throw new RuntimeException(e);
			}
		}
	}

	private synchronized static ConcurrentHashMap<String, String> getPropertiesMap(String fileName) {
		if (fileName.endsWith(PROPERTIES_FILE_SUFFIX))
			fileName = fileName.split(PROPERTIES_FILE_SUFFIX)[0];
		ConcurrentHashMap<String, String> propertiesMap = AllPropertiesMap.get(fileName);
		if (propertiesMap == null) {
			propertiesMap = new ConcurrentHashMap<>();
			AllPropertiesMap.put(fileName, propertiesMap);
		}
		return propertiesMap;
	}

	public synchronized static String getProperty(String fileName, String propertyName) {
		String propertyValue = getPropertiesMap(fileName).get(propertyName);
		if (propertyValue == null) {
			logger.error("Property name -> [{}] is not found of file name -> [{}]", propertyName, fileName);
			throw new RuntimeException("Read property error.");
		}
		return propertyValue;
	}

	public static int getIntProperty(String fileName, String propertyName) {
		String propertyValue = getProperty(fileName, propertyName);
		if (StringUtil.notDecimal(propertyValue)) {
			logger.error("Property name -> [{}] is not decimal of file name -> [{}]", propertyName, fileName);
			throw new RuntimeException("Read property error.");
		}
		try {
			return Integer.parseInt(propertyValue);
		} catch (NumberFormatException e) {
			logger.error("Property name -> [{}], value -> [{}] from file name -> [{}] throw NumberFormatException",
					propertyName, propertyValue, fileName);
			throw new RuntimeException(e);
		}
	}

	public static long getLongProperty(String fileName, String propertyName) {
		String propertyValue = getProperty(fileName, propertyName);
		if (StringUtil.notDecimal(propertyValue)) {
			logger.error("Property name -> [{}] is not decimal of file name -> [{}]", propertyName, fileName);
			throw new RuntimeException("Read property error.");
		}
		try {
			return Long.parseLong(propertyValue);
		} catch (NumberFormatException e) {
			logger.error("Property name -> [{}], value -> [{}] from file name -> [{}] throw NumberFormatException",
					propertyName, propertyValue, fileName);
			throw new RuntimeException(e);
		}
	}

	public static double getDoubleProperty(String fileName, String propertyName) {
		String propertyValue = getProperty(fileName, propertyName);
		if (StringUtil.notDecimal(propertyValue)) {
			logger.error("Property name -> [{}] is not decimal of file name -> [{}]", propertyName, fileName);
			throw new RuntimeException("Read property error.");
		}
		try {
			return Double.parseDouble(propertyValue);
		} catch (NumberFormatException e) {
			logger.error("Property name -> [{}], value -> [{}] from file name -> [{}] throw NumberFormatException",
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
