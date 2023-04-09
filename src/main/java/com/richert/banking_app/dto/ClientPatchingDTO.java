package com.richert.banking_app.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class ClientPatchingDTO {

    @NotBlank(message = "First name shouldn't be empty")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    String firstName;

    @NotBlank(message = "Last name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email shouldn't be empty")
    @Size(max = 60, message = "Max size of the email is 60 characters")
    String email;

    @NotBlank(message = "Address shouldn't be empty")
    @Size(min = 10, max = 80, message = "Address should be between 10 and 80 characters")
    String address;

    @NotBlank(message = "Phone number shouldn't be empty")
    @Digits(message = "Phone number should consist of digits", integer = 15, fraction = 0)
    @Size(min = 1, max = 20, message = "Phone number should be between 1 and 20 characters")
    String phone;
}