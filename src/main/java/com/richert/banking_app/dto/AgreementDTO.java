package com.richert.banking_app.dto;

import lombok.Value;

@Value
public class AgreementDTO {

    int id;

    String accountProductStatus;

    String interestRate;

    String createdAt;

    String updatedAt;

    String account;

    String product;
}