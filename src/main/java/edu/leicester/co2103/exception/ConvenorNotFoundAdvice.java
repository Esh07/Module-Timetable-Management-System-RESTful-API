package edu.leicester.co2103.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.leicester.co2103.controller.info.ErrorInfo;

/*
 * This is a controller advice class that handles all the convenor not found exceptions
 * 
 */
@ControllerAdvice
public class ConvenorNotFoundAdvice {

	/*
	 * Exception handler for convenor not found
	 * 
	 * @param ex - exception
	 * 
	 * @return ErrorInfo - error message
	 */
	@ExceptionHandler(ConvenorNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	ErrorInfo convenorNotFoundHandler(ConvenorNotFoundException ex) {
		return new ErrorInfo(ex.getMessage());
	}

}
