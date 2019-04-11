package io.ffreedom.common.functional;

import java.util.function.BiFunction;

@FunctionalInterface
public interface BiConverter<FROM, TO> extends BiFunction<FROM, TO, TO> {

	TO convertTo(FROM from, TO to);

	@Override
	default TO apply(FROM from, TO to) {
		return convertTo(from, to);
	}

}
