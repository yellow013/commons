package io.ffreedom.common.datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public interface TimeZones {

	ZoneOffset UTC = ZoneOffset.UTC;

	ZoneId SYSTEM_DEFAULT = ZoneId.systemDefault();

	ZoneOffset SYSTEM_DEFAULT_OFFSET = ZonedDateTime.ofInstant(Instant.EPOCH, SYSTEM_DEFAULT).getOffset();
	/**
	 * Chinese Standard Time
	 */
	ZoneId CST = ZoneId.of("Asia/Shanghai");

	ZoneOffset CST_OFFSET = ZonedDateTime.ofInstant(Instant.EPOCH, CST).getOffset();

	/**
	 * Japan Standard Time
	 */
	ZoneId JST = ZoneId.of("Asia/Tokyo");

	ZoneOffset JST_OFFSET = ZonedDateTime.ofInstant(Instant.EPOCH, JST).getOffset();

}
