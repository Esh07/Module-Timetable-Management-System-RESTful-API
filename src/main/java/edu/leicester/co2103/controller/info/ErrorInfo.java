package edu.leicester.co2103.controller.info;

/*
 * This is a simple class that is used to send an error message to the client. 
 */
public class ErrorInfo {

	String message;

	/*
	 * Constructor
	 * Name: ErrorInfo
	 * parameters: String string - the error message
	 * return type: none
	 */
	public ErrorInfo(String string) {
		this.message = string;
	}

	/*
	 * Getter
	 * Name: getMessage
	 * parameters: none
	 * return type: String - the error message
	 * 
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * Setter
	 * Name: setMessage
	 * parameters: String message - the error message
	 * return type: none
	 * 
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
