package org.beam.common.service;

/**
 * implements java.lang.Runnable
 * 
 * @author peng.j
 *
 */
public interface ServiceThread extends Runnable {

	void start();

	void stop();

	default void run() {
		start();
	}

}