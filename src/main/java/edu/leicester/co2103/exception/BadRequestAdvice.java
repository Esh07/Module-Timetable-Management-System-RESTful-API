package edu.leicester.co2103.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.leicester.co2103.controller.info.ErrorInfo;

/*
 * This is a controller advice class that handles all the bad request exceptions
 * 
 */
public class BadRequestAdvice {

    /*
     * Exception handler for bad request
     * 
     * @param req - http servlet request
     * 
     * @param ex - exception
     * 
     * @return ErrorInfo - error message
     */
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorInfo BadRequestHandler(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(ex.getMessage());
    }
}
