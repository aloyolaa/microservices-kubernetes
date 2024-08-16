package org.aloyolaa.springcloud.msvc.users.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.aloyolaa.springcloud.msvc.users.validator.UniqueEmailDBValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailDBValidation.class)
public @interface UniqueEmailDB {
    String message() default "El email ya esta registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
