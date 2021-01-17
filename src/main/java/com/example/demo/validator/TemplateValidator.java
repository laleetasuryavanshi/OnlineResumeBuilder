package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class TemplateValidator implements ConstraintValidator<Template, String> {
	List<String> templates = Arrays.asList("LA", "CA", "RA");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return templates.contains(value);

	}
}
