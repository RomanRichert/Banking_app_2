package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.enums.ClientStatus;
import com.richert.banking_app.exception.InvalidClientStatusException;
import com.richert.banking_app.mapper.ClientMapper;
import com.richert.banking_app.mapper.ClientMapperImpl;
import com.richert.banking_app.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.richert.banking_app.util.DtoCreator.getClientResponseDTO;
import static com.richert.banking_app.util.EntityCreator.getClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;

    @Spy
    ClientMapper clientMapper = new ClientMapperImpl();

    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    void getAllClients() {
        List<ClientResponseDTO> response = List.of(getClientResponseDTO());
        List<Client> clients = List.of(getClient());
        String status = ClientStatus.PENDING.toString();

        when(clientRepository.findByStatus(ClientStatus.valueOf(status))).thenReturn(clients);
        when(clientRepository.findByStatus(ClientStatus.ACTIVE)).thenReturn(List.of());
        when(clientRepository.findAll()).thenReturn(clients);

        assertEquals(response.get(0).getId(), clientService.getAllClients(status).get(0).getId(), "The ids in the responseDTOs should be equal");
        assertEquals(response.get(0).getId(), clientService.getAllClients("PeNdInG").get(0).getId(), "The ids in the responseDTOs should be equal");
        assertEquals(List.of(), clientService.getAllClients(ClientStatus.ACTIVE.toString()), "There should come an empty list");
        assertEquals(response.get(0).getId(), clientService.getAllClients(null).get(0).getId(), "The ids in the responseDTOs should be equal");
        assertThrows(InvalidClientStatusException.class, () -> clientService.getAllClients("Some stuff"), "InvalidClientStatusException should be thrown");

        verify(clientRepository, times(3)).findByStatus(any());
        verify(clientRepository).findAll();
    }
}