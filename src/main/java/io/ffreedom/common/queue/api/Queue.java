package io.ffreedom.common.queue.api;

public interface Queue<T> {
	
	boolean enqueue(T t);
	
	String getQueueName();

}