package com.doklad.api.customers.utility.exception.controllerExcxaptions;

import com.doklad.api.customers.utility.exception.notificationExceptions.NotificationNotFoundException;
import com.doklad.api.developers.v1.utility.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificationResponseEntityExceptionController {

    @ExceptionHandler(value = NotificationNotFoundException.class)
    public ResponseEntity<Object> exception(NotificationNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
