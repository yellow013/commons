package io.ffreedom.common.concurrent.persistence;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import io.ffreedom.common.concurrent.persistence.base.FileCycle;

public class CharSequenceDataPersistenceTest {

	@Test
	public void test0() {
		StringDataPersistence persistence = StringDataPersistence.newBuilder().setFileCycle(FileCycle.MINUTE).build();
		StringWriter writer = persistence.createWriter();
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
						if (text != null)
							System.out.println(text);
						Thread.sleep(500);
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
