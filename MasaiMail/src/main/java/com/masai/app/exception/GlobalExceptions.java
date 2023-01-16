package com.masai.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> myExpHandler(IllegalArgumentException ie) {
		System.out.println("inside myHandler method...");
		return new ResponseEntity<String>(ie.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyException> validationHandler(MethodArgumentNotValidException ex){
		
		MyException error = new MyException();
		
		error.setTimestamp(LocalDateTime.now());
		error.setMessage("Validation Error");
		String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
		error.setDetails(msg);
		
		return new ResponseEntity<MyException>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyException> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req) {
		MyException err=new MyException(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyException> myExceptionHandler(Exception e, WebRequest req) {
		MyException err = new MyException(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
}