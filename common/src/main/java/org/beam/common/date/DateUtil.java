package org.beam.common.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.beam.common.date.DateFormatStyle;
import org.beam.common.utils.StringUtil;

public abstract class DateUtil {

	public static String getDateStr(DateFormatStyle style) {
		SimpleDateFormat formater = new SimpleDateFormat(style.getPattern());
		return formater.format(new Date());
	}

	public static Date strToDate(String str, DateFormatStyle style) {
		if (StringUtil.isNullOrEmpty(str)) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(style.getPattern());
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
