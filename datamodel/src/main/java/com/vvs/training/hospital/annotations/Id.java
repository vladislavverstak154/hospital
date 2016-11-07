package com.vvs.training.hospital.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
@Inherited
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Id {
	
}
