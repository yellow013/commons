package io.ffreedom.commons.chronicle.map;

public class ChronicleIOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 439989412345254532L;

	public ChronicleIOException(Throwable throwable) {
		super(throwable.getMessage(), throwable);
	}

}
