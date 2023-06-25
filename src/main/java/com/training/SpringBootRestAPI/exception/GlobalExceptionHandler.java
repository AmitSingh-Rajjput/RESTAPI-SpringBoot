package com.training.SpringBootRestAPI.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//We extendts ResponseEntityExceptionHandler for the validation of the entity fields
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(resourceNotFound.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFound(resourceNotFound expection,WebRequest webrequest){

		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),expection.getMessage(),webrequest.getDescription(false),"USER_NOT_FOUND");
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExist.class)
	public ResponseEntity<ErrorDetails> handleEmailAlreadyExist(EmailAlreadyExist expection,WebRequest webrequest){

		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),expection.getMessage(),webrequest.getDescription(false),"EMAIL_ALREADY EXISTS");
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	//handle global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception expection,WebRequest webrequest){

		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),expection.getMessage(),webrequest.getDescription(false),"Internal server error!!");
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		// In this method we return hashmap with the field name and message.
		Map<String,String> errors = new HashMap<>();
		
		// From this we get all the validation error
		List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		
		//then we segregate and put into and map with its proper message.
		errorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
    
	
	
}
