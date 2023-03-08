package com.richert.banking_app.service.impl;

import com.richert.banking_app.dto.ClientResponseDTO;
import com.richert.banking_app.entity.enums.ClientStatus;
import com.richert.banking_app.mapper.ClientMapper;
import com.richert.banking_app.repository.ClientRepository;
import com.richert.banking_app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.richert.banking_app.service.util.RequestChecker.checkClientStatus;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Override
    public List<ClientResponseDTO> getAllClientsByStatus(String clientStatus) {
        String status = clientStatus.toUpperCase();
        checkClientStatus(status);
        return clientMapper.clientsToClientDTOs(clientRepository.findByStatus(ClientStatus.valueOf(status)));
    }
}