package com.richert.banking_app.controller;

import com.richert.banking_app.service.ClientService;
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
@WebMvcTest(ClientController.class)
@DisplayName("Test class for ClientController")
class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;

    @Test
    @SneakyThrows
    @DisplayName("Makes an HTTP-request to /clients/status/pending . Expected HTTP-status - OK")
    void getAllClientsByStatus() {
        mvc.perform(get("/clients/pending")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DisplayName("Makes an HTTP-request to /clients/accounts?amount=200 . Expected HTTP-status - OK")
    void getAllClientsWhereBalanceMoreThan() {
        mvc.perform(get("/clients/accounts")
                        .param("amount", "200")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}