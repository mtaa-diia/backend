package com.doklad.api.customers.utility.exception.secretExceptions;

public class SecretNotFoundException extends RuntimeException {
    public SecretNotFoundException(String message) {
        super(message);
    }
}
