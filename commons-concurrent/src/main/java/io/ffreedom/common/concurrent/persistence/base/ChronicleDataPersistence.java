package io.ffreedom.common.concurrent.persistence.base;

import java.time.LocalDate;
import java.util.Scanner;

import org.slf4j.Logger;

import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.common.datetime.Pattern.DatePattern;
import io.ffreedom.common.env.SysProperty;
import io.ffreedom.common.env.SysPropertys;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.utils.StringUtil;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.RollCycles;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;

public abstract class ChronicleDataPersistence<T, RT extends QueueReader<T>, WT extends QueueWriter<T>> {

	private String savePath;

	private String name;

	protected SingleChronicleQueue queue;

	protected Logger logger = CommonLoggerFactory.getLogger(this.getClass());

	protected ChronicleDataPersistence() {
		this("", "", null, null);
	}

	protected ChronicleDataPersistence(Logger externalLogger) {
		this("", "", null, externalLogger);
	}

	protected ChronicleDataPersistence(String prefix, Logger externalLogger) {
		this("", prefix, null, externalLogger);
	}

	protected ChronicleDataPersistence(String prefix, LocalDate date, Logger externalLogger) {
		this("", prefix, date, externalLogger);
	}

	protected ChronicleDataPersistence(String rootPath, String prefix, Logger externalLogger) {
		this(rootPath, prefix, null, externalLogger);
	}

	protected ChronicleDataPersistence(String rootPath, String prefix, LocalDate date, Logger externalLogger) {
		if (externalLogger != null)
			this.logger = externalLogger;
		initSavePath(rootPath, prefix, date);
		initChronicleComponent();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			queue.close();
			logger.info("Add ShutdownHook of {}", name);
		}, "ChronicleQueue-Instance-Cleanup-Thread"));
	}

	private void initSavePath(String rootPath, String prefix, LocalDate date) {
		if (StringUtil.isNullOrEmpty(rootPath))
			rootPath = SysProperty.JAVA_IO_TMPDIR + "/";
		if (StringUtil.isNullOrEmpty(prefix))
			prefix = "data";
		this.name = date == null ? prefix : prefix + "-" + DateTimeUtil.dateStr(date, DatePattern.YYYYMMDD);
		this.savePath = rootPath + name;
	}

	private void initChronicleComponent() {
		this.queue = SingleChronicleQueueBuilder.binary(savePath).build();
	}

	public String getName() {
		return name;
	}

	public String getSavePath() {
		return savePath;
	}

	public SingleChronicleQueue getQueue() {
		return queue;
	}

	abstract public RT newQueueReader();

	abstract public WT newQueueWriter();

	public static void main(String[] args) {
		String path = "backup-" + LocalDate.now();
		SingleChronicleQueue queue = SingleChronicleQueueBuilder.binary(path).rollCycle(RollCycles.HOURLY).build();
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

}
