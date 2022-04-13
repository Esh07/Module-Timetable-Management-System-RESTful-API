package edu.leicester.co2103.exception;

public class ConvenorNotFoundException extends RuntimeException{
	public ConvenorNotFoundException(Long id){
		super("Could not find employee " + id);
	}
}
