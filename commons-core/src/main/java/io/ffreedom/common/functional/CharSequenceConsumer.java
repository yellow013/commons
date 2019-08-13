package io.ffreedom.common.functional;

import java.util.function.Consumer;

@FunctionalInterface
public interface CharSequenceConsumer<T extends CharSequence> extends Consumer<T> {

}
