package com.laleetaprojjects.main.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
	/*@ExceptionHandler(FresherNotFoundException.class)
	public ResponseEntity<Object> handleFresherNotFound(FresherNotFoundException bnfe) throws IOException {
		return new ResponseEntity<>(bnfe.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	
	
	@ExceptionHandler(FresherNotFoundException.class)
	public ResponseEntity<Object> handleFresherNotFound
	(FresherNotFoundException bnfe, WebRequest request) throws IOException {
		String errors = new String();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, bnfe.getLocalizedMessage(), errors);
		return ResponseEntity.badRequest().body(apiError);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

}
