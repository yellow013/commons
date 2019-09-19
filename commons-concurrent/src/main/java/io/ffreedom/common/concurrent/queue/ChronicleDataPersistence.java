package io.ffreedom.common.concurrent.queue;

import java.time.LocalDate;
import java.util.Scanner;

import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;

public class ChronicleDataPersistence<T> {

	private String path;

	public ChronicleDataPersistence(String path) {
		this.path = path;
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
	}

}
