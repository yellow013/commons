package io.ffreedom.commons.chronicle.base;

public class ChronicleWriteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3834475045588301175L;

	public ChronicleWriteException(Throwable throwable) {
		super(throwable);
	}

	public ChronicleWriteException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
