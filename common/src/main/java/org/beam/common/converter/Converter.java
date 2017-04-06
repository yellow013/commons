package org.beam.common.converter;

/**
 * 
 * @author peng.j
 *
 * @param <F>
 *            Form object.
 * @param <T>
 *            To object.
 */
public interface Converter<T1, T2> {

	default T2 leftRotation(T1 t1) {
		return null;
	}

	default T1 rightRotation(T2 t2) {
		return null;
	}

}
