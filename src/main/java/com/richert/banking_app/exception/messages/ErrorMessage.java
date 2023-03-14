package com.richert.banking_app.exception.messages;

import com.richert.banking_app.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    NOT_ENOUGH_MONEY("Not enough money on the account. id = "),
    BANK_ACCOUNT_NOT_FOUND("Bank account not found. id = "),
    AMOUNT_IS_0("Amount shouldn't be 0. "),
    TRANSFER_AMOUNT_IS_NEGATIVE("Transfer amount can't be negative. "),
    INVALID_CLIENT_STATUS(" - this status doesn't exist. Available statuses: " + Arrays.toString(AccountStatus.values())),
    INVALID_DATE(" - bad date format. Please enter the date in following format yyyy-MM-dd"),
    TRANSACTION_NOT_FOUND("Transaction not found. ");

    private final String message;
}