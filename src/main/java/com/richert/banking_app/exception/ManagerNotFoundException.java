package com.richert.banking_app.exception;

import static com.richert.banking_app.exception.messages.ErrorMessage.BANK_ACCOUNT_NOT_FOUND;

public class ManagerNotFoundException extends EntityNotFoundException {
    public ManagerNotFoundException(int manager) {
        super(BANK_ACCOUNT_NOT_FOUND.getMessage() + manager);
    }
}