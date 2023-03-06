package com.richert.banking_app.dto;

import lombok.Value;
import java.util.Set;

@Value
public class ClientRequestDTO {

    String taxCode;

    String firstName;

    String lastName;

    String email;

    String address;

    String phone;

    Set<String> accounts;
}