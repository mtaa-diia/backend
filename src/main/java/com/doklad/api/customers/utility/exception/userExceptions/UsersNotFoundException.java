package com.doklad.api.customers.utility.exception.userExceptions;

public class UsersNotFoundException extends RuntimeException{
    public UsersNotFoundException(String message) {
        super(message);
    }
}
