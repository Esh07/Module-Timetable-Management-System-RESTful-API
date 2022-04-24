package edu.leicester.co2103.controller.info;

public class SuccessInfo {
    String message;

    public SuccessInfo(String string) {
        this.message = string;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
