package io.ffreedom.common.utils;

public final class ParamValidator {

	private ParamValidator() {
	}

	public final static boolean paramIsNull(Object objs) {
		return objs == null;
	}

	public final static boolean paramIsNull(Object... objs) {
		if (objs != null) {
			for (Object obj : objs) {
				if (obj == null) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

}
