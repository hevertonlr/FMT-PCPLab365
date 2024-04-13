package com.lab365.app.pcp.infra.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception ex){
        return Error.getError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handler(DataIntegrityViolationException ex){
        return Error.getError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handler(MethodArgumentNotValidException ex) {
        return Error.processFieldErrors(ex.getBindingResult().getFieldErrors());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handler(NotFoundException ex){
        return Error.getError(HttpStatus.NOT_FOUND, ex);
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handler(ConflictException ex){
        return Error.getError(HttpStatus.CONFLICT, ex);
    }
}
