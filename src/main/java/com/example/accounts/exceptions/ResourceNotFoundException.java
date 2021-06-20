package com.example.accounts.exceptions;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String errorCode, String description) {
        super(errorCode, description);
    }
}
