package io.ffreedom.commons.chronicle.queue.base;

import static io.ffreedom.common.utils.StringUtil.isPath;

import java.io.File;
import java.util.function.ObjIntConsumer;

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
	private ObjIntConsumer<File> storeFileListener;

	protected Logger logger;

	protected ChronicleDataPersistence(BaseBuilder<?> builder) {
		this.rootPath = builder.rootPath;
		this.folder = builder.folder;
		this.fileCycle = builder.fileCycle;
		this.storeFileListener = builder.storeFileListener;
		this.logger = builder.logger;
		init();
	}

	private void init() {
		this.name = folder;
		this.savePath = rootPath + folder;
		this.queue = SingleChronicleQueueBuilder.single(savePath).rollCycle(fileCycle.getRollCycle())
				.storeFileListener(this::storeFileHandle).build();
		Runtime.getRuntime().addShutdownHook(new Thread(this::shutdownHandle, "ChronicleQueue-Cleanup-Thread"));
	}

	private void shutdownHandle() {
		queue.close();
		logger.info("Run ShutdownHook of {}", name);
	}

	private void storeFileHandle(int cycle, File file) {
		if (storeFileListener != null)
			storeFileListener.accept(file, cycle);
		else
			logger.info("Released file : cycle==[{}], file==[{}]", cycle, file.getAbsolutePath());
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

	public abstract RT createReader();

	public abstract WT createWriter();

	protected abstract static class BaseBuilder<BT> {

		private String rootPath = SysProperty.JAVA_IO_TMPDIR + "/chronicle-queue/";
		private String folder = "default";
		private Logger logger = CommonLoggerFactory.getLogger(ChronicleDataPersistence.class);
		private FileCycle fileCycle = FileCycle.HOUR;
		private ObjIntConsumer<File> storeFileListener = null;

		public BT setRootPath(String rootPath) {
			this.rootPath = isPath(rootPath) ? rootPath : rootPath + "/";
			return getThis();
		}

		public BT setFolder(String folder) {
			this.folder = isPath(folder) ? folder : folder + "/";
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

		public BT setStoreFileListener(ObjIntConsumer<File> storeFileListener) {
			this.storeFileListener = storeFileListener;
			return getThis();
		}

		protected abstract BT getThis();

	}

}
