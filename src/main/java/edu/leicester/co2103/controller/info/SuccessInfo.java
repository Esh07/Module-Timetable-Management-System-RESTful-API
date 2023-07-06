package edu.leicester.co2103.controller.info;

/*
 * This is a simple class that is used to send a success message to the client. 
 */
public class SuccessInfo {
    String message;

    /*
     * Constructor
     * Name: SuccessInfo
     * parameters: String string - the success message
     * return type: none
     * 
     */
    public SuccessInfo(String string) {
        this.message = string;
    }

    /*
     * Getter
     * Name: getMessage
     * parameters: none
     * return type: String - the success message
     * 
     */
    public String getMessage() {
        return message;
    }

    /*
     * Setter
     * Name: setMessage
     * parameters: String message - the success message
     * return type: none
     * 
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
