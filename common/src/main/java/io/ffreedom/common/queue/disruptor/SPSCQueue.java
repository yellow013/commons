package io.ffreedom.common.queue.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import io.ffreedom.common.queue.base.LoadContainer;
import io.ffreedom.common.queue.base.QueueProcessor;
import io.ffreedom.common.queue.base.SCQueue;
import io.ffreedom.common.utils.ThreadUtil;

/**
 * 
 * @author Peng.Jin
 *
 * @param <T>
 */

public class SPSCQueue<T> implements SCQueue<T> {

	private Disruptor<LoadContainer<T>> disruptor;

	private LoadContainerEventProducer producer;

	private QueueProcessor<T> processor;

	private volatile boolean isStop = false;

	public SPSCQueue(int queueSize, boolean autoRun, QueueProcessor<T> processor) {
		this(queueSize, autoRun, processor, WaitStrategyOption.BusySpin);
	}

	public SPSCQueue(int queueSize, boolean autoRun, QueueProcessor<T> processor, WaitStrategyOption option) {
		if (queueSize == 0 || queueSize % 2 != 0) {
			throw new IllegalArgumentException("queueSize set error...");
		}
		this.processor = processor;
		this.disruptor = new Disruptor<>(
				// 实现EventFactory<LoadContainer<>>的Lambda
				LoadContainer::new,
				// 队列容量
				queueSize,
				// 实现ThreadFactory的Lambda
				(runnable) -> {
					return ThreadUtil.newThread(runnable, "disruptor-working-thread");
				},
				// 生产者策略, 使用单生产者
				ProducerType.SINGLE,
				// Waiting策略
				WaitStrategyFactory.newWaitStrategy(option));
		this.disruptor.handleEventsWith((LoadContainer<T> event, long sequence, boolean endOfBatch) -> {
			if (this.processor != null) {
				this.processor.process(event.unloading());
			}
		});
		this.producer = new LoadContainerEventProducer(disruptor.getRingBuffer());
		if (autoRun) {
			start();
		}
	}

	public SPSCQueue(int queueSize) {
		this(queueSize, false, null);
	}

	@Override
	public SPSCQueue<T> setProcessor(QueueProcessor<T> processor) {
		this.processor = processor;
		return this;
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

	@Override
	public boolean enQueue(T t) {
		try {
			if (isStop) {
				return false;
			}
			this.producer.onData(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void start() {
		this.disruptor.start();
	}

	@Override
	public void stop() {
		this.isStop = true;
		while (disruptor.getBufferSize() != 0) {
			ThreadUtil.sleep(1);
		}
		disruptor.shutdown();
	}

	public static void main(String[] args) {

		SPSCQueue<Integer> queue = new SPSCQueue<>(64, true, (integer) -> {
			System.out.println("********************************************");
		});

		ThreadUtil.startNewThread(() -> {
			int i = 0;
			for (;;) {
				queue.enQueue(++i);
			}
		});

		ThreadUtil.sleep(10000);

		queue.stop();

	}

}
