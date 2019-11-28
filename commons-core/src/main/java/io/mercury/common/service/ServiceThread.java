package io.mercury.common.service;

/**
 * implements java.lang.Runnable
 * 
 * @author phoenix
 *
 */
public interface ServiceThread extends Runnable {

	void start();

	void stop();

	default void run() {
		start();
	}

}