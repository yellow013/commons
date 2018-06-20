package io.ffreedom.common.functional;

import java.util.function.Function;

@FunctionalInterface
public interface Converter<FROM, TO> extends Function<FROM, TO> {

	default TO convert(FROM from) {
		return apply(from);
	}

}
