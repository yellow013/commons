package io.ffreedom.common.functional;

import java.util.function.BiFunction;

@FunctionalInterface
public interface BeanTransfer<B1, B2> extends BiFunction<B1, B2, B2> {

	B2 transfer(B1 b1, B2 b2);

	@Override
	default B2 apply(B1 t, B2 u) {
		return transfer(t, u);
	}

}
