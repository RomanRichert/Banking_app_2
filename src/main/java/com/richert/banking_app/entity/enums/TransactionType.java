package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {

    NEW(1),
    PENDING(4),
    APPROVED(8);

    private final int value;
}