package com.example.accounts.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    String errorCode;

    String description;

    public ApiException(String errorCode, String description, Throwable exception) {
        super(exception);
        this.errorCode = errorCode;
        this.description = description;
    }

    public ApiException(String errorCode, String description) {
        super();
        this.errorCode = errorCode;
        this.description = description;
    }

}
