package io.ffreedom.transport.netty;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;

public class Test {

	public static void main(String[] args) {

		Instant now = Instant.now();
		
		System.out.println(now.getEpochSecond());
		System.out.println(now.getLong(ChronoField.MICRO_OF_SECOND));
		System.out.println(now.getLong(ChronoField.NANO_OF_SECOND));
		System.out.println(now.getNano() / 1000);
		
		long time = new Date().getTime();
		System.out.println(time);
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
	    System.out.println(ZonedDateTime.now().getNano());

	}

}
