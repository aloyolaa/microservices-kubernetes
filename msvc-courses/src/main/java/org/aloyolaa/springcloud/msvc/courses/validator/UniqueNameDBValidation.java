package org.aloyolaa.springcloud.msvc.courses.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.aloyolaa.springcloud.msvc.courses.service.CourseService;
import org.aloyolaa.springcloud.msvc.courses.validator.annotation.UniqueNameDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueNameDBValidation implements ConstraintValidator<UniqueNameDB, String> {
    @Autowired
    private CourseService courseService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return courseService == null || !courseService.existsByName(s);
    }
}
