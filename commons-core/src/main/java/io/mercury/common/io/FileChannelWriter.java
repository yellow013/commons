package io.mercury.common.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import io.mercury.common.annotations.lang.MayThrowsRuntimeException;
import io.mercury.common.character.Separator;
import io.mercury.common.sys.SysProperties;

public final class FileChannelWriter {

	private FileChannelWriter() {
	}

	public static File write(final File file, List<String> lines) throws IOException {
		return write(file, lines, 4096);
	}

	@MayThrowsRuntimeException(NullPointerException.class)
	public static File write(final File file, List<String> lines, final int bufCapacity) throws IOException {
		if (file == null)
			throw new NullPointerException("file must not be null.");
		if (lines == null)
			lines = new ArrayList<>();
		File parentFile = file.getParentFile();
		if (!parentFile.exists())
			parentFile.mkdirs();
		if (!file.exists())
			file.createNewFile();
		try (RandomAccessFile rafile = new RandomAccessFile(file, "rw"); FileChannel channel = rafile.getChannel()) {
			ByteBuffer buffer = ByteBuffer.allocateDirect(bufCapacity);
			for (String line : lines) {
				byte[] bytes = line.getBytes();

				if (bytes.length < bufCapacity)
					// bytes.length 小于 buffer capacity, 只需写一次
					flipAndChannelWrite(buffer.put(bytes), channel);
				else {
					// 计算需要写入的次数
					int repeat = bytes.length / bufCapacity;
					int index = 0;
					for (int i = 0; i < repeat; i++)
						// 从上次写入的index开始继续写入
						flipAndChannelWrite(buffer.put(bytes, index = i * bufCapacity, bufCapacity), channel);
					// 计算剩余的数据量
					int remaining = bytes.length % bufCapacity;
					if (remaining > 0)
						// 从上次写入的index写入剩余的部分
						flipAndChannelWrite(buffer.put(bytes, index += bufCapacity, remaining), channel);
				}
			}
			channel.force(true);
		}
		return file;
	}

	private static final void flipAndChannelWrite(ByteBuffer buffer, FileChannel channel) throws IOException {
		buffer.flip();
		while (buffer.hasRemaining())
			channel.write(buffer);
		buffer.clear();
	}

	public static void main(String[] args) {

		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			StringBuilder builder = new StringBuilder();
			for (int j = 0; j < 1000; j++) {
				builder.append(j);
				builder.append(',');
			}
			builder.append(Separator.LINE_SEPARATOR);
			lines.add(builder.toString());
		}

		try {
			write(new File(SysProperties.JAVA_IO_TMPDIR_FILE, "test.csv"), lines, 2048);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
