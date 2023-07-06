package edu.leicester.co2103.exception;

/*
 * This is a custom exception class for convenor not found
 * 
 */
public class ConvenorNoContentException extends RuntimeException {

    /*
     * Constructor for ConvenorNoContentException
     * 
     * @param id - id
     * 
     * @return void
     */
    public ConvenorNoContentException(Long id) {
        super("There is no content for " + id);
    }
}
