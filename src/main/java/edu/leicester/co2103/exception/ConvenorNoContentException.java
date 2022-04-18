package edu.leicester.co2103.exception;

public class ConvenorNoContentException extends RuntimeException{
    public ConvenorNoContentException(Long id){
        super("Information successfully updated " + id);
    }
}
