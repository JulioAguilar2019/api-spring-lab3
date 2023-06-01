package com.labperiodo3.exceptions;

import org.springframework.http.HttpStatus;

public class InvoicesAppException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    private HttpStatus status;
    private String message;

    public InvoicesAppException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public InvoicesAppException(HttpStatus status, String message, String message1) {
        super();
        this.status = status;
        this.message = message;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
