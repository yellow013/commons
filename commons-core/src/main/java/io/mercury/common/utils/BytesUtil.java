package io.mercury.common.utils;

import javax.annotation.Nonnull;

import io.mercury.common.annotations.lang.MayThrowsRuntimeException;

public final class BytesUtil {

	private BytesUtil() {
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final int bytesToInt(@Nonnull byte[] bytes) {
		if (bytes == null || bytes.length < 4)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [4]");
		return ((bytes[0] & 0xFF) << 24) 
				| ((bytes[1] & 0xFF) << 16) 
				| ((bytes[2] & 0xFF) << 8) 
				| ((bytes[3] & 0xFF));
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final int bytesToInt(@Nonnull byte[] bytes, int offset) {
		if (bytes == null || bytes.length < offset + 4)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [offset + 4]");
		return ((bytes[offset] & 0xFF) << 24) 
				| ((bytes[offset + 1] & 0xFF) << 16) 
				| ((bytes[offset + 2] & 0xFF) << 8)
				| ((bytes[offset + 3] & 0xFF));
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final char bytesToChar(@Nonnull byte[] bytes) {
		if (bytes == null || bytes.length < 2)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [2]");
		return (char) (((bytes[0] & 0xFF) << 8) 
				| ((bytes[1] & 0xFF)));
	}

	@MayThrowsRuntimeException(ArrayIndexOutOfBoundsException.class)
	public static final char bytesToChar(@Nonnull byte[] bytes, int offset) {
		if (bytes == null || bytes.length < offset + 2)
			throw new ArrayIndexOutOfBoundsException("byte array length must be greater than [offset + 2]");
		return (char) (((bytes[offset] & 0xFF) << 8) 
				| ((bytes[offset + 1] & 0xFF)));
	}

	public static final long intMerge(int highPos, int lowPos) {
		// TODO
		return 0l;
	}

	public static final int longSplitHighPos(long l) {
		// TODO
		return 0;
	}

}
