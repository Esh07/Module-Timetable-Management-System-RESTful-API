package edu.leicester.co2103.exception;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException(String code) {
        super("Could not find module with id " + code);
    }
}
