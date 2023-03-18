package com.richert.banking_app.mapper;

import com.richert.banking_app.repository.ManagerRepository;
import com.richert.banking_app.service.impl.ManagerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.richert.banking_app.util.DtoCreator.getClientRequestDTO;
import static com.richert.banking_app.util.DtoCreator.getClientResponseDTO;
import static com.richert.banking_app.util.EntityCreator.getClient;
import static com.richert.banking_app.util.EntityCreator.getManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for ClientMapper")
class ClientMapperTest {

    private final ClientMapper clientMapper = new ClientMapperImpl();

    @InjectMocks
    private ManagerServiceImpl managerService;

    @Mock
    private static ManagerRepository managerRepository;

    @Test
    @DisplayName("Tests mapping of a Client to ClientResponseDTO. All fields except \"createdAt\" must match")
    void toDTO() {
        assertEquals(getClientResponseDTO(), clientMapper.toDTO(getClient()), "Something went wrong by mapping Client to ClientResponseDTO");
    }

    @Test
    @DisplayName("Tests mapping of a List<Client> to List<ClientResponseDTO>")
    void clientsToClientDTOs() {
        assertEquals(List.of(getClientResponseDTO()), clientMapper.clientsToClientDTOs(List.of(getClient())), "Something went wrong by mapping List<Client> to List<ClientResponseDTO>");
    }

    @Test
    @DisplayName("Tests mapping ClientRequestDTO into Client")
    void toEntity() {
        when(managerRepository.findById(any())).thenReturn(Optional.of(getManager()));

        assertEquals(getClient(), clientMapper.toEntity(getClientRequestDTO(), managerRepository));
        verify(managerRepository).findById(any());
    }
}