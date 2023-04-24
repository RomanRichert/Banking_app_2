package com.richert.banking_app.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class ClientRequestDTO {

    @NotNull(message = "Tax code shouldn't be null")
    @NotBlank(message = "Tax code shouldn't be empty")
    @Size(min = 1, max = 20, message = "Tax code should be between 1 and 20 characters")
    String taxCode;

    @NotNull(message = "First name shouldn't be null")
    @NotBlank(message = "First name shouldn't be empty")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    String firstName;

    @NotNull(message = "Last name shouldn't be null")
    @NotBlank(message = "Last name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    String lastName;

    @NotNull(message = "Email shouldn't be null")
    @Email(message = "Invalid email")
    @NotBlank(message = "Email shouldn't be empty")
    @Size(max = 60, message = "Max size of the email is 60 characters")
    String email;

    @NotNull(message = "Address shouldn't be null")
    @NotBlank(message = "Address shouldn't be empty")
    @Size(min = 10, max = 80, message = "Address should be between 10 and 80 characters")
    String address;

    @NotNull(message = "Phone number shouldn't be null")
    @NotBlank(message = "Phone number shouldn't be empty")
    @Digits(message = "Phone number should consist of digits", integer = 15, fraction = 0)
    @Size(min = 1, max = 20, message = "Phone number should be between 1 and 20 characters")
    String phone;

    @NotNull(message = "Managers id number shouldn't be null")
    @PositiveOrZero(message = "Managers id can't be negative")
    int manager;
}