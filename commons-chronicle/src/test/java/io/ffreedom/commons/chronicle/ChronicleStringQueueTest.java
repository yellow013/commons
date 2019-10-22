package io.ffreedom.commons.chronicle;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import io.ffreedom.commons.chronicle.queue.ChronicleStringQueue;
import io.ffreedom.commons.chronicle.queue.accessor.StringReader;
import io.ffreedom.commons.chronicle.queue.accessor.StringWriter;
import io.ffreedom.commons.chronicle.queue.base.FileCycle;

public class ChronicleStringQueueTest {

	@Test
	public void test0() {
		ChronicleStringQueue persistence = ChronicleStringQueue.newBuilder().setFileCycle(FileCycle.MINUTELY).build();
		StringWriter writer = persistence.acquireWriter();
		StringReader reader = persistence.createReader();

		LocalDateTime wantOf = LocalDateTime.of(2019, 9, 26, 20, 35);
		// Start 2019-09-26T20:35:02.526

		long epochSecond = wantOf.toEpochSecond(ZoneOffset.ofHours(8));

		new Thread(() -> {
			try {
				boolean moved = reader.moveTo(epochSecond);
				System.out.println(moved);
				while (true) {
					try {
						String text = reader.next();
						if (text != null) {
							System.out.println(text);
							Thread.sleep(500);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		while (true) {
			try {
				writer.append(LocalDateTime.now().toString());
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
