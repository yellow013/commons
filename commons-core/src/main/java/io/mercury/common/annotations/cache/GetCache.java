package io.mercury.common.annotations.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE })
public @interface GetCache {

}
