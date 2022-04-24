package edu.leicester.co2103.exception;

public class ModuleNoContentException extends RuntimeException {
    public ModuleNoContentException(Long id) {
        super("There is no content for " + id);
    }
}
