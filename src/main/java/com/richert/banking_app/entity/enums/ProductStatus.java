package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    ACTIVE(1),
    PENDING(2),
    REMOVED(3);

    private final int value;
}