package org.aloyolaa.springcloud.msvc.courses.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.aloyolaa.springcloud.msvc.courses.validator.UniqueNameDBValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameDBValidation.class)
public @interface UniqueNameDB {
    String message() default "El nombre ya esta registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
