package com.richert.banking_app.controller;

import com.richert.banking_app.service.ClientService;
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
@WebMvcTest(ClientController.class)
@DisplayName("Test class for ClientController")
class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;

    @Test
    @DisplayName("Makes an HTTP-request to /clients. Expected HTTP-status - OK")
    void getAllClients() throws Exception {
        mvc.perform(get("/clients/status/pending")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}