package com.springboot.angular.coffeesystem.util;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IsExistsValidatorHandler.class)
@Documented
public @interface IsExists {

    String message() default "Email is already taken!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
