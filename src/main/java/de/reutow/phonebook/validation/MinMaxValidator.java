package de.reutow.phonebook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinMaxValidator implements ConstraintValidator<MinMax,Integer> {

    private  int min;
    private  int max;
    @Override
    public void initialize(MinMax constraintAnnotation) {

        max = constraintAnnotation.maxAge();
        min = constraintAnnotation.minAge();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value <= max && value > min;
    }
}
