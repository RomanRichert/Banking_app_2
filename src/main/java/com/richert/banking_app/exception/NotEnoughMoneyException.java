package com.richert.banking_app.exception;

import static com.richert.banking_app.exception.messages.ErrorMessage.NOT_ENOUGH_MONEY;

public class NotEnoughMoneyException extends BadRequestException {

    public NotEnoughMoneyException(String message) {
        super(NOT_ENOUGH_MONEY.getMessage() + message);
    }
}