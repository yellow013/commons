package io.ffreedom.common.annotations.thread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface LockHeld {
}