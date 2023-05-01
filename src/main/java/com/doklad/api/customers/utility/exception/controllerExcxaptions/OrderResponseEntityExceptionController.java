package com.doklad.api.customers.utility.exception.controllerExcxaptions;

import com.doklad.api.customers.utility.exception.orderExceptions.OrderNotFoundException;
import com.doklad.api.developers.v1.utility.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderResponseEntityExceptionController {

    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<ApiError> exception(OrderNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
