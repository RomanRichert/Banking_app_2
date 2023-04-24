package com.richert.banking_app.service.util;

import com.richert.banking_app.entity.Client;
import com.richert.banking_app.exception.ClientNotFoundException;
import com.richert.banking_app.exception.InvalidClientStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.richert.banking_app.entity.enums.Status.REMOVED;
import static com.richert.banking_app.util.EntityCreator.getClient;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for RequestChecker")
class RequestCheckerTest {

    @Test
    @DisplayName("Must throw an InvalidClientStatusException")
    void checkClientStatus() {
        assertThrows(InvalidClientStatusException.class, () -> RequestChecker.checkClientStatus("Non-valid client status"), "InvalidClientStatusException should be thrown");
    }

    @Test
    @DisplayName("Must throw a ClientNotFoundException")
    void checkIfClientIsDeleted() {
        Client client = getClient();
        client.setStatus(REMOVED);

        assertThrows(ClientNotFoundException.class, () -> RequestChecker.checkIfClientIsDeleted(client), "ClientNotFoundException should be thrown");
    }

    @Test
    @DisplayName("The method checks if the incoming string is null or blank")
    void checkIfStringIsNullOrBlank() {
        assertTrue(RequestChecker.checkIfStringIsNullOrBlank("1237657"), "Expected true");
        assertFalse(RequestChecker.checkIfStringIsNullOrBlank(""), "Expected false");
        assertFalse(RequestChecker.checkIfStringIsNullOrBlank("    "), "Expected false");
        assertFalse(RequestChecker.checkIfStringIsNullOrBlank(" "), "Expected false");
        assertFalse(RequestChecker.checkIfStringIsNullOrBlank(null), "Expected false");
    }
}