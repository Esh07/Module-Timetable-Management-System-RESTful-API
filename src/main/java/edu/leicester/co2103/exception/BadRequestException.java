package edu.leicester.co2103.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(Object obj) {
        super("Invalid input data");
    }

}
