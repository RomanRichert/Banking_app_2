package com.richert.banking_app.service;

import com.richert.banking_app.dto.ClientResponseDTO;

import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClientsByStatus(String clientStatus);
}