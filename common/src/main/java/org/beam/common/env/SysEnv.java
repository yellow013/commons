package org.beam.common.env;

public interface SysEnv {

	String JAVA_CONFIG_HOME = System.getenv("JAVA_CONFIG_HOME");

	String JAVA_LOG_HOME = System.getenv("JAVA_LOG_HOME");

}
