package io.ffreedom.common.functional;

import java.util.function.BiFunction;

@FunctionalInterface
public interface BeanSetter<B1, B2> extends BiFunction<B1, B2, B2> {

	B2 setBean(B1 b1, B2 b2);

	@Override
	default B2 apply(B1 t, B2 u) {
		return setBean(t, u);
	}

}
