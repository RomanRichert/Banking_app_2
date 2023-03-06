package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    CREDIT(0),
    DEPOSIT(1),
    CURRENT(2);

    private final int value;
}