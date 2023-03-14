package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    ACTIVE(0),
    PENDING(1),
    BLOCKED(2),
    REMOVED(3);

    private final int value;
}