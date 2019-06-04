package io.ffreedom.common.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public interface TimeZones {

	ZoneOffset UTC = ZoneOffset.UTC;

	ZoneOffset DEFAULT_ZONE_OFFSET = ZonedDateTime.now().getOffset();

	ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

	/**
	 * Chinese Standard Time
	 */
	ZoneId CST = ZoneId.of("Asia/Shanghai");

	/**
	 * Japan Standard Time
	 */
	ZoneId JST = ZoneId.of("Asia/Tokyo");

}
