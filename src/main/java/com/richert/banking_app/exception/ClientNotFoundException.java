package com.richert.banking_app.exception;

import static com.richert.banking_app.exception.messages.ErrorMessage.CLIENT_NOT_FOUND;

public class ClientNotFoundException extends EntityNotFoundException {

    public ClientNotFoundException(String id) {
        super(CLIENT_NOT_FOUND.getMessage()+id);
    }
}