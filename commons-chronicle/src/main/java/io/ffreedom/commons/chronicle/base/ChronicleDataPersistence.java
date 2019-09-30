package io.ffreedom.commons.chronicle.base;

import java.io.File;

import org.slf4j.Logger;

import io.ffreedom.common.env.SysProperty;
import io.ffreedom.common.log.CommonLoggerFactory;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;

public abstract class ChronicleDataPersistence<T, RT extends DataReader<T>, WT extends DataWriter<T>> {

	private String savePath;
	private String name;

	private SingleChronicleQueue queue;

	private String rootPath;
	private String folder;
	private FileCycle fileCycle;

	protected Logger logger;

	protected ChronicleDataPersistence(BaseBuilder<?> builder) {
		this.rootPath = builder.rootPath;
		this.folder = builder.folder;
		this.fileCycle = builder.fileCycle;
		this.logger = builder.logger;
		init();
	}

	private void init() {
		this.name = folder;
		this.savePath = rootPath + folder;
		this.queue = SingleChronicleQueueBuilder.single(savePath).rollCycle(fileCycle.getRollCycle()).build();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			queue.close();
			logger.info("Add ShutdownHook of {}", name);
		}, "ChronicleQueue-Instance-Cleanup-Thread"));
	}

	public String getName() {
		return name;
	}

	public String getSavePath() {
		return savePath;
	}

	public FileCycle getFileCycle() {
		return fileCycle;
	}

	public SingleChronicleQueue getQueue() {
		return queue;
	}

	public boolean deleteFolder() {
		File file = new File(savePath);
		if (file.isAbsolute())
			return file.delete();
		return false;
	}

	abstract public RT createReader();

	abstract public WT createWriter();

	protected abstract static class BaseBuilder<BT> {

		protected String rootPath = SysProperty.JAVA_IO_TMPDIR + "/";;
		protected String folder = "chronicle";
		protected Logger logger = CommonLoggerFactory.getLogger(this.getClass());
		protected FileCycle fileCycle = FileCycle.HOUR;

		public BT setRootPath(String rootPath) {
			this.rootPath = rootPath;
			return getThis();
		}

		public BT setFolder(String folder) {
			this.folder = folder;
			return getThis();
		}

		public BT setLogger(Logger logger) {
			this.logger = logger;
			return getThis();
		}

		public BT setFileCycle(FileCycle fileCycle) {
			this.fileCycle = fileCycle;
			return getThis();
		}

		abstract protected BT getThis();

	}

}
