package io.ffreedom.common.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;

public interface TimeZones {

	ZoneId SYSTEM_DEFAULT = ZoneId.systemDefault();

	ZoneOffset UTC = ZoneOffset.UTC;

	/**
	 * Chinese Standard Time
	 */
	ZoneId CST = ZoneId.of("Asia/Shanghai");

	/**
	 * Japan Standard Time
	 */
	ZoneId JST = ZoneId.of("Asia/Tokyo");

}
