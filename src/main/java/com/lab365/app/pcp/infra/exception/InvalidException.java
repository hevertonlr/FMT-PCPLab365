package com.lab365.app.pcp.infra.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message){
        super(message);
    }
}
