package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    NEW(0),
    ACTIVE(1),
    PENDING(2),
    BLOCKED(3),
    REMOVED(4);

    private final int value;
}