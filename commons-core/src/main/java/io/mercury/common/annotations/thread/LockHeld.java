package io.mercury.common.annotations.thread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.LOCAL_VARIABLE })
public @interface LockHeld {
}