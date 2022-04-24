package edu.leicester.co2103.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.leicester.co2103.controller.info.ErrorInfo;

@ControllerAdvice
public class ModuleNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ModuleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorInfo ModuleNotFoundHandler(ModuleNotFoundException ex) {
        return new ErrorInfo(ex.getMessage());
    }
}
