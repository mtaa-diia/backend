package com.doklad.api.customers.utility.exception.controllerExcxaptions;

import com.doklad.api.customers.utility.exception.userExceptions.UserNotFoundException;
import com.doklad.api.developers.v1.utility.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
