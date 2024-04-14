package com.lab365.app.pcp.infra.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new WhitespaceRule(),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false)
        ));

        final RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid())
            return true;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(StringUtils.collectionToDelimitedString(validator.getMessages(result), "-")).addConstraintViolation();
        return false;
    }
}
