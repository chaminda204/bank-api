package com.example.accounts.api.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String errorCode;

    private String description;

}
