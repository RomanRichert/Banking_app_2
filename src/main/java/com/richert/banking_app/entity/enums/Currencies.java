package com.richert.banking_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currencies {

    EUR(0),
    USD(1),
    UAH(2),
    RUB(3);

    private final int value;
}