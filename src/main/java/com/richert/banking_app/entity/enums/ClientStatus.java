package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientStatus {

    ACTIVE(0),
    PENDING(1),
    REMOVED(2),
    BLOCKED(3);

    private final int value;
}