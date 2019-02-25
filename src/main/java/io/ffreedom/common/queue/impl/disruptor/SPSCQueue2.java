package io.ffreedom.common.queue.impl.disruptor;

import java.util.function.Supplier;

import org.slf4j.Logger;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.common.queue.api.SCQueue;
import io.ffreedom.common.utils.ThreadUtil;

public class SPSCQueue2<T> extends SCQueue<T> {

	private Logger logger = LoggerFactory.getLogger(SPSCQueue2.class);

	private Disruptor<T> disruptor;

	private LoadContainerEventProducer producer;

	private volatile boolean isStop = false;

	public SPSCQueue2(int queueSize, boolean autoRun, Supplier<T> supplier, Processor<T> processor) {
		super(processor);
		if (queueSize == 0 || queueSize % 2 != 0)
			throw new IllegalArgumentException("queueSize set error...");
		this.processor = processor;
		this.disruptor = new Disruptor<>(
				// 实现EventFactory<LoadContainer<>>的Lambda
				() -> supplier.get(),
				// 队列容量
				queueSize,
				// 实现ThreadFactory的Lambda
				// (Runnable runnable) -> ThreadUtil.newThread(runnable, "disruptor-thread"),
				DaemonThreadFactory.INSTANCE,
				// 生产者策略, 使用单生产者
				ProducerType.SINGLE,
				// Waiting策略
				new BusySpinWaitStrategy());
		this.disruptor.handleEventsWith((event, sequence, endOfBatch) -> tryCallProcessor(event));
		this.producer = new LoadContainerEventProducer(disruptor.getRingBuffer());
		if (autoRun)
			start();
	}

	private void tryCallProcessor(T t) {
		try {
			processor.process(t);
		} catch (Exception e) {
			logger.error("processor throw exception -> [{}]", e.getMessage(), e);
			throw new RuntimeException(e);
		}
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
					// TODO Auto-generated method stub
				}
			});
		}
	}

	@Override
	public boolean enqueue(T t) {
		try {
			if (isStop)
				return false;
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
		this.isStop = true;
		while (disruptor.getBufferSize() != 0)
			ThreadUtil.sleep(10);
		disruptor.shutdown();
		logger.info("Call stop() success, disruptor is shutdown.");
	}

	public static void main(String[] args) {

		SPSCQueue2<Integer> queue = new SPSCQueue2<>(1024, true, () -> null, (integer) -> System.out.println(integer));

		ThreadUtil.startNewThread(() -> {
			int i = 0;
			for (;;) {
				queue.enqueue(++i);
				ThreadUtil.sleep(5000);
			}
		});

		ThreadUtil.sleep(10000);

	}

	@Override
	public String getQueueName() {
		// TODO Auto-generated method stub
		return null;
	}

}
