package com.example.accounts.api;

import com.example.accounts.api.model.ApiResponse;
import com.example.accounts.api.model.ErrorResponse;
import com.example.accounts.exceptions.ApiException;
import com.example.accounts.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleBindException(final BindException exception) {
        List<ErrorResponse> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(error -> convertToApiError((FieldError) error))
                .collect(Collectors.toList());
        return ApiResponse.builder()
                .errors(errors)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleConstraintViolationException(ConstraintViolationException ex) {
        List<ErrorResponse> errors = ex.getConstraintViolations().stream()
                .map(convertToApiError())
                .collect(Collectors.toList());

        return ApiResponse.builder()
                .errors(errors)
                .build();

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .errorCode(ex.getErrorCode())
                .description(ex.getDescription())
                .build();

        return ApiResponse.builder()
                .errors(asList(error))
                .build();
    }

    @ExceptionHandler({Exception.class, ApiException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleException(Exception ex) {

        ErrorResponse error = ErrorResponse.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .description("Internal Server Error")
                .build();

        return ApiResponse.builder()
                .errors(asList(error))
                .build();
    }

    private ErrorResponse convertToApiError(FieldError error) {
        return ErrorResponse.builder()
                .errorCode("BAD_REQUEST")
                .description(error.getDefaultMessage())
                .build();
    }

    private Function<ConstraintViolation, ErrorResponse> convertToApiError() {
        return error -> ErrorResponse.builder()
                .errorCode("BAD_REQUEST")
                .description(error.getMessage())
                .build();
    }


}
