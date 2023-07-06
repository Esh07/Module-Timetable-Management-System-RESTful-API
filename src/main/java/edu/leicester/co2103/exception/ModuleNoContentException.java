package edu.leicester.co2103.exception;

/*
 * This is a custom exception class for module not found
 * 
 */
public class ModuleNoContentException extends RuntimeException {

    /*
     * Constructor for ModuleNoContentException
     * 
     * @param id - id
     * 
     * @return void
     */
    public ModuleNoContentException(Long id) {
        super("There is no content for " + id);
    }
}
