package edu.leicester.co2103.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ConvenorNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ConvenorNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String convenorNotFoundHandler(ConvenorNotFoundException ex) {
		return ex.getMessage();
	}
	
	


}
