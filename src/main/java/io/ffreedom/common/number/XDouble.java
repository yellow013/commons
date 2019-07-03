package io.ffreedom.common.number;

public final class XDouble {

	private long integerPart;
	private int decimalPart;

	public final static XDouble newDouble(long integerPart, int decimalPart) {
		return new XDouble(integerPart, decimalPart);
	}

	public XDouble(long integerPart, int decimalPart) {
		
	}

}
