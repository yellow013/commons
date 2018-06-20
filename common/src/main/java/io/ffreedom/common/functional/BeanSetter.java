package io.ffreedom.common.functional;

import java.util.function.BiFunction;

@FunctionalInterface
public interface BeanSetter<B1, B2> extends BiFunction<B1, B2, B2> {

	default B2 setBean(B1 b1, B2 b2) {
		return apply(b1, b2);
	}

}
