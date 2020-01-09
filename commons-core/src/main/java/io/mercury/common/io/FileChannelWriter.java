package io.mercury.common.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import io.mercury.common.annotation.lang.MayThrowsRuntimeException;
import io.mercury.common.character.Separator;
import io.mercury.common.sys.SysProperties;

/**
 * Use FileChannel
 * 
 * @author yellow013
 */
public final class FileChannelWriter {

	private FileChannelWriter() {
	}

	public static File writing(List<String> source, final File target) throws IOException {
		return writing(source, target, 8192);
	}

	@MayThrowsRuntimeException(NullPointerException.class)
	public static File writing(List<String> source, final File target, final int bufferCapacity) throws IOException {
		if (target == null)
			throw new NullPointerException("target file must not be null.");
		if (source == null)
			source = new ArrayList<>();
		File parentFile = target.getParentFile();
		if (!parentFile.exists())
			parentFile.mkdirs();
		if (!target.exists())
			target.createNewFile();
		try (RandomAccessFile rafile = new RandomAccessFile(target, "rw"); FileChannel channel = rafile.getChannel()) {
			// 根据输入的[buffer capacity]分配堆外缓冲区
			ByteBuffer buffer = ByteBuffer.allocateDirect(bufferCapacity);
			for (String data : source) {
				byte[] bytes = data.getBytes();
				if (bytes.length < bufferCapacity)
					// [bytes.length]小于[buffer capacity], 只需写一次
					flipAndChannelWriting(buffer.put(bytes), channel);
				else {
					// 计算需要写入的次数
					int repeat = bytes.length / bufferCapacity;
					int index = 0;
					for (int i = 0; i < repeat; i++)
						// 从上次写入的[index]开始写入, 每次增加[buffer capacity]的长度
						flipAndChannelWriting(buffer.put(bytes, index = i * bufferCapacity, bufferCapacity), channel);
					// 计算剩余的长度
					int remaining = bytes.length % bufferCapacity;
					if (remaining > 0)
						// 从上次写入的[index]开始写入剩余的数据, [index]需要加上最后一次写入的[buffer capacity]长度
						flipAndChannelWriting(buffer.put(bytes, index += bufferCapacity, remaining), channel);
				}
			}
			channel.force(true);
		}
		return target;
	}

	private static final void flipAndChannelWriting(ByteBuffer buffer, FileChannel channel) throws IOException {
		// 转换buffer为写入
		buffer.flip();
		// 检查是否还有剩余数据
		while (buffer.hasRemaining())
			channel.write(buffer);
		// 清空指针, 转换为写入状态
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
			writing(lines, new File(SysProperties.JAVA_IO_TMPDIR_FILE, "test.csv"), 2048);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
