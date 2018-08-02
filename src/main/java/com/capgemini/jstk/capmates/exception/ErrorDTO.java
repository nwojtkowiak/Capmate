package com.capgemini.jstk.capmates.exception;

public class ErrorDTO {

    private String error;
    //private String status;
    private  String message;

    public ErrorDTO(String error, String message){
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
