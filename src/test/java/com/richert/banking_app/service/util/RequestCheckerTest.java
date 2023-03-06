package com.richert.banking_app.service.util;

import com.richert.banking_app.exception.InvalidClientStatusException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RequestCheckerTest {

    @Test
    void checkClientStatus() {
        assertThrows(InvalidClientStatusException.class, () -> RequestChecker.checkClientStatus("Non-valid client status"), "InvalidClientStatusException should be thrown");
    }
}