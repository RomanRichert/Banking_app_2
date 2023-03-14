package com.richert.banking_app.exception;

import static com.richert.banking_app.exception.messages.ErrorMessage.INVALID_DATE;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(String date) {
        super(date + INVALID_DATE.getMessage());
    }
}