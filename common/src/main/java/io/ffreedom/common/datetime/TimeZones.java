package io.ffreedom.common.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;

public interface TimeZones {

	/**
	 * 系统默认时区
	 */
	ZoneId SYSTEM_DEFAULT = ZoneOffset.systemDefault();

	/**
	 * UTC时间
	 */
	ZoneOffset UTC = ZoneOffset.UTC;

	/**
	 * 中国标准时间
	 */
	ZoneId CTT = ZoneId.of("Asia/Shanghai");

}
