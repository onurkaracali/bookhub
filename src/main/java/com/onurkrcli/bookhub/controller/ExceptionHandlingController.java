package com.onurkrcli.bookhub.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.onurkrcli.bookhub.BookhubException;
import com.onurkrcli.bookhub.validation.ValidationException;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BookhubException.class)
	public BookhubResponse<String> processBookhubException(BookhubException exception) {
		BookhubResponse<String> response = new BookhubResponse<>();
		response.setResult("ERROR");
		
		response.setData(exception.getMessage());
		return response;
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> processValidationException(ValidationException validationException, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		BookhubResponse<String> response = new BookhubResponse<>();
		response.setResult("ERROR");
		
		response.setData(validationException.getMessage());
		return handleExceptionInternal(validationException, response, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
	
	@ExceptionHandler(Exception.class)
	public BookhubResponse<String> processGenericException() {
		BookhubResponse<String> response = new BookhubResponse<>();
		
		response.setResult("ERROR");
		response.setData("GENERIC_ERROR");
		return response;
	}
}
