package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class AdminIdException extends RuntimeException {

	private static final long serialVersionUID = -4847444923363831524L;

	public AdminIdException() {
		super();
	}
	
	public AdminIdException(String errMsg) {
		super(errMsg);
	}
}
