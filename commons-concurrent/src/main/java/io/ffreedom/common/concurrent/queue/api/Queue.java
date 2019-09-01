package io.ffreedom.common.concurrent.queue.api;

public interface Queue<E> {
	
	boolean enqueue(E e);
	
	String getQueueName();

}