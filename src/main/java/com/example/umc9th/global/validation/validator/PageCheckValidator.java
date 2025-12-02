package com.example.umc9th.global.validation.validator;

import com.example.umc9th.global.apiPayload.exception.handler.PageValidationException;
import com.example.umc9th.global.apiPayload.code.ErrorReason;
import com.example.umc9th.global.validation.annotation.PageCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageCheckValidator implements ConstraintValidator<PageCheck, Integer> {

    @Override
    public void initialize(PageCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value <= 0) {
            throw new PageValidationException(ErrorReason.of("PAGE_INVALID", "페이지 번호는 1 이상이어야 합니다."));
        }
        return true;
    }
}