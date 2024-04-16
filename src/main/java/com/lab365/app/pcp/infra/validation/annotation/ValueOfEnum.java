package com.lab365.app.pcp.infra.validation.annotation;

import com.lab365.app.pcp.infra.validation.EnumConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = EnumConstraintValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String[] excludedValues() default {};

    String message() default "Valor de Enum inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
