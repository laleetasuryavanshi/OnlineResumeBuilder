package com.example.demo.exception;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<Object> handleResumeNotFound(ResumeNotFoundException rnfe) throws IOException {
    	return new ResponseEntity<>(rnfe.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }*/
	
	@ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<Object> handleResumeNotFound(ResumeNotFoundException rnfe, WebRequest request) throws IOException {
    	String errors = new String();
    	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, rnfe.getLocalizedMessage(), errors);
    	return ResponseEntity.badRequest().body(apiError);
    	//return new ResponseEntity<>(rnfe.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(ExperiencedUserNotFoundException.class)
	public ResponseEntity<Object> handleFresherUserNotFound
	(ExperiencedUserNotFoundException ee, WebRequest request) throws IOException {
		String errors = new String();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ee.getLocalizedMessage(), errors);
		return ResponseEntity.badRequest().body(apiError);
	}
    
    @ExceptionHandler(TemplateNotFoundException.class)
	public ResponseEntity<Object> handleTemplateNotFound(TemplateNotFoundException tnfe) throws IOException {
		return new ResponseEntity<>(tnfe.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}
    
    @ExceptionHandler(FresherNotFoundException.class)
	public ResponseEntity<Object> handleFresherNotFound
	(FresherNotFoundException bnfe, WebRequest request) throws IOException {
		String errors = new String();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, bnfe.getLocalizedMessage(), errors);
		return ResponseEntity.badRequest().body(apiError);
	}
    
    @ExceptionHandler(FresherUserNotFoundException.class)
	public ResponseEntity<Object> handleFresherUserNotFound
	(FresherUserNotFoundException fe, WebRequest request) throws IOException {
		String errors = new String();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, fe.getLocalizedMessage(), errors);
		return ResponseEntity.badRequest().body(apiError);
	}
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
}
    

}
