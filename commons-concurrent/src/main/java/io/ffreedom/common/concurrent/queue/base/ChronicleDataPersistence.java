package io.ffreedom.common.concurrent.queue.base;

import java.time.LocalDate;
import java.util.Scanner;

import org.slf4j.Logger;

import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.common.env.SysProperty;
import io.ffreedom.common.env.SysPropertys;
import io.ffreedom.common.log.CommonLoggerFactory;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;

public abstract class ChronicleDataPersistence<T> {

	private String path;

	private String name;

	private SingleChronicleQueue queue;

	protected ExcerptAppender appender;
	protected ExcerptTailer tailer;

	protected Logger logger = CommonLoggerFactory.getLogger(ChronicleDataPersistence.class);

	protected ChronicleDataPersistence() {
		this(null);
	}

	protected ChronicleDataPersistence(Logger externalLogger) {
		this("data", String.valueOf(DateTimeUtil.date()), externalLogger);
	}

	protected ChronicleDataPersistence(String prefix, String name) {
		this(prefix, name, null);
	}

	protected ChronicleDataPersistence(String prefix, String name, Logger externalLogger) {
		this.name = prefix + "-" + name;
		if (externalLogger != null)
			this.logger = externalLogger;
		initSavePath();
		initChronicleComponent();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			queue.close();
		}, "ChronicleQueue-Instance-Cleaner"));
	}

	private void initSavePath() {
		this.path = SysProperty.JAVA_IO_TMPDIR + "/" + name;
	}

	private void initChronicleComponent() {
		this.queue = SingleChronicleQueueBuilder.binary(path).build();
		this.appender = queue.acquireAppender();
		this.tailer = queue.createTailer();
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public SingleChronicleQueue getQueue() {
		return queue;
	}

	public static void main(String[] args) {
		String path = "backup-" + LocalDate.now();
		SingleChronicleQueue queue = SingleChronicleQueueBuilder.binary(path).build();
		ExcerptAppender appender = queue.acquireAppender();
		try (Scanner read = new Scanner(System.in)) {
			while (true) {
				System.out.println("type something");
				String line = read.nextLine();
				if (line.isEmpty())
					break;
				appender.writeText(line);
			}
		}
		System.out.println("... bye.");
		SysPropertys.showAll();
	}

	public void append(T event) {
		append0(event);
	}

	abstract protected void append0(T event);

//	public byte[] getEvent() {
//		try {
//			tailer.readBytes(reader)
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//
//	public List<byte[]> getEvents() {
//
//	}

}
