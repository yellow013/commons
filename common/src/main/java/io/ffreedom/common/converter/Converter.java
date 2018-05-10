package io.ffreedom.common.converter;

public interface Converter<FROM, TO> {

	TO convert(FROM from);

}
