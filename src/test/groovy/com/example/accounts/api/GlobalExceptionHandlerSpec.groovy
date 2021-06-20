package com.example.accounts.api

import com.example.accounts.exceptions.ApiException
import com.example.accounts.exceptions.ResourceNotFoundException
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

class GlobalExceptionHandlerSpec extends Specification {

    def exceptionHandler = new GlobalExceptionHandler()

    def 'should handle BindException and return errors'() {
        given:

        def bindingResult = Mock(BindingResult)
        def exception = new BindException(bindingResult)
        def error = Mock(FieldError)

        when:
        def response = exceptionHandler.handleBindException(exception)

        then:
        response
        response.errors[0].errorCode == 'BAD_REQUEST'
        response.errors[0].description == 'some error'

        interaction {
            bindingResult.getAllErrors() >> [error]
            error.getDefaultMessage() >> 'some error'

        }
    }

    def 'should handle ConstraintViolationException and return errors'() {
        given:
        def exception = Mock(ConstraintViolationException)
        def violation = Mock(ConstraintViolation)

        when:
        def response = exceptionHandler.handleConstraintViolationException(exception)

        then:
        response
        response.errors[0].errorCode == 'BAD_REQUEST'
        response.errors[0].description == 'violation error'

        interaction {
            exception.getConstraintViolations() >> [violation]
            violation.getMessage() >> 'violation error'
        }
    }

    def 'should handle ResourceNotFoundException'() {
        given:
        def exception = new ResourceNotFoundException("RESOURCE_NOT_FOUND", "Resource Not Found")

        when:
        def response = exceptionHandler.handleResourceNotFoundException(exception)

        then:
        response
        response.errors[0].errorCode == 'RESOURCE_NOT_FOUND'
        response.errors[0].description == 'Resource Not Found'
    }

    @Unroll('should handle #thrownException and return error')
    def 'should handle Exception'() {
        when:
        def response = exceptionHandler.handleException(exception)

        then:
        response

        where:
        thrownException | exception
        'exception'     | new Exception("Some Error")
        'api exception' | new ApiException("CODE", "Something wrong")

    }
}
