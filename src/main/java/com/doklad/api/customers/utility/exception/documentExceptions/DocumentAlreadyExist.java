package com.doklad.api.customers.utility.exception.documentExceptions;

public class DocumentAlreadyExist extends RuntimeException {
    public DocumentAlreadyExist(String message) {
        super(message);
    }
}
