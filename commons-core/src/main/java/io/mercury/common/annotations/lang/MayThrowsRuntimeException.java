package io.mercury.common.annotations.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.CONSTRUCTOR })
public @interface MayThrowsRuntimeException {

	Class<? extends RuntimeException>[] value() default RuntimeException.class;

}
