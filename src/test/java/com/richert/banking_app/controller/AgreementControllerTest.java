package com.richert.banking_app.controller;

import com.richert.banking_app.service.AgreementService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@WebMvcTest(AgreementController.class)
@DisplayName("Test class for AgreementController")
class AgreementControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AgreementService agreementService;

    @Test
    @SneakyThrows
    @DisplayName("Makes an HTTP-request to /agreements?clientId=*any* . Expected HTTP-status - OK")
    void findAgreementsWhereClientIdIs() {
        mvc.perform(get("/agreements")
                        .param("clientId", "200")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}