package io.ffreedom.common.queue.disruptor;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.queue.base.SCQueue;
import io.ffreedom.common.utils.ThreadUtil;

public class SPSCQueue2<T> extends SCQueue<T> {

	private Disruptor<T> disruptor;

	private LoadContainerEventProducer producer;

	public SPSCQueue2(int queueSize, boolean autoRun, EventFactory<T> eventFactory, Processor<T> processor) {
		super(processor);
		if (queueSize == 0 || queueSize % 2 != 0) {
			throw new IllegalArgumentException("queueSize set error...");
		}
		this.processor = processor;
		this.disruptor = new Disruptor<>(
				// 实现EventFactory<LoadContainer<>>的Lambda
				eventFactory,
				// 队列容量
				queueSize,
				// 实现ThreadFactory的Lambda
				// (Runnable runnable) -> ThreadUtil.newThread(runnable, "disruptor-thread"),
				DaemonThreadFactory.INSTANCE,
				// 生产者策略, 使用单生产者
				ProducerType.SINGLE,
				// Waiting策略
				new BusySpinWaitStrategy());
		this.disruptor.handleEventsWith((event, sequence, endOfBatch) -> this.processor.process(event));
		this.producer = new LoadContainerEventProducer(disruptor.getRingBuffer());
		if (autoRun)
			start();
	}

	private class LoadContainerEventProducer {

		private final RingBuffer<T> ringBuffer;

		private LoadContainerEventProducer(RingBuffer<T> ringBuffer) {
			this.ringBuffer = ringBuffer;
		}

		public void onData(T t) {
			ringBuffer.publishEvent(new EventTranslator<T>() {
				@Override
				public void translateTo(T event, long sequence) {
					// TODO 实现未完成
				}
			});
		}
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

	public void start() {
		this.disruptor.start();
	}

	@Override
	public void stop() {
		while (disruptor.getBufferSize() != 0) {
			ThreadUtil.sleep(1000);
		}
		disruptor.shutdown();
	}

	public static void main(String[] args) {

		SPSCQueue2<Integer> queue = new SPSCQueue2<>(1024, true, () -> {
			return null;
		}, (integer) -> {
			System.out.println(integer);
		});

		ThreadUtil.startNewThread(() -> {
			int i = 0;
			for (;;) {
				queue.enQueue(++i);
				ThreadUtil.sleep(5000);
			}
		});

		ThreadUtil.sleep(10000);

	}

}
