package com.lab365.app.pcp.infra.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;
    private List<String> excludedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
        excludedValues = Arrays.asList(annotation.excludedValues());
        acceptedValues.removeAll(excludedValues);
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return value == null || acceptedValues.contains(value.toString().toUpperCase());
    }
}
