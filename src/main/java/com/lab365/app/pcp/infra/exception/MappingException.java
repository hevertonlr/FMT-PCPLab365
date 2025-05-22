package com.lab365.app.pcp.infra.exception;

public class MappingException extends RuntimeException {
    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
