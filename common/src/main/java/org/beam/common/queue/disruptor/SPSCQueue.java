package org.beam.common.queue.disruptor;

import org.beam.common.queue.base.LoadContainer;
import org.beam.common.queue.base.QueueProcessor;
import org.beam.common.queue.base.SCQueue;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.LiteBlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class SPSCQueue<T> implements SCQueue<T> {

	private Disruptor<LoadContainer<T>> disruptor;

	private LoadContainerEventProducer producer;

	@SuppressWarnings("unchecked")
	public SPSCQueue(int queueSize, QueueProcessor<T> processor) {
		if (queueSize == 0 || queueSize % 2 != 0) {
			throw new IllegalArgumentException("queueSize set error");
		}

		this.disruptor = new Disruptor<>(
				// 实现EventFactory<LoadContainer<>>的Lambda
				LoadContainer::new,
				// 队列容量
				queueSize,
				// 实现ThreadFactory的Lambda
				(runnable) -> {
					return new Thread(runnable, "disruptor-thread");
				},
				// 生产者策略, 使用单生产者
				ProducerType.SINGLE,
				// Waiting策略
				new LiteBlockingWaitStrategy());

		this.disruptor.handleEventsWith((LoadContainer<T> event, long sequence, boolean endOfBatch) -> {
			processor.process(event.unloading());
		});

		this.producer = new LoadContainerEventProducer(disruptor.getRingBuffer());

	}

	@Override
	public boolean enQueue(T t) {
		try {
			this.producer.onData(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void startProcess() {
		this.disruptor.start();
	}

	private class LoadContainerEventProducer {

		private final RingBuffer<LoadContainer<T>> ringBuffer;

		private LoadContainerEventProducer(RingBuffer<LoadContainer<T>> ringBuffer) {
			this.ringBuffer = ringBuffer;
		}

		public void onData(T t) {
			ringBuffer.publishEvent(new EventTranslatorOneArg<LoadContainer<T>, T>() {
				public void translateTo(LoadContainer<T> event, long sequence, T t) {
					event.loading(t);
				}
			}, t);
		}
	}

	public static void main(String[] args) {

		SPSCQueue<String> queue = new SPSCQueue<>(1024, (str) -> {
			System.out.println(str);
		});

		new Thread(() -> {
			int i = 0;
			for (;;) {
				queue.enQueue(++i + "");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		queue.startProcess();
		
	}

}
