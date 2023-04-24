package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.enums.Status;
import com.richert.banking_app.exception.InvalidClientStatusException;
import com.richert.banking_app.mapper.ClientMapper;
import com.richert.banking_app.mapper.ClientMapperImpl;
import com.richert.banking_app.repository.ClientRepository;
import com.richert.banking_app.repository.ManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.richert.banking_app.entity.enums.Status.REMOVED;
import static com.richert.banking_app.util.DtoCreator.getClientRequestDTO;
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

    @Mock
    ManagerRepository managerRepository;

    @Spy
    ClientMapper clientMapper = new ClientMapperImpl();

    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    @DisplayName("Testing getAllClientsByStatus()")
    void getAllClientsByStatus() {
        List<ClientResponseDTO> response = List.of(getClientResponseDTO());
        List<Client> clients = List.of(getClient());
        String status = Status.PENDING.toString();

        when(clientRepository.findByStatus(Status.valueOf(status))).thenReturn(clients);
        when(clientRepository.findByStatus(Status.ACTIVE)).thenReturn(List.of());

        assertEquals(response, clientService.getAllClientsByStatus(status), "The ids in the responseDTOs should be equal");
        assertEquals(response, clientService.getAllClientsByStatus("PeNdInG"), "The ids in the responseDTOs should be equal");
        assertEquals(List.of(), clientService.getAllClientsByStatus(Status.ACTIVE.toString()), "There should come an empty list");
        assertThrows(InvalidClientStatusException.class, () -> clientService.getAllClientsByStatus("Some stuff"), "Non valid ClientStatus, InvalidClientStatusException should be thrown");

        verify(clientRepository, times(3)).findByStatus(any());
    }

    @Test
    @DisplayName("Testing getAllClientsWhereBalanceMoreThan()")
    void getAllClientsWhereBalanceMoreThan() {
        List<ClientResponseDTO> response = List.of(getClientResponseDTO());
        List<Client> clients = List.of(getClient());

        when(clientRepository.findByAccountsBalanceGreaterThan(any(), eq(REMOVED), eq(REMOVED))).thenReturn(clients);

        assertEquals(response, clientService.getAllClientsWhereBalanceMoreThan(12.04), "Lists should be equal");

        verify(clientRepository).findByAccountsBalanceGreaterThan(any(), eq(REMOVED), eq(REMOVED));
    }

    @Test
    @DisplayName("Testing the creating of Client")
    void createClient() {
        when(clientRepository.save(any())).thenReturn(getClient());

        assertEquals(getClientResponseDTO(), clientService.createClient(getClientRequestDTO()), "DTOs should be equal");

        verify(clientRepository).save(any());
    }

    @Test
    @DisplayName("Testing of getting a Client by id")
    void getClientById(){
        when(clientRepository.findById(any())).thenReturn(Optional.of(getClient()));

        assertEquals(getClientResponseDTO(), clientService.getClientById("80cf3044-b1cd-11ed-8545-08979887bb18"), "DTOs should be equal");
        assertThrows(IllegalArgumentException.class, () -> clientService.getClientById("Non-valid id"), "Clients id is not valid, IllegalArgumentException should be thrown");
        assertThrows(NullPointerException.class, () -> clientService.getClientById(null), "Clients id is null, NullPointerException should be thrown");

        verify(clientRepository).findById(any());
    }

    @Test
    @DisplayName("Testing of getting Clients where ClientStatus is not \"REMOVED\"")
    void getAllNotDeletedClients(){
        List<Client> clients = List.of(getClient());
        List<ClientResponseDTO> response = List.of(getClientResponseDTO());

        when(clientRepository.findByStatusNot(REMOVED)).thenReturn(clients);

        assertEquals(response, clientService.getAllNotDeletedClients(), "Lists should be equal");

        verify(clientRepository).findByStatusNot(REMOVED);
    }

    @Test
    @DisplayName("Testing of getting Clients where ClientStatus is \"REMOVED\"")
    void getAllDeletedClients(){
        List<Client> clients = List.of(getClient());
        List<ClientResponseDTO> response = List.of(getClientResponseDTO());

        when(clientRepository.findByStatus(REMOVED)).thenReturn(clients);

        assertEquals(response, clientService.getAllDeletedClients(), "Lists should be equal");

        verify(clientRepository).findByStatus(REMOVED);
    }
}