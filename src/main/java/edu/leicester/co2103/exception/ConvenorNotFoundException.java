package edu.leicester.co2103.exception;

/*
 * This is a custom exception class for convenor not found
 * 
 */
public class ConvenorNotFoundException extends RuntimeException {

	/*
	 * Constructor for ConvenorNotFoundException
	 * 
	 * @param id - id
	 * 
	 * @return void
	 */
	public ConvenorNotFoundException(Long id) {
		super("Could not find convenor with id " + id);
	}
}
