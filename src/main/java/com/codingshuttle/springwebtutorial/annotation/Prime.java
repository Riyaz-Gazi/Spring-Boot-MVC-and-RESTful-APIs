package com.codingshuttle.springwebtutorial.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PrimeNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Prime {
    String message() default "The number must be a prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
