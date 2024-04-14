package com.lab365.app.pcp.infra.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Error {
    private final HttpStatus status;
    private final String message;
    @Setter
    private List<ErrorList> errors;

    private void setFieldErrors(List<FieldError> fieldErrors){
        this.errors = fieldErrors.stream().map(e -> new ErrorList(
                e.getField(),
                Objects.requireNonNull(e.getDefaultMessage()).split("-")
        )).collect(Collectors.toList());
    }

    public static ResponseEntity<?> processFieldErrors(List<FieldError> fieldErrors) {
        Error error = new Error(HttpStatus.BAD_REQUEST, "Erro de Validação");
        error.setFieldErrors(fieldErrors);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    public static ResponseEntity<?> getError(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(new Error(status, e.getMessage()));
    }
}