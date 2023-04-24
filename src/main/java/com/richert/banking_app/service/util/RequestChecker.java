package com.richert.banking_app.service.util;

import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.enums.Status;
import com.richert.banking_app.exception.ClientNotFoundException;
import com.richert.banking_app.exception.InvalidClientStatusException;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.stream.Stream;

import static com.richert.banking_app.entity.enums.Status.REMOVED;

@UtilityClass
public class RequestChecker {

    public static void checkClientStatus(String status) {
        if (!Stream.of((Status.values())).map(Objects::toString).toList().contains(status))
            throw new InvalidClientStatusException(status);
    }

    public static void checkIfClientIsDeleted(Client client) {
        if (client.getStatus() == REMOVED) throw new ClientNotFoundException(client.getId().toString());
    }

    public static boolean checkIfStringIsNullOrBlank(String string) {
        return string != null && !string.isBlank();
    }
}