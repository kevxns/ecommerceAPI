package dev.keven.ecommerce.common.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    protected String message;
    protected HttpStatus status;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
