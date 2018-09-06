package io.ffreedom.common.utils;

public final class ParamValidator {

	private ParamValidator() {
	}

	public final static boolean isNull(Object obj) {
		return obj == null;
	}

	public final static boolean isNull(Object... objs) {
		if (objs == null) {
			return true;
		}
		for (Object obj : objs) {
			if (obj == null)
				return true;
		}
		return false;
	}

}
