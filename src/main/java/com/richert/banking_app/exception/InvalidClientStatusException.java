package com.richert.banking_app.exception;

import static com.richert.banking_app.exception.messages.ErrorMessage.INVALID_CLIENT_STATUS;

public class InvalidClientStatusException extends RuntimeException {

    public InvalidClientStatusException(String status) {
        super(status + INVALID_CLIENT_STATUS.getMessage());
    }
}