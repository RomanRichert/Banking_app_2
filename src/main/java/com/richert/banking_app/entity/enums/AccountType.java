package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    NEW(0),
    CREDIT(1),
    DEPOSIT(2),
    CURRENT(3);

    private final int value;
}