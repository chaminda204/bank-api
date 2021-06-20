package com.example.accounts.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Setter
@Getter
@AllArgsConstructor
@Builder
@JsonInclude(NON_NULL)
public class ApiResponse<T> {

    private T data;

    private List<ErrorResponse> errors;
}
