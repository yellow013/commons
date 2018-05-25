package io.ffreedom.common.functional;

@FunctionalInterface
public interface BeanSetter<B1, B2> {

	B2 setBean(B1 b1, B2 b2);

}
