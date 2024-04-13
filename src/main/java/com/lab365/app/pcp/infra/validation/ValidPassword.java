package com.lab365.app.pcp.infra.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE,FIELD,ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPassword {
    String message() default "Password Inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
