package com.richert.banking_app.exception;

import static com.richert.banking_app.exception.messages.ErrorMessage.TRANSACTION_NOT_FOUND;

public class TransactionNotFoundException extends EntityNotFoundException {

    public TransactionNotFoundException(String message) {
        super(TRANSACTION_NOT_FOUND.getMessage() + message);
    }
}