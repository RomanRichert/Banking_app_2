package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.enums.AccountStatus;
import com.richert.banking_app.entity.enums.ClientStatus;
import com.richert.banking_app.exception.InvalidClientStatusException;
import com.richert.banking_app.mapper.ClientMapper;
import com.richert.banking_app.mapper.ClientMapperImpl;
import com.richert.banking_app.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
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
@DisplayName("Test class for ClientServiceImpl")
class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;

    @Spy
    ClientMapper clientMapper = new ClientMapperImpl();

    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    @DisplayName("Testing getAllClientsByStatus()")
    void getAllClientsByStatus() {
        List<ClientResponseDTO> response = List.of(getClientResponseDTO());
        List<Client> clients = List.of(getClient());
        String status = ClientStatus.PENDING.toString();

        when(clientRepository.findByStatus(ClientStatus.valueOf(status))).thenReturn(clients);
        when(clientRepository.findByStatus(ClientStatus.ACTIVE)).thenReturn(List.of());

        assertEquals(response, clientService.getAllClientsByStatus(status), "The ids in the responseDTOs should be equal");
        assertEquals(response, clientService.getAllClientsByStatus("PeNdInG"), "The ids in the responseDTOs should be equal");
        assertEquals(List.of(), clientService.getAllClientsByStatus(ClientStatus.ACTIVE.toString()), "There should come an empty list");
        assertThrows(InvalidClientStatusException.class, () -> clientService.getAllClientsByStatus("Some stuff"), "InvalidClientStatusException should be thrown");

        verify(clientRepository, times(3)).findByStatus(any());
    }

    @Test
    @DisplayName("Testing getAllClientsWhereBalanceMoreThan()")
    void getAllClientsWhereBalanceMoreThan() {
        List<ClientResponseDTO> response = List.of(getClientResponseDTO());
        List<Client> clients = List.of(getClient());

        when(clientRepository.findByAccountsBalanceGreaterThan(any(), AccountStatus.REMOVED, ClientStatus.REMOVED)).thenReturn(clients);

        assertEquals(response, clientService.getAllClientsWhereBalanceMoreThan(anyDouble()), "Lists should be equals");

        verify(clientRepository).findByAccountsBalanceGreaterThan(any(), AccountStatus.REMOVED, ClientStatus.REMOVED);
    }
}