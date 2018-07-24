package io.ffreedom.common.functional;

@FunctionalInterface
public interface Converter<FROM, TO> {

	TO convert(FROM from);

}
