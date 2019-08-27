package io.ffreedom.common.queue.api;

public interface Queue<E> {
	
	boolean enqueue(E e);
	
	String getQueueName();

}