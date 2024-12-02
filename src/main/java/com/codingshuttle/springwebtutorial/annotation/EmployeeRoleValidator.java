package com.codingshuttle.springwebtutorial.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        if(role==null) return false;
        List<String> roles = List.of("USER", "ADMIN");
        return roles.contains(role);
    }
}
