package com.richert.banking_app.exception;

import static com.richert.banking_app.exception.messages.ErrorMessage.BANK_ACCOUNT_NOT_FOUND;

public class BankAccountNotFoundException extends EntityNotFoundException {

    public BankAccountNotFoundException(String id) {
        super(BANK_ACCOUNT_NOT_FOUND.getMessage() + id);
    }
}