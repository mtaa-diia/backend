package com.doklad.api.customers.utility.exception.exceptionControllers;

import com.doklad.api.customers.utility.exception.documentExceptions.DocumentAlreadyExist;
import com.doklad.api.customers.utility.exception.documentExceptions.DocumentNotFoundException;
import com.doklad.api.developers.v1.utility.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DocumentResponseEntityExceptionController {

    @ExceptionHandler(value = {DocumentNotFoundException.class})
    public ResponseEntity<ApiError> documentNotFoundException(DocumentNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = {DocumentAlreadyExist.class})
    public ResponseEntity<ApiError> documentAlreadyExistException(DocumentAlreadyExist exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
