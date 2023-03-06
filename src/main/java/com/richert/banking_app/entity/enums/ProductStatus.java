package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {

    ACTIVE(0),
    PENDING(1),
    REMOVED(2);

    private final int value;
}