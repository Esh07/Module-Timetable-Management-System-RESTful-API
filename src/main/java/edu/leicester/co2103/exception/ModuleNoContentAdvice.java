package edu.leicester.co2103.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.leicester.co2103.controller.info.ErrorInfo;

/*
 * This is a controller advice class that handles all the module not found exceptions
 * 
 */
public class ModuleNoContentAdvice {

    /*
     * Exception handler for module not found
     * 
     * @param ex - exception
     * 
     * @return ErrorInfo - error message
     */
    @ResponseBody
    @ExceptionHandler(ModuleNoContentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorInfo ModuleNoContentHandler(ModuleNoContentException ex) {
        return new ErrorInfo(ex.getMessage());
    }
}
