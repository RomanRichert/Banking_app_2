package com.richert.banking_app.service.util;

import com.richert.banking_app.exception.InvalidClientStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for RequestChecker")
class RequestCheckerTest {

    @Test
    @DisplayName("Must throw an InvalidClientStatusException ")
    void checkClientStatus() {
        assertThrows(InvalidClientStatusException.class, () -> RequestChecker.checkClientStatus("Non-valid client status"), "InvalidClientStatusException should be thrown");
    }
}