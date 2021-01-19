package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = TemplateValidator.class)
@Documented
public @interface Template {

	String message() default "Template is not correct, Please Enter as LA or RA or CA";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
