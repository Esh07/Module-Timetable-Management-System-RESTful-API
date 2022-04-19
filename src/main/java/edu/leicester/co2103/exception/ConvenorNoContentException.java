package edu.leicester.co2103.exception;

public class ConvenorNoContentException extends RuntimeException{
    public ConvenorNoContentException(Long id){
        super("There is no content for " + id);
    }
}
