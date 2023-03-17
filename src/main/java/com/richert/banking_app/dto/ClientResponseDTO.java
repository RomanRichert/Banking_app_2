package com.richert.banking_app.dto;

import lombok.Value;

import java.util.Set;

@Value
public class ClientResponseDTO {

    String id;

    String status;

    String taxCode;

    String firstName;

    String lastName;

    String email;

    String address;

    String phone;

    String createdAt;

    String manager;

    Set<String> accounts;
}