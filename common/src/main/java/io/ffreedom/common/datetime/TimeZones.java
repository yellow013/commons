package io.ffreedom.common.datetime;

import java.time.ZoneId;
import java.time.ZoneOffset;

public final class TimeZones {

	/**
	 * 系统默认时区
	 */
	public static final ZoneId SYSTEM_DEFAULT = ZoneOffset.systemDefault();

	/**
	 * UTC时间
	 */
	public static final ZoneOffset UTC = ZoneOffset.UTC;

	/**
	 * 中国标准时间
	 */
	public static final ZoneId CTT = ZoneId.of("Asia/Shanghai");

}
