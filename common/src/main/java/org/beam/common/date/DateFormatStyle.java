package org.beam.common.date;

public enum DateFormatStyle {

	yyyyMMdd(DateFormatPattern.yyyyMMdd),

	yyyy_MM_dd(DateFormatPattern.yyyy_MM_dd),

	yyyy_MM_dd_HH_mm_ss(DateFormatPattern.yyyy_MM_dd_HH_mm_ss),

	HH_mm_ss(DateFormatPattern.HH_mm_ss),

	HHmm(DateFormatPattern.HHmm),

	;

	private String pattern;

	private DateFormatStyle(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

}
