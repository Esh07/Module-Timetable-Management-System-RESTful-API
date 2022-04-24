package edu.leicester.co2103.controller.info;

public class ErrorInfo {
	String message;

	public ErrorInfo(String string) {
		this.message = string;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
