package org.beam.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.beam.common.config.GlobalSet;

public class CommonLoggerFactory {

	static {
		System.setProperty(GlobalSet.JET_ID_PROPERTY_NAME, String.valueOf(GlobalSet.JET_ID));
	}

	public static Logger getLogger(Class<?> clazz) {
		return LogManager.getLogger(clazz);
	}

}
