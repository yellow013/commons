package io.ffreedom.common.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import io.ffreedom.common.utils.StringUtil;

public abstract class DateUtil {

	public static String getDateStr(DateStyle style) {
		DateFormat yyyyMMdd = style.newDateFormat();
		return yyyyMMdd.format(new Date());
	}

	public static Date strToDate(String str, DateStyle style) {
		if (StringUtil.isNullOrEmpty(str)) {
			return null;
		}
		DateFormat dateFormat = style.newDateFormat();
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
