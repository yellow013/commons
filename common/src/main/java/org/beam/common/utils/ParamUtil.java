package org.beam.common.utils;

public abstract class ParamUtil {

	public static boolean paramIsNull(Object objs) {
		return objs == null;
	}

	public static boolean paramIsNull(Object... objs) {
		return objs == null;
	}

}
