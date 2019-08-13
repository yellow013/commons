package io.ffreedom.common.functional;

import java.util.function.Function;

@FunctionalInterface
public interface Converter<FROM, TO> extends Function<FROM, TO> {

	TO convert(FROM from);
	
	@Override
	default TO apply(FROM f) {
		return convert(f);
	}

}
