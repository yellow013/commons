package io.ffreedom.common.collections.queue.api;

public interface Queue<E> {
	
	boolean enqueue(E e);
	
	String getQueueName();

}