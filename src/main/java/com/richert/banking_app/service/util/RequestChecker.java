package com.richert.banking_app.service.util;

import com.richert.banking_app.entity.enums.ClientStatus;
import com.richert.banking_app.exception.InvalidClientStatusException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestChecker {

    public static void checkClientStatus(String status) {
        if (status != null && !status.isBlank()) {
            for (ClientStatus cs : ClientStatus.values()) {
                if (status.equals(cs.name())) return;
            }
        }

        throw new InvalidClientStatusException(status);
    }
}