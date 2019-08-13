package io.ffreedom.common.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public interface TimeZones {

	ZoneOffset UTC = ZoneOffset.UTC;

	ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

	ZoneOffset DEFAULT_ZONE_OFFSET = ZonedDateTime.now(DEFAULT_ZONE_ID).getOffset();
	/**
	 * Chinese Standard Time
	 */
	ZoneId CST = ZoneId.of("Asia/Shanghai");

	/**
	 * Japan Standard Time
	 */
	ZoneId JST = ZoneId.of("Asia/Tokyo");

}
