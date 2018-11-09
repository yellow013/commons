package io.ffreedom.common.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;

public interface TimeZones {

	ZoneOffset UTC = ZoneOffset.UTC;

	ZoneId SYS_DEFAULT = ZoneOffset.systemDefault();

	/**
	 * Chinese Standard Time
	 */
	ZoneId CST = ZoneId.of("Asia/Shanghai");

	/**
	 * Japan Standard Time
	 */
	ZoneId JST = ZoneId.of("Asia/Tokyo");

}
