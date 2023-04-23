package com.doklad.api.customers.utility.exception.exceptionControllers;

import com.doklad.api.customers.utility.exception.userExceptions.UserAlreadyExistException;
import com.doklad.api.customers.utility.exception.userExceptions.UserNotFoundException;
import com.doklad.api.developers.v1.utility.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserResponseEntityExceptionController {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ApiError> userNotFoundException(UserNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<ApiError> userAlreadyExistException(UserAlreadyExistException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
