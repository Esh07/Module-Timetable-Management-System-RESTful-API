package edu.leicester.co2103.exception;

/*
 * This is a custom exception class for bad request
 * 
 */
public class BadRequestException extends RuntimeException {

    /*
     * Constructor for BadRequestException
     * 
     * @param obj - object
     * 
     * @return void
     */
    public BadRequestException(Object obj) {
        super("Invalid input data");
    }

}
