package com.lab365.app.pcp.infra.validation.annotation;

import com.lab365.app.pcp.infra.validation.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface ValidPassword {
    String message() default "Password Inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
