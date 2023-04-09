package com.richert.banking_app.service;

import com.richert.banking_app.dto.ClientPatchingDTO;
import com.richert.banking_app.dto.ClientRequestDTO;
import com.richert.banking_app.dto.ClientResponseDTO;

import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClientsByStatus(String clientStatus);

    List<ClientResponseDTO> getAllClientsWhereBalanceMoreThan(double amount);

    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO getClientById(String id);

    List<ClientResponseDTO> getAllNotDeletedClients();

    void updateClient(ClientPatchingDTO clientPatchingDTO, String id);

    void deleteClient(String id);
}