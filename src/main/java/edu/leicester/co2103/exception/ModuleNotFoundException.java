package edu.leicester.co2103.exception;

/*
 * This is a custom exception class for module not found
 * 
 */
public class ModuleNotFoundException extends RuntimeException {

    /*
     * Constructor for ModuleNotFoundException
     * 
     * @param code - code
     * 
     * @return void
     */
    public ModuleNotFoundException(String code) {
        super("Could not find module with id " + code);
    }
}
