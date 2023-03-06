package com.richert.banking_app.exception;

public class InvalidTransactionTypeException extends RuntimeException {

    public InvalidTransactionTypeException(String type) {
        super(type);
    }
}