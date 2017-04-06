package org.beam.common.comparison;

public enum ComparisonValue {

	EQUAL(0),

	GREATER(1),

	LESSER(-1),

	;

	private int code;

	private ComparisonValue(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
