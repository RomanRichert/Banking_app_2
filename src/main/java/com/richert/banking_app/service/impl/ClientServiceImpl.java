package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.ClientPatchingDTO;
import com.richert.banking_app.dto.ClientRequestDTO;
import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.enums.AccountStatus;
import com.richert.banking_app.entity.enums.ClientStatus;
import com.richert.banking_app.exception.ClientNotFoundException;
import com.richert.banking_app.mapper.ClientMapper;
import com.richert.banking_app.repository.ClientRepository;
import com.richert.banking_app.repository.ManagerRepository;
import com.richert.banking_app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.richert.banking_app.service.util.RequestChecker.checkClientStatus;
import static com.richert.banking_app.service.util.RequestChecker.checkIfClientIsDeleted;
import static java.lang.System.currentTimeMillis;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final ManagerRepository managerRepository;

    @Override
    public List<ClientResponseDTO> getAllClientsByStatus(String clientStatus) {
        String status = clientStatus.toUpperCase();
        checkClientStatus(status);

        return clientMapper.clientsToClientDTOs(clientRepository.findByStatus(ClientStatus.valueOf(status)));
    }

    @Override
    public List<ClientResponseDTO> getAllClientsWhereBalanceMoreThan(double amount) {
        return clientMapper.clientsToClientDTOs(clientRepository.findByAccountsBalanceGreaterThan(BigDecimal.valueOf(amount), AccountStatus.REMOVED, ClientStatus.REMOVED));
    }

    @Override
    @Transactional
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        return clientMapper.toDTO(clientRepository.save(clientMapper.toEntity(clientRequestDTO, managerRepository)));
    }

    @Override
    public ClientResponseDTO getClientById(String id) {
        Client client = clientRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ClientNotFoundException(id));
        checkIfClientIsDeleted(client);

        return clientMapper.toDTO(client);
    }

    @Override
    public List<ClientResponseDTO> getAllNotDeletedClients() {
        return clientMapper.clientsToClientDTOs(clientRepository.findByStatusNot(ClientStatus.REMOVED));
    }

    @Override
    @Transactional
    public void updateClient(ClientPatchingDTO clientPatchingDTO, String id) {
        Client patchedClient = clientRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ClientNotFoundException(id));
        checkIfClientIsDeleted(patchedClient);

        if (clientPatchingDTO.getFirstName() != null && !clientPatchingDTO.getFirstName().isBlank()){
            patchedClient.setFirstName(clientPatchingDTO.getFirstName());
        }

        if (clientPatchingDTO.getLastName() != null && !clientPatchingDTO.getLastName().isBlank()){
            patchedClient.setLastName(clientPatchingDTO.getLastName());
        }

        if (clientPatchingDTO.getEmail() != null && !clientPatchingDTO.getEmail().isBlank()){
            patchedClient.setEmail(clientPatchingDTO.getEmail());
        }

        if (clientPatchingDTO.getAddress() != null && !clientPatchingDTO.getAddress().isBlank()){
            patchedClient.setAddress(clientPatchingDTO.getAddress());
        }

        if (clientPatchingDTO.getPhone() != null && !clientPatchingDTO.getPhone().isBlank()){
            patchedClient.setPhone(clientPatchingDTO.getPhone());
        }

        patchedClient.setUpdatedAt(new Timestamp(currentTimeMillis()));

        clientRepository.updateClientById(
                patchedClient.getFirstName(),
                patchedClient.getLastName(),
                patchedClient.getEmail(),
                patchedClient.getAddress(),
                patchedClient.getPhone(),
                patchedClient.getUpdatedAt(),
                UUID.fromString(id)
        );
    }

    @Override
    public void deleteClient(String id) {
        clientRepository.updateStatusById(ClientStatus.REMOVED, id);
    }
}