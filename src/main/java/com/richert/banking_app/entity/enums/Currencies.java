package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currencies {
    EUR(1),
    USD(2),
    UAH(3),
    RUB(4);

    private final int value;
}