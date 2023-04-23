package com.doklad.api.customers.utility.exception.documentExceptions;

public class DocumentAlreadyExistException extends RuntimeException {
    public DocumentAlreadyExistException(String message) {
        super(message);
    }
}
