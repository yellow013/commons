package io.ffreedom.common.annotations.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.LOCAL_VARIABLE })
public @interface MayThrowsRuntimeException {

}
