package io.ffreedom.common.mark;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface BootMain {
	
	String[] args();

}
