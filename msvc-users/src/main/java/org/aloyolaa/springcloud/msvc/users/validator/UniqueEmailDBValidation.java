package org.aloyolaa.springcloud.msvc.users.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.aloyolaa.springcloud.msvc.users.service.UserService;
import org.aloyolaa.springcloud.msvc.users.validator.annotation.UniqueEmailDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailDBValidation implements ConstraintValidator<UniqueEmailDB, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService == null || !userService.existsByEmail(s);
    }
}
