package com.example.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.demo.validator.Template;

public class TemplateType {
	@Template
	@NotEmpty
	@NotNull
	private String template;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public TemplateType(String template) {
		super();
		this.template = template;
	}

	public TemplateType() {
		super();
		// TODO Auto-generated constructor stub
	}
}
