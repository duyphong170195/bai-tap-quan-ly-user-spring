package com.example.baitapquanlyuser.validates.impl;

import com.example.baitapquanlyuser.validates.EntityValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class BaseEntityValidator<T> implements EntityValidator<T> {

    private static Validator validator;

    public BaseEntityValidator(Validator validator){
        this.validator = validator;
    }

    @Override
    public Set<ConstraintViolation<T>> validate(T t) {
        return validator.validate(t);
    }
}
