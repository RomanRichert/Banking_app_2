package com.richert.banking_app.dto;

import lombok.Value;

@Value
public class ClientRequestDTO {

    String taxCode;

    String firstName;

    String lastName;

    String email;

    String address;

    String phone;

    int manager;
}